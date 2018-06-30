package net.dark_roleplay.core_modules.blueprints.objects.guis.buttons;

import net.dark_roleplay.core_modules.blueprints.objects.other.RenderMode;
import net.dark_roleplay.core_modules.blueprints.objects.translations.Translations;
import net.dark_roleplay.core_modules.blueprints.objects.variables.wrappers.Variable_RenderMode;
import net.dark_roleplay.library.experimental.guis.elements.Gui_Button;

public class Button_ChangeRenderMode extends Gui_Button{

	private Variable_RenderMode var;
	private int amount;
	
	public Button_ChangeRenderMode(Variable_RenderMode var, int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.var = var;
		this.setText(Translations.BUTTON_CHANGE_RENDER_MODE.get(Translations.getLocalizedRenderModeVar(var.get())));
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton){
		switch(this.var.get()){
			case NONE:
				this.var.set(RenderMode.BOUNDING_BOX);
				break;
			case BOUNDING_BOX:
				this.var.set(RenderMode.INVISIBLE);
				break;
			case INVISIBLE:
				this.var.set(RenderMode.NONE);
				break;
			default:
				this.var.set(RenderMode.BOUNDING_BOX);
				break;
		}
		this.setText(Translations.BUTTON_CHANGE_RENDER_MODE.get(Translations.getLocalizedRenderModeVar(var.get())));
		return true;
	}
}
