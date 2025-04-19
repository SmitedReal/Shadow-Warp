package net.mcreator.basicallyshadowwarp.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.basicallyshadowwarp.network.BasicallyShadowWarpModVariables;

import java.util.List;
import java.util.Comparator;

public class ShadowWarpRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double fulldamage = 0;
		double entities = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if ((entity.getCapability(BasicallyShadowWarpModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BasicallyShadowWarpModVariables.PlayerVariables())).hasGyrokineticked == false) {
					if (entityiterator instanceof ItemEntity || entityiterator == entity) {
						continue;
					} else {
						{
							Entity _ent = entityiterator;
							_ent.teleportTo(x, (y + 1), z);
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport(x, (y + 1), z, _ent.getYRot(), _ent.getXRot());
						}
						{
							boolean _setval = true;
							entity.getCapability(BasicallyShadowWarpModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.hasGyrokineticked = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						entities = entities + 1;
					}
				} else if ((entity.getCapability(BasicallyShadowWarpModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BasicallyShadowWarpModVariables.PlayerVariables())).hasGyrokineticked == true) {
					if (entityiterator instanceof ItemEntity || entityiterator == entity) {
						continue;
					} else {
						entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.INDIRECT_MAGIC)), 20);
						{
							boolean _setval = false;
							entity.getCapability(BasicallyShadowWarpModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.hasGyrokineticked = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						fulldamage = 20 * entities;
					}
				}
			}
		}
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			_player.displayClientMessage(Component.literal("\u00A7c\u00A7lYou dealt §a§l" + fulldamage + "§c§l damage to §e§l" + entities + "§c§l entities."), false);
		}
	}
}
