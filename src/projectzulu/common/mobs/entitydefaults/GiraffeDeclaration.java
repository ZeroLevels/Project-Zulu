package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityGiraffe;
import projectzulu.common.mobs.models.ModelGiraffe;

public class GiraffeDeclaration extends SpawnableDeclaration{
	
	public GiraffeDeclaration(){
		super("Giraffe", EntityGiraffe.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelGiraffe.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 3);

		eggColor1 =  (239 << 16) + (228 << 8) + 109;					eggColor2 = (91 << 16) + (87 << 8) + 41;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		
		defaultBiomesToSpawn.add("Savanna");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.beefRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.BlackLichen.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS."+mobName, customMobData);
		super.outputDataToList(config, customMobData);
	}
}
