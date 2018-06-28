package net.dark_roleplay.core_modules.blueprints.objects.packets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.server.permission.PermissionAPI;

public class Packet_LoadBlueprint extends PacketBase.Server<Packet_LoadBlueprint>{

	private BlockPos pos;
	private BlockPos offset;
	private String name;
	private Mode mode;
	
	public Packet_LoadBlueprint(){}
	
	public Packet_LoadBlueprint(TileEntityBlueprintController te){
		this.pos = te.getPos();
		this.offset = te.getOffset();
		this.name = te.getName();
		this.mode = te.getMode();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.mode = Mode.getById(buf.readShort());
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.offset = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.name = ByteBufUtils.readUTF8String(buf);
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
		ByteBufUtils.writeUTF8String(buf, this.name);
	}

	@Override
	public void handleServerSide(Packet_LoadBlueprint message, EntityPlayer player) {
		if(!PermissionAPI.hasPermission(player, Permissions.BLOCK_BLUEPRINT_LOAD)) {
			player.sendStatusMessage(new TextComponentTranslation(Translations.MISSING_PERMISSIONS_LOAD.getKey()), true);
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
				te.setMode(message.mode);
				te.markDirty();
				
				References.FOLDER_BLUEPRINTS.mkdirs();
				File structure = new File(References.FOLDER_BLUEPRINTS.getPath() + "/" + message.name + ".blueprint");
				if(structure.exists()){
					try {
						Blueprint bp = BlueprintUtil.readFromFile(new FileInputStream(structure));
						if(bp != null){
							bp.build(te.getWorld(), te.getPos().add(te.getOffset()), Rotation.NONE, Mirror.NONE);
						}
						player.sendStatusMessage(new TextComponentTranslation(Translations.SUCCESS_LOADING.getKey(), message.name), true);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

}
