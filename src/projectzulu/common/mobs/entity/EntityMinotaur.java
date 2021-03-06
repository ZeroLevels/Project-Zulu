package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityMinotaur extends EntityGenericAnimal implements IMob{	

	/* General Abilite Variabeles */
	int ticksToCheckAbilities = 3;

	/* Charge Variables */
	protected boolean isCharging = false;
	protected float timeSinceLastCharge = 5f;
	protected float chargeTriggerThreshold = 10f*20f + rand.nextInt(10*20);
	protected float chargeTime = 0.2f*chargeTriggerThreshold;
	protected int chargeSpeedModifier = 2;
	
	public EntityMinotaur(World par1World){
		super(par1World);

		this.myEntitySize = EnumEntitySize.SIZE_6;
		setSize(1.0f, 2.4f);
		experienceValue = 7;
//		chargeSpeedModifier = 2;
		
		this.moveSpeed = 0.25f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		
		this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false, 2.3f*2.3f));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

//		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
//		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Block.tallGrass.blockID, false));
//		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
	}
	
	@Override
	protected int getAttackStrength(World par1World) {
		if(par1World == null){
			return 4;
		}
		switch (par1World.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 3; 
		case 2:
			return 4; 
		case 3:
			return 6; 
		default:
			return 4;
		}
	}

	@Override
	public String getTexture(){
		this.texture = DefaultProps.mobDiretory + "minotaur.png";
		return super.getTexture();
	}

	@Override
	public int getMaxHealth(){ return 30; }

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue(){return 6;}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.worldObj.playSoundAtEntity(this, "mob.cow.step", 0.15F, 1.0F);
	}
	
	@Override
	protected void updateAITick() {
		this.angerLevel = 100;
		super.updateAITick();
	}
	
	@Override
	public void onLivingUpdate(){
		this.isInWeb = false;
		
		
		if(ticksExisted % ticksToCheckAbilities == 0){
			/* Check If Entity Should START Charging */
			if(this.timeSinceLastCharge > chargeTriggerThreshold && !isCharging){// && targetedEntity != null && this.getDistanceToEntity(targetedEntity) < 20.0f){
				this.isCharging = true;
				this.timeSinceLastCharge = 0;
				chargeTriggerThreshold = 10f*20f + rand.nextInt(10*20);
			}

			/* Check If Entity Should STOP Charging */
			if(isCharging && this.timeSinceLastCharge > chargeTime){
				this.isCharging = false;
			}

			/* Increase Time Since Last Charge */
			this.timeSinceLastCharge += ticksToCheckAbilities;
		}
		
		super.onLivingUpdate();
	}
	
	@Override
	public float getSpeedModifier() {
		float var1 = super.getSpeedModifier();
		if(isCharging){
			var1 *= chargeSpeedModifier;
		}
		return super.getSpeedModifier();
	}
}