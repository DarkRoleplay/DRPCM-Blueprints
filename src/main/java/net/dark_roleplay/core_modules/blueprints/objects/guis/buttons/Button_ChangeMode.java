package net.dark_roleplay.core_modules.blueprints.objects.guis.buttons;

import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.translations.Translations;
import net.dark_roleplay.core_modules.blueprints.objects.variables.wrappers.Variable_Mode;
import net.dark_roleplay.library.experimental.guis.elements.Gui_Button;
import net.minecraft.client.resources.I18n;

public class Button_ChangeMode extends Gui_Button{

	private Variable_Mode var;
	private int amount;
	
	public Button_ChangeMode(Variable_Mode var, int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.var = var;
		this.setText(Translations.BUTTON_CHANGE_RENDER_MODE.get(Translations.getLocalizedModeVar(var.get())));
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton){
		switch(this.var.get()){
			case CORNER:
				this.var.set(Mode.SAVE);
				break;
			case LOAD:
				this.var.set(Mode.CORNER);
				break;
			case SAVE:
				this.var.set(Mode.LOAD);
				break;
			default:
				this.var.set(Mode.LOAD);
				break;
		}
		this.setText(Translations.BUTTON_CHANGE_RENDER_MODE.get(Translations.getLocalizedModeVar(var.get())));
		return true;
	}
}
