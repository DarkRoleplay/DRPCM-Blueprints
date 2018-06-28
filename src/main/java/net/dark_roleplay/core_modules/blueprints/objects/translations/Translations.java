package net.dark_roleplay.core_modules.blueprints.objects.translations;

import net.dark_roleplay.core_modules.blueprints.References;
import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.other.RenderMode;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IStringSerializable;

public class Translations {
		
	public static final Translation BUTTON_CHANGE_RENDER_MODE 	= new Translation(References.MODID + ".gui.structure.button.render_mode");
	public static final Translation BUTTON_CHANGE_MODE 			= new Translation(References.MODID + ".gui.structure.button.mode");
	public static final Translation BUTTON_SAVE 				= new Translation(References.MODID + ".gui.structure.button.save");
	public static final Translation BUTTON_LOAD 				= new Translation(References.MODID + ".gui.structure.button.load"); 
	public static final Translation LABEL_OFFSET				= new Translation(References.MODID + ".gui.structure.label.offset");
	public static final Translation LABEL_OFFSET_X 				= new Translation(References.MODID + ".gui.structure.label.offset.x");
	public static final Translation LABEL_OFFSET_Y 				= new Translation(References.MODID + ".gui.structure.label.offset.y");
	public static final Translation LABEL_OFFSET_Z 				= new Translation(References.MODID + ".gui.structure.label.offset.z");  
	public static final Translation LABEL_SIZE					= new Translation(References.MODID + ".gui.structure.label.size");
	public static final Translation LABEL_SIZE_X 				= new Translation(References.MODID + ".gui.structure.label.size.x");
	public static final Translation LABEL_SIZE_Y 				= new Translation(References.MODID + ".gui.structure.label.size.y");
	public static final Translation LABEL_SIZE_Z 				= new Translation(References.MODID + ".gui.structure.label.size.z");         
	public static final Translation NAME 						= new Translation(References.MODID + ".gui.structure.label.blueprint_name");
	public static final Translation LABEL_ARCHITECTS 			= new Translation(References.MODID + ".gui.structure.label.architects");
	public static final Translation VAR_RENDER_MODE_NONE 		= new Translation(References.MODID + ".gui.structure.var.render_mode.none");
	public static final Translation VAR_RENDER_MODE_BB 			= new Translation(References.MODID + ".gui.structure.var.render_mode.bounding_box");
	public static final Translation VAR_RENDER_MODE_ALL 		= new Translation(References.MODID + ".gui.structure.var.render_mode.all");
	public static final Translation VAR_MODE_SAVE 				= new Translation(References.MODID + ".gui.structure.var.mode.save");
	public static final Translation VAR_MODE_LOAD 				= new Translation(References.MODID + ".gui.structure.var.mode.load");
	public static final Translation VAR_MODE_CORNER 			= new Translation(References.MODID + ".gui.structure.var.mode.corner");
	

	public static final Translation MISSING_PERMISSIONS_SAVE 	= new Translation(References.MODID + ".message.missing_permissions.save");
	public static final Translation MISSING_PERMISSIONS_LOAD 	= new Translation(References.MODID + ".message.missing_permissions.load");

	public static final Translation SUCCESS_SAVING 	= new Translation(References.MODID + ".message.success.save");
	public static final Translation SUCCESS_LOADING 	= new Translation(References.MODID + ".message.success.load");
	
	public static String getLocalizedModeVar(Mode mode) {
		switch(mode) {
		case SAVE:
			return VAR_MODE_SAVE.get();
		case LOAD:
			return VAR_MODE_LOAD.get();
		case CORNER:
			return VAR_MODE_CORNER.get();
		default:
			return VAR_MODE_CORNER.get();
		}
	}
	
	public static String getLocalizedRenderModeVar(RenderMode mode) {
		switch(mode) {
		case BOUNDING_BOX:
			return VAR_RENDER_MODE_BB.get();
		case INVISIBLE:
			return VAR_RENDER_MODE_ALL.get();
		case NONE:
			return VAR_RENDER_MODE_NONE.get();
		default:
			return VAR_RENDER_MODE_NONE.get();
		}
	}
	
	public static class Translation{
		String key;
		
		public Translation(String key) {
			this.key = key;
		}
		
		public String get(Object... parameters) {
			return I18n.format(key, parameters);
		}
		
		public String getKey() {
			return this.key;
		}
	}
}
