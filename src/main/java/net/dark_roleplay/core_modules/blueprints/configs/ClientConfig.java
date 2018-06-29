package net.dark_roleplay.core_modules.blueprints.configs;

import net.dark_roleplay.core_modules.blueprints.References;
import net.dark_roleplay.library.configs.prefabs.RGBA;
import net.minecraftforge.common.config.Config;

@Config(modid = References.MODID, name = "Dark Roleplay Core/Modules/Blueprints", category = "client")
public class ClientConfig {
	
	@Config.Comment("Some Settings to customize the blueprint controller")
	public static Blueprints BLUEPRINTS = new Blueprints();
	
	public static class Blueprints{
		
		@Config.Name("Draws two boxes for invisible blocks")
		@Config.Comment("Increases visability but can cause higher fps drops ")
		public boolean HIGHLIGHT_INVISIBLE_BLOCKS = true;
		
		@Config.Name("Invisible Blocks color")
		@Config.Comment("The Color in which invisible blocks will be highlighted")
		public RGBA INVISIBLE_BLOCKS_COLOR = new RGBA(1.0F, 0.0F, 0.0F, 1.0F);
		
		@Config.Name("Air Blocks color")
		@Config.Comment("The Color in which Air blocks will be highlighted")
		public RGBA AIR_BLOCKS_COLOR = new RGBA(0.5F, 0.5F, 1.0F, 1.0F);
	}
}
