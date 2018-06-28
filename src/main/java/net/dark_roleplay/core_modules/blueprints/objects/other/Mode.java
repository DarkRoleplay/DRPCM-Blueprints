package net.dark_roleplay.core_modules.blueprints.objects.other;

import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.minecraft.util.IStringSerializable;

public enum Mode implements IStringSerializable {
	SAVE("save", 0), LOAD("load", 1), CORNER("corner", 2);

	private static final Mode[] MODES;
	private final String modeName;
	private final int modeId;

	private Mode(String modeName, int modeId) {
		this.modeName = modeName;
		this.modeId = modeId;
	}

	public String getName() {
		return this.modeName;
	}

	public int getModeId() {
		return this.modeId;
	}

	public static Mode getById(int id) {
		return id >= 0 && id < MODES.length ? MODES[id] : MODES[0];
	}

	static {
		MODES = values();
	}
}