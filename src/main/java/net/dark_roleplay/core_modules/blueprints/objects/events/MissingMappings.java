package net.dark_roleplay.core_modules.blueprints.objects.events;

import com.google.common.collect.ImmutableList;

import net.dark_roleplay.core_modules.blueprints.References;
import net.dark_roleplay.core_modules.blueprints.handler.Blocks;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = References.MODID)
public class MissingMappings {

	@SubscribeEvent
	public static void MissingMappingsBlock(RegistryEvent.MissingMappings<Block> event){
		ImmutableList<Mapping<Block>> mappings = event.getAllMappings();
		for(RegistryEvent.MissingMappings.Mapping mapping : mappings){
			String name = mapping.key.toString();
			if(name.equals("drpcore:blueprint_controller")){
				mapping.remap(Blocks.BLUEPRINT_CONTROLLER);
			}
		}
	}
	
}