package net.dark_roleplay.core_modules.blueprints.objects.guis.labels;

import net.dark_roleplay.library.experimental.guis.elements.Gui_Label;
import net.dark_roleplay.library.experimental.variables.wrappers.IntegerWrapper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class Label_AxisInt extends Gui_Label{

	private IntegerWrapper var;

	public Label_AxisInt(IntegerWrapper var, String translationKey, int color){
		super(translationKey, color);
		this.var = var;
	}

	public Label_AxisInt(IntegerWrapper var, String translationKey, int color, int posX, int posY){
		super(translationKey, color, posX, posY);
		this.var = var;
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		if(this.text != null && !this.text.isEmpty()){
			String localizedText = I18n.format(this.text, this.var.get());
			int width = this.fr.getStringWidth(localizedText);
			if(this.hasShadow){
				this.fr.drawStringWithShadow(localizedText, this.posX + (this.width - width) / 2, this.posY, this.color);
			}else{
				this.fr.drawString(localizedText, this.posX + (this.width - width) / 2, this.posY, this.color);
			}
		}
		GlStateManager.color(1f, 1f, 1f);
	}

	@Override
	public void setText(String text){
		this.text = text;
	}

	@Override
	public String getText(){
		return this.text;
	}

}
