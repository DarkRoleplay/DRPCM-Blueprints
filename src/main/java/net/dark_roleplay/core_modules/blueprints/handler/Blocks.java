package net.dark_roleplay.core_modules.blueprints.handler;

import net.dark_roleplay.core_modules.blueprints.References;
import net.dark_roleplay.core_modules.blueprints.objects.blocks.BlueprintController;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(References.MODID)
@Mod.EventBusSubscriber
public class Blocks {

	public static final Block BLUEPRINT_CONTROLLER = null;

	@SubscribeEvent
	public static final void register(RegistryEvent.Register<Block> event) {
		registerBlocks(
			event.getRegistry(),
			new BlueprintController("blueprint_controller")
		);

		GameRegistry.registerTileEntity(TileEntityBlueprintController.class, new ResourceLocation(References.MODID, "structure_controller"));

	}

	private static final void registerBlocks(IForgeRegistry<Block> registry, Block... blocks){
		for(Block block : blocks){
			registry.register(block);
			Items.addBlockItem((ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()).setTranslationKey(block.getTranslationKey()));
		}
	}
}
