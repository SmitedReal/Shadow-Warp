
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.basicallyshadowwarp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.basicallyshadowwarp.item.ShadowWarpItem;
import net.mcreator.basicallyshadowwarp.BasicallyShadowWarpMod;

public class BasicallyShadowWarpModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, BasicallyShadowWarpMod.MODID);
	public static final RegistryObject<Item> SHADOW_WARP = REGISTRY.register("shadow_warp", () -> new ShadowWarpItem());
	// Start of user code block custom items
	// End of user code block custom items
}
