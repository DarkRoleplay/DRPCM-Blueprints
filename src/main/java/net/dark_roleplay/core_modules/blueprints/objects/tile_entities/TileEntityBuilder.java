package net.dark_roleplay.core_modules.blueprints.objects.tile_entities;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityBuilder extends TileEntity{

	protected BlockPos offset = new BlockPos(0, 1, 0);
	protected BlockPos size = new BlockPos(5, 5, 5);
	
	public BlockPos getOffset() {
		return this.offset;
	}

	public BlockPos getSize() {
		return this.size;
	}
	
	public void setOffset(BlockPos offset) {
		this.offset = offset;
	}

	public void setSize(BlockPos size) {
		this.size = size;
	}
}
