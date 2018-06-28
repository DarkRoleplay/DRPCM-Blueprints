package net.dark_roleplay.core_modules.blueprints.configs;

import net.dark_roleplay.core_modules.blueprints.References;
import net.minecraftforge.common.config.Config;

@Config(modid = References.MODID, name = "Dark Roleplay Core/Modules/Blueprints", category = "client")
public class ClientConfig {
	
	@Config.Comment("Some Settings to customize the blueprint controller")
	public static Blueprints BLUEPRINTS = new Blueprints();
	
	public static class Blueprints{
		
		@Config.Name("Draws two boxes for invisible blocks")
		@Config.Comment("Increases visability but can cause higher fps drops ")
		public boolean HIGHLIGHT_INVISIBLE_BLOCKS = true;
		
		@Config.Name("Ivisible Blocks color")
		@Config.Comment("The Color in which invisible blocks will be highlighted")
		public RGBA INVISIBLE_BLOCKS_COLOR = new RGBA(0.5F, 0.5F, 1.0F, 0.5F);
		
	}
	
	public static class RGB{
		
		@Config.Name("Red Value")
		@Config.Comment("1.0 = full red, 0.0 = not red at all")
		@Config.RangeDouble(min = 0.0, max = 1.0)
		public float RED = 1.0F;
		
		@Config.Name("Green Value")
		@Config.Comment("1.0 = full green, 0.0 = not green at all")
		@Config.RangeDouble(min = 0.0, max = 1.0)
		public float GREEN = 1.0F;
		
		@Config.Name("Blue Value")
		@Config.Comment("1.0 = full blue, 0.0 = not blue at all")
		@Config.RangeDouble(min = 0.0, max = 1.0)
		public float BLUE = 1.0F;
		
		public RGB(float red, float green, float blue) {
			this.RED = red;
			this.GREEN = green;
			this.BLUE = blue;
		}
	}
	
	public static class RGBA{
		
		@Config.Name("Red Value")
		@Config.Comment("1.0 = full red, 0.0 = not red at all")
		@Config.RangeDouble(min = 0.0, max = 1.0)
		public float RED = 1.0F;
		
		@Config.Name("Green Value")
		@Config.Comment("1.0 = full green, 0.0 = not green at all")
		@Config.RangeDouble(min = 0.0, max = 1.0)
		public float GREEN = 1.0F;
		
		@Config.Name("Blue Value")
		@Config.Comment("1.0 = full blue, 0.0 = not blue at all")
		@Config.RangeDouble(min = 0.0, max = 1.0)
		public float BLUE = 1.0F;
		
		@Config.Name("Alpha Value")
		@Config.Comment("1.0 = full visible, 0.0 = not visible at all")
		@Config.RangeDouble(min = 0.0, max = 1.0)
		public float ALPHA = 1.0F;
		
		public RGBA(float red, float green, float blue, float alpha) {
			this.RED = red;
			this.GREEN = green;
			this.BLUE = blue;
			this.ALPHA = alpha;
		}
	}

}
