package net.dark_roleplay.core_modules.blueprints.objects.variables.wrappers;

import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;

public class Variable_Mode {

	private Mode value;
	
	public Variable_Mode(){
		this.value = Mode.SAVE;
	}
	
	public Variable_Mode(Mode value){
		this.value = value;
	}
	
	public void set(Mode value){
		this.value = value;
	}
	
	public Mode get(){
		return this.value;
	}
}
