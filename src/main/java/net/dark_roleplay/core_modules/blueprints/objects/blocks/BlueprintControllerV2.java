package net.dark_roleplay.core_modules.blueprints.objects.blocks;

import net.dark_roleplay.core_modules.blueprints.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlueprintControllerV2 extends Block{

	public BlueprintControllerV2(String registryName) {
		super(Material.IRON, MapColor.SILVER);
        this.setDefaultState(this.blockState.getBaseState());
        this.setRegistryName(registryName);
        this.setTranslationKey(References.MODID + "." + registryName);
        this.setBlockUnbreakable();
	}

}
