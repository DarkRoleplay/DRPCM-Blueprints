package net.dark_roleplay.core_modules.blueprints.handler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class Permissions {

	public static final String BLOCK_BLUEPRINT_LOAD = "drpcmblueprints.blocks.blueprint.load";
	public static final String BLOCK_BLUEPRINT_SAVE = "drpcmblueprints.blocks.blueprint.save";
	public static final String BLOCK_BLUEPRINT_CHANGE = "drpcmblueprints.blocks.blueprint.change";
	public static final String BLOCK_BLUEPRINT_SEE = "drpcmblueprints.blocks.blueprint.see";
	
	public static void init(FMLInitializationEvent event){
		PermissionAPI.registerNode(BLOCK_BLUEPRINT_LOAD, DefaultPermissionLevel.OP, "Allows players to load a Blueprint");
		PermissionAPI.registerNode(BLOCK_BLUEPRINT_SAVE, DefaultPermissionLevel.OP, "Allows players to save a Blueprint");
		PermissionAPI.registerNode(BLOCK_BLUEPRINT_CHANGE, DefaultPermissionLevel.OP, "Allows players to Change the settings of a Blueprint Controller");
		PermissionAPI.registerNode(BLOCK_BLUEPRINT_SEE, DefaultPermissionLevel.OP, "Allows a player to see the settings of a Blueprint Controller");
	}
}
