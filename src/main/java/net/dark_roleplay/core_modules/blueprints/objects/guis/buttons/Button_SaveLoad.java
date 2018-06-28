package net.dark_roleplay.core_modules.blueprints.objects.guis.buttons;

import net.dark_roleplay.core_modules.blueprints.objects.guis.GuiBlueprintController;
import net.dark_roleplay.library.experimental.guis.elements.Gui_Button;

public class Button_SaveLoad extends Gui_Button{

	private boolean saveMode;
	
	private GuiBlueprintController parent;
	
	public Button_SaveLoad(GuiBlueprintController parent, boolean saveMode, int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.saveMode = saveMode;
		this.parent = parent;
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton){
		if(this.saveMode){
			this.parent.save();
		}else{
			this.parent.load();
		}
		return true;
	}
}
