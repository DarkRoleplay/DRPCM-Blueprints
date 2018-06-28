package net.dark_roleplay.core_modules.blueprints.objects.variables.wrappers;

import net.dark_roleplay.core_modules.blueprints.objects.other.RenderMode;

public class Variable_RenderMode {

	private RenderMode value;
	
	public Variable_RenderMode(){
		this.value = RenderMode.BOUNDING_BOX;
	}
	
	public Variable_RenderMode(RenderMode value){
		this.value = value;
	}
	
	public void set(RenderMode value){
		this.value = value;
	}
	
	public RenderMode get(){
		return this.value;
	}
}
