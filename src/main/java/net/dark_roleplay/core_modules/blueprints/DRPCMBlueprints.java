package net.dark_roleplay.core_modules.blueprints;

import net.dark_roleplay.core_modules.blueprints.handler.Permissions;
import net.dark_roleplay.core_modules.blueprints.objects.packets.Packet_LoadBlueprint;
import net.dark_roleplay.core_modules.blueprints.objects.packets.Packet_SaveBlueprint;
import net.dark_roleplay.core_modules.blueprints.objects.packets.SyncPacket_BlueprintBlock;
import net.dark_roleplay.core_modules.blueprints.objects.tesrs.TESRBlueprintController;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, dependencies = References.DEPENDECIES)
public class DRPCMBlueprints{

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	References.init(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    	Permissions.init(event);
    	
    	if(event.getSide().isClient()) {
    		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlueprintController.class, new TESRBlueprintController());
    	}

		INSTANCE.registerMessage(SyncPacket_BlueprintBlock.class, SyncPacket_BlueprintBlock.class, 0, Side.SERVER);
		INSTANCE.registerMessage(Packet_SaveBlueprint.class, Packet_SaveBlueprint.class, 1, Side.SERVER);		
		INSTANCE.registerMessage(Packet_LoadBlueprint.class, Packet_LoadBlueprint.class, 2, Side.SERVER);
    }
    
	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}
}
