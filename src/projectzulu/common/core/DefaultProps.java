package projectzulu.common.core;


public class DefaultProps {
	
	/* ModIDs, Dependencies, and Version */
	public static final String DesiredBefore = "after:ExtrabiomesXL@"; // TODO: Experimental, Needs to BE Tested
	public static final String VERSION_STRING = "0.6.4d";
	public static final String CoreModId = "ProjectZulu|Core";
	public static final String BlocksModId = "ProjectZulu|Blocks";
	public static final String MobsModId = "ProjectZulu|Mobs";
	public static final String WorldModId = "ProjectZulu|World";
	public static final String DungeonModId = "ProjectZulu|Dungeon";
	public static final String DEPENDENCY_CORE = "required-after:ProjectZulu|Core@";	
	
	/* Packet Channels */
	public static final String defaultChannel = "Channel_Zulu";
	
	/* Module Resource Directories */
	public static final String coreDiretory = "/projectzuluresources/module_core/";
	public static final String mobDiretory = "/projectzuluresources/module_mob/";
	public static final String blockDiretory = "/projectzuluresources/module_block/";
	public static final String worldDiretory = "/projectzuluresources/module_world/";
	public static final String dungeonDiretory = "/projectzuluresources/module_dungeon/";

	/* Common Resource Directories */
	public static final String blockSpriteSheet = blockDiretory + "blocks_projectzulu.png";
	public static final String itemSpriteSheet = blockDiretory + "items_projectzulu.png";
	
	/* Config Directories */
	public static final String configDirectory = "/Project Zulu/";
	public static final String customResourcesDirectory = "CustomResources/";
	public static final String streamingResourcesDirectory = "Streaming/";
	public static final String soundResourcesDirectory = "Sound/"; //TODO: Each Sound Type should probably have its own folder
	public static final String defaultConfigFile = "ProjectZuluConfig.cfg";
	public static final String tempConfigFile = "tempSettingsConfig.cfg";
	public static final String mobBiomeSpawnConfigFile = "ProjectZuluMobBiomeConfig.cfg";
	public static final String structureBiomeConfigFile = "ProjectZuluStructureBiomeConfig.cfg";
	
}