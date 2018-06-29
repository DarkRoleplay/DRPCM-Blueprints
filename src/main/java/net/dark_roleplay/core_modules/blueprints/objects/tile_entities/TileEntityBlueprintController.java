package net.dark_roleplay.core_modules.blueprints.objects.tile_entities;

import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.other.RenderMode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityBlueprintController extends TileEntityBuilder {

	private String name = "";
	private String architects = "";

	private Mode mode = Mode.SAVE;

	private RenderMode renderMode = RenderMode.NONE;

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("posX", this.offset.getX());
		compound.setInteger("posY", this.offset.getY());
		compound.setInteger("posZ", this.offset.getZ());
		compound.setInteger("sizeX", this.size.getX());
		compound.setInteger("sizeY", this.size.getY());
		compound.setInteger("sizeZ", this.size.getZ());
		compound.setInteger("mode_id", this.mode.getModeId());
		compound.setInteger("render_mode_id", this.renderMode.getModeId());
		compound.setString("name", this.name);
		return compound;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int i = compound.getInteger("posX");
		int j = compound.getInteger("posY");
		int k = compound.getInteger("posZ");
		this.offset = new BlockPos(i, j, k);
		int l = compound.getInteger("sizeX");
		int i1 = compound.getInteger("sizeY");
		int j1 = compound.getInteger("sizeZ");
		this.size = new BlockPos(l, i1, j1);

		if (compound.hasKey("mode")) {
			try {
				this.mode = Mode.valueOf(compound.getString("mode"));
			} catch (IllegalArgumentException var9) {
				this.mode = Mode.LOAD;
			}

			try {
				if (compound.hasKey("render_mode"))
					this.renderMode = RenderMode.valueOf(compound.getString("render_mode"));
				else
					this.renderMode = RenderMode.BOUNDING_BOX;
			} catch (IllegalArgumentException var9) {
				this.renderMode = RenderMode.BOUNDING_BOX;
			}
		} else {
			this.mode = Mode.getById(compound.getInteger("mode_id"));
			this.renderMode = RenderMode.getById(compound.getInteger("render_mode_id"));
		}

		this.name = compound.hasKey("name") ? compound.getString("name") : "";
		this.markDirty();
	}

	public void markDirty() {
		super.markDirty();
		//this.getWorld().markAndNotifyBlock(pos, this.getWorld().getChunkFromBlockCoords(pos), this.getWorld().getBlockState(pos), this.getWorld().getBlockState(pos), 2);;
	}

	public String getName() {
		return this.name;
	}

	public String getArchitects() {
		return this.architects;
	}

	public Mode getMode() {
		return this.mode;
	}

	public RenderMode getRenderMode() {
		return this.renderMode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArchitects(String architects) {
		this.architects = architects;
		this.markDirty();
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public void setRenderMode(RenderMode renderMode) {
		this.renderMode = renderMode;
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}
}
