package net.dark_roleplay.core_modules.blueprints.objects.other;

import net.minecraft.util.IStringSerializable;

public enum RenderMode implements IStringSerializable {
	NONE("none", 0), BOUNDING_BOX("bounding_box", 1), INVISIBLE("all", 2);

	private static final RenderMode[] MODES;
	private final String modeName;
	private final int modeId;

	private RenderMode(String modeName, int modeId) {
		this.modeName = modeName;
		this.modeId = modeId;
	}

	public String getName() {
		return this.modeName;
	}

	public int getModeId() {
		return this.modeId;
	}

	public static RenderMode getById(int id) {
		return id >= 0 && id < MODES.length ? MODES[id] : MODES[0];
	}

	static {
		MODES = values();
	}
}
