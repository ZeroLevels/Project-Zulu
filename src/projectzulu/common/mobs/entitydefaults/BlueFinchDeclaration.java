package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityBlueFinch;
import projectzulu.common.mobs.models.ModelFinch;

public class BlueFinchDeclaration extends SpawnableDeclaration{
	
	public BlueFinchDeclaration(){
		super("Blue Finch", EntityBlueFinch.class, EnumCreatureType.ambient);		
		setSpawnProperties(10, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelFinch.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 1);

		eggColor1 =  (38 << 16) + (103 << 8) + 255;						eggColor2 = (224 << 16) + (233 << 8) + 255;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 
		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");	
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");		
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");		
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");		
		defaultBiomesToSpawn.add("Woodlands");	
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		super.outputDataToList(config, customMobData);
	}
}