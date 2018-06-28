package net.dark_roleplay.core_modules.blueprints.handler;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class Permissions {

	public static final String BLOCK_BLUEPRINT_LOAD = "drpcmblueprints.blocks.blueprint.load";
	public static final String BLOCK_BLUEPRINT_SAVE = "drpcmblueprints.blocks.blueprint.save";
	
	@EventHandler
	public static void init(FMLInitializationEvent event){
		PermissionAPI.registerNode(BLOCK_BLUEPRINT_LOAD, DefaultPermissionLevel.OP, "Allows players to Load a Blueprint");
		PermissionAPI.registerNode(BLOCK_BLUEPRINT_SAVE, DefaultPermissionLevel.OP, "Allows players to Save a Blueprint");
	}
}
