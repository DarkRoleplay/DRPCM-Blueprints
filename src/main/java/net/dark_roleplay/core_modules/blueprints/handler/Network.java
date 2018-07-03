package net.dark_roleplay.core_modules.blueprints.handler;

import net.dark_roleplay.core_modules.blueprints.References;
import net.dark_roleplay.core_modules.blueprints.objects.packets.Packet_LoadBlueprint;
import net.dark_roleplay.core_modules.blueprints.objects.packets.Packet_SaveBlueprint;
import net.dark_roleplay.core_modules.blueprints.objects.packets.SyncPacket_BlueprintBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class Network {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);

	public static void init(FMLInitializationEvent event){
		INSTANCE.registerMessage(SyncPacket_BlueprintBlock.class, SyncPacket_BlueprintBlock.class, 0, Side.SERVER);
		INSTANCE.registerMessage(SyncPacket_BlueprintBlock.class, SyncPacket_BlueprintBlock.class, 1, Side.CLIENT);
		INSTANCE.registerMessage(Packet_SaveBlueprint.class, Packet_SaveBlueprint.class, 2, Side.SERVER);		
		INSTANCE.registerMessage(Packet_LoadBlueprint.class, Packet_LoadBlueprint.class, 3, Side.SERVER);
	}
	
	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}
}
