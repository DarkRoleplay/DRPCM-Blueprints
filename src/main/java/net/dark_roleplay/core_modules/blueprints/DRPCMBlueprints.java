package net.dark_roleplay.core_modules.blueprints;

import net.dark_roleplay.core_modules.blueprints.handler.Network;
import net.dark_roleplay.core_modules.blueprints.handler.Permissions;
import net.dark_roleplay.core_modules.blueprints.objects.tesrs.TESRBlueprintController;
import net.dark_roleplay.core_modules.blueprints.objects.tile_entities.TileEntityBlueprintController;
import net.dark_roleplay.library.sides.IProxy;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, dependencies = References.DEPENDECIES, certificateFingerprint = "893c317856cf6819b3a8381c5664e4b06df7d1cc")
public class DRPCMBlueprints{

	@SidedProxy(modId = References.MODID)
	public static IProxy proxy;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	References.init(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    	Permissions.init(event);
    	Network.init(event);
    	
    	proxy.init(event);
    }
    
	
	public static class ServerProxy implements IProxy{
		
	}
	
	@SideOnly(Side.CLIENT)
	public static class ClientProxy implements IProxy{
		
		@Override
		public void init(FMLInitializationEvent event) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlueprintController.class, new TESRBlueprintController());
		}
	}
}
