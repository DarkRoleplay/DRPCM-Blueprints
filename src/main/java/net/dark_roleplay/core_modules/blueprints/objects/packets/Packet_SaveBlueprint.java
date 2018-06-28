package net.dark_roleplay.core_modules.blueprints.objects.packets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import io.netty.buffer.ByteBuf;
import net.dark_roleplay.core_modules.blueprints.References;
import net.dark_roleplay.core_modules.blueprints.handler.Permissions;
import net.dark_roleplay.core_modules.blueprints.objects.other.Mode;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.dark_roleplay.core_modules.blueprints.objects.translations.Translations;
import net.dark_roleplay.library.blueprints.Blueprint;
import net.dark_roleplay.library.blueprints.BlueprintUtil;
import net.dark_roleplay.library.networking.PacketBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.server.permission.PermissionAPI;

public class Packet_SaveBlueprint extends PacketBase.Server<Packet_SaveBlueprint>{

	private BlockPos pos;
	private BlockPos offset;
	private BlockPos size;
	private String name;
	private String architects;
	private Mode mode;
	
	public Packet_SaveBlueprint(){}
	
	public Packet_SaveBlueprint(TileEntityBlueprintController te){
		this.pos = te.getPos();
		this.offset = te.getOffset();
		this.size = te.getSize();
		this.name = te.getName();
		this.architects = te.getArchitects();
		this.mode = te.getMode();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.mode = Mode.getById(buf.readShort());
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.offset = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.size = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.name = ByteBufUtils.readUTF8String(buf);
		this.architects = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
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
		ByteBufUtils.writeUTF8String(buf, this.architects);
	}

	@Override
	public void handleServerSide(Packet_SaveBlueprint message, EntityPlayer player) {
		if(!PermissionAPI.hasPermission(player, Permissions.BLOCK_BLUEPRINT_SAVE)) {
			player.sendStatusMessage(new TextComponentTranslation(Translations.MISSING_PERMISSIONS_SAVE.getKey()), true);
			return;
		}
		player.getServer().addScheduledTask(new Runnable(){
			@Override
			public void run() {
				World world = player.getEntityWorld();
				TileEntity tileEntity = world.getTileEntity(message.pos);
				if(!(tileEntity instanceof TileEntityBlueprintController))
					return;
				TileEntityBlueprintController te = (TileEntityBlueprintController) tileEntity;
				
				te.setName(message.name);
				te.setOffset(message.offset);
				te.setSize(message.size);
				te.setMode(message.mode);
				te.markDirty();
				
				File structure = new File(References.FOLDER_BLUEPRINTS.getPath() + "/" + message.name + ".blueprint");
				structure.getParentFile().mkdirs();
				
				Blueprint bp = BlueprintUtil.createBlueprint(te.getWorld(), te.getPos().add(message.offset.getX(), message.offset.getY(), message.offset.getZ()), (short) message.size.getX(), (short) message.size.getY(), (short) message.size.getZ(), message.name, message.architects);
				player.getServer().addScheduledTask(new Runnable(){
					@Override
					public void run() {
						try {
							structure.createNewFile();
							OutputStream os = new FileOutputStream(structure);
							BlueprintUtil.writeToStream(os, bp);
							player.sendStatusMessage(new TextComponentTranslation(Translations.SUCCESS_SAVING.getKey(), message.name), true);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}

}
