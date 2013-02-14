package projectzulu.common.mobs.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import cpw.mods.fml.common.Loader;

public class EntityBrownBear extends EntityBear{

	public EntityBrownBear(World par1World) {
		super(par1World);
		setSize(1.5f, 2.1f);
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
	}
	
	/** 
	 * Set Entity Attack Strength
	 * This is overriden by each Entity if deviations from default are desired
	 **/
	@Override
	protected int getAttackStrength(World par1World){
		switch (par1World.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 4; 
		case 2:
			return 5; 
		case 3:
			return 7; 
		default:
			return 3;
		}
	}
	
	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "bearbrown.png";
		return super.getTexture();
	}

	@Override
	public int getMaxHealth(){return 20;}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue(){return 4;}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.brownBear.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.brownBear.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.brownBear.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}
	
	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,4), 1);
		}
		super.dropRareDrop(par1);
	}
}