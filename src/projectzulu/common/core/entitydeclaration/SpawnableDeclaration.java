package projectzulu.common.core.entitydeclaration;

import java.util.ArrayList;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.common.registry.EntityRegistry;

public abstract class SpawnableDeclaration extends EggableDeclaration{

	protected int spawnRate;
	protected boolean useGlobalSpawn = false;
	protected int secondarySpawnRate;
	protected int minInChunk;
	protected int maxInChunk;
	protected EnumCreatureType spawnType;

	protected ArrayList<String> defaultBiomesToSpawn = new ArrayList<String>();	
	ArrayList<SpawnEntry> biomesToSpawn = new ArrayList<SpawnEntry>();
	
	protected SpawnableDeclaration(String mobName, Class mobClass, EnumCreatureType creatureType) {
		super(mobName, mobClass, creatureType);
		spawnType = creatureType;
	}
	
	protected void setSpawnProperties(int spawnRate, int secondarySpawnRate, int minInChunk, int maxInChunk){
		this.spawnRate = spawnRate;
		this.secondarySpawnRate = secondarySpawnRate;
		this.minInChunk = minInChunk;
		this.maxInChunk = maxInChunk;
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {
		super.loadCreaturesFromConfig(config);
		spawnRate = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" SpawnRate", spawnRate).getInt(spawnRate);
		secondarySpawnRate = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" SecondarySpawnRate",secondarySpawnRate).getInt(secondarySpawnRate);
		minInChunk = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" minInChunk", minInChunk).getInt(minInChunk);
		maxInChunk = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" maxInChunk", maxInChunk).getInt(maxInChunk);
		spawnType = ConfigHelper.configGetCreatureType(config, "MOB CONTROLS."+mobName, "Spawn List Type", spawnType);
		
		useGlobalSpawn = config.get("MOB CONTROLS."+mobName, "Use Global Spawn Rates", useGlobalSpawn).getBoolean(useGlobalSpawn);
	}
	
	@Override
	public void loadBiomesFromConfig(Configuration config) {
		for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
			if(BiomeGenBase.biomeList[i] == null){
				continue;
			}
            SpawnEntry spawnEntry = ConfigHelper.configGetSpawnEntry(config, "Mob Spawn Biome Controls." + mobName, BiomeGenBase.biomeList[i],
                    defaultBiomesToSpawn.contains(BiomeGenBase.biomeList[i].biomeName), spawnRate, minInChunk,
                    maxInChunk);
            if(spawnEntry != null){
                biomesToSpawn.add(spawnEntry);
            }
		}
	}
	
	/* Create loadCustomMobData() method which calls outputData to List. loadCustom contains calls that are the same for all creatures */
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData){
	    super.outputDataToList(config, customMobData);
	    customMobData.secondarySpawnRate = secondarySpawnRate;
	    customMobData.spawnType = spawnType;
	}
	
	@Override
	public void addSpawn() {
		if(spawnType == null){
			return;
		}
		
		for (int i = 0; i < biomesToSpawn.size(); i++){
		    if(useGlobalSpawn){
                EntityRegistry.addSpawn(mobClass, spawnRate, minInChunk, maxInChunk, spawnType,
                        biomesToSpawn.get(i).biome);
		    }else{
	            EntityRegistry.addSpawn(mobClass, biomesToSpawn.get(i).spawnRate, biomesToSpawn.get(i).minInChunk,
	                    biomesToSpawn.get(i).maxInChunk, spawnType, biomesToSpawn.get(i).biome);
		    }
			if(reportSpawningInLog){
                ProjectZuluLog.info("Registering %s to biome %s at rates of %s,%s,%s", mobClass.getSimpleName(),
                        biomesToSpawn.get(i).biome.biomeName, biomesToSpawn.get(i).spawnRate,
                        biomesToSpawn.get(i).minInChunk, biomesToSpawn.get(i).maxInChunk);
			}
		}
	}
}
