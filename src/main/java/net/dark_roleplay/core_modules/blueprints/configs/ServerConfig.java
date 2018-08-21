package net.dark_roleplay.core_modules.blueprints.configs;

import net.dark_roleplay.core_modules.blueprints.References;
import net.minecraftforge.common.config.Config;

@Config(modid = References.MODID, name = "Dark Roleplay Core/Modules/Blueprints", category = "server")
public class ServerConfig {
	
	@Config.Name("Command Alias - Create Blueprint Controller")
	@Config.Comment("this is a list of all possible alliases for the command")
	public static String[] COMMAND_CREATE_BPC_ALIASES = new String[] {"blueprintcontrollercreate", "bpcontrollercreate", "blueprintccreate"};
	
}
