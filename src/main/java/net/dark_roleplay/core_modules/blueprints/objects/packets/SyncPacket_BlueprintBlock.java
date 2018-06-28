package net.dark_roleplay.core_modules.blueprints.objects.packets;

import io.netty.buffer.ByteBuf;
import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.other.RenderMode;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.dark_roleplay.library.networking.PacketBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class SyncPacket_BlueprintBlock extends PacketBase.Server<SyncPacket_BlueprintBlock>{

	private BlockPos pos;
	private BlockPos offset;
	private BlockPos size;
	private String name;
	private Mode mode;
	private RenderMode renderMode;
	
	public SyncPacket_BlueprintBlock(){
		
	}
	
	public SyncPacket_BlueprintBlock(TileEntityBlueprintController te){
		this.pos = te.getPos();
		this.offset = te.getOffset();
		this.size = te.getSize();
		this.name = te.getName();
		this.mode = te.getMode();
		this.renderMode = te.getRenderMode();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.renderMode = RenderMode.getById(buf.readShort());
		this.mode = Mode.getById(buf.readShort());
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.offset = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.size = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.name = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(this.renderMode.getModeId());
		buf.writeShort(this.mode.getModeId());
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
		buf.writeInt(this.offset.getX());
		buf.writeInt(this.offset.getY());
		buf.writeInt(this.offset.getZ());
		buf.writeInt(this.size.getX());
		buf.writeInt(this.size.getY());
		buf.writeInt(this.size.getZ());
		ByteBufUtils.writeUTF8String(buf, this.name);
	}

	@Override
	public void handleServerSide(SyncPacket_BlueprintBlock message, EntityPlayer player) {
		player.getServer().addScheduledTask(new Runnable(){
			@Override
			public void run() {
				World world = player.getEntityWorld();
				TileEntity tileEntity = world.getTileEntity(message.pos);
				if(!(tileEntity instanceof TileEntityBlueprintController))
					return;
				TileEntityBlueprintController te = (TileEntityBlueprintController) tileEntity;
				
				te.setRenderMode(message.renderMode);
				te.setName(message.name);
				te.setOffset(message.offset);
				te.setSize(message.size);
				te.setMode(message.mode);
				te.markDirty();
			}
		});
	}

}
