package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAISmoothSwimming;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityCrocodile extends EntityGenericAnimal{	
	public EntityCrocodile(World par1World){
		super(par1World);
		this.myEntitySize = EnumEntitySize.SIZE_6;
		setSize(1.7f, 0.9f);
		
		this.moveSpeed = 0.25f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISmoothSwimming(this, true));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.chickenRaw.itemID, false));
		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
	}

	@Override
	protected int getAttackStrength(World par1World) {
		switch (par1World.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 4; 
		case 2:
			return 5; 
		case 3:
			return 6; 
		default:
			return 3;
		}
	}

	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "crocodile.png";
		return super.getTexture();
	}

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){ return "sounds.crocoodileliving"; }
	
	@Override
    public float getSpeedModifier() {
		float var1 = super.getSpeedModifier();
		if(isInWater()){
			var1 *= 2.0f;
		}
		return super.getSpeedModifier();
	};
	
	@Override
	protected void updateAITick() {
		if( this.isInWater() ){
			this.angerLevel = 400 + this.rand.nextInt(400);
		}
		super.updateAITick();
	}

	@Override
	protected int decreaseAirSupply(int par1) {
		return par1;
	}

	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if(itemStack != null && itemStack.getItem().itemID == Item.chickenRaw.itemID){
			return true;
		}else{
			return super.isValidBreedingItem(itemStack);
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,1), 1);
		}
		super.dropRareDrop(par1);
	}
}