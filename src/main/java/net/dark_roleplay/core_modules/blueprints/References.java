package net.dark_roleplay.core_modules.blueprints;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class References {
	public static final String MODID = "drpcmblueprints";
	public static final String NAME = "Dark Roleplay Core Module Blueprints";
	public static final String VERSION = "1.12.2-1.0.0";
	public static final String ACCEPTEDVERSIONS = "[1.12.2,1.13)";
	public static final String UPDATE_JSON = "";
	public static final String DEPENDECIES = "required-after:forge@[1.12.2-14.23.4.2705,);required-after:drplibrary@[1.12.2-0.1.0.5,)";
	public static final String URL = "http://dark-roleplay.net/";
	public static final List<String> AUTHORS = Arrays.asList("JTK222");
	public static final String CREDITS = "Lead Programmer: JTK222";
	public static final String DESCRIPTION = "Dark Roleplay Core Modules are included into Dark Roleplay Core, they are used for easier modulation and updaing.";
	
	public static Logger LOGGER;

	public static File FOLDER_CONFIGS;
	public static File FOLDER_MAIN;
	public static File FOLDER_BLUEPRINTS;
	
	public static void init(FMLPreInitializationEvent event){
		References.LOGGER = LogManager.getLogger(References.MODID);
		
		ModMetadata meta = event.getModMetadata();
		meta.autogenerated = false;
		meta.modId = MODID;
		meta.name = NAME;
		meta.authorList = AUTHORS;
		meta.credits = CREDITS;
		meta.description = DESCRIPTION;
		meta.version = VERSION;
		meta.url = URL;
		meta.updateJSON = UPDATE_JSON;
		meta.parent = "drpcore";
		
		//Folders
		FOLDER_CONFIGS = event.getModConfigurationDirectory();
		
		FOLDER_MAIN = new File(event.getModConfigurationDirectory().getParentFile().getPath() + "/dark roleplay/");
		FOLDER_MAIN.mkdirs();

		FOLDER_BLUEPRINTS = new File(FOLDER_MAIN.getPath() + "/core/modules/blueprints/");
		FOLDER_BLUEPRINTS.mkdirs();
	}
}