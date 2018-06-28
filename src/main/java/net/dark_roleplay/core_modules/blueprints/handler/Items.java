package net.dark_roleplay.core_modules.blueprints.handler;

import java.util.ArrayList;
import java.util.List;

import net.dark_roleplay.core_modules.blueprints.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value= References.MODID)
@Mod.EventBusSubscriber
public class Items {

	private static List<ItemBlock> blockItems = new ArrayList<ItemBlock>();

	public static void addBlockItem(ItemBlock item){
		blockItems.add(item);
	}
	
	@SubscribeEvent
	public static final void registerEvent(RegistryEvent.Register<Item> event) {
		for(ItemBlock b : blockItems){
			event.getRegistry().register(b);
		}
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event){
		for(Item i : blockItems){
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
		}
	}
}
