package projectzulu.common.mobs.entityai;

import java.util.EnumSet;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.mobs.entity.EntityGenericCreature;
import projectzulu.common.mobs.entity.EntityStates;

/** This AI is Compatible with Only Flying Entities: Designed Specifically for Vulture */
public class EntityAIVultureFollow extends EntityAIBase
{
    World worldObj;
    EntityGenericCreature attacker;
    EntityLiving entityTarget;

    /**
     * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
     */
    int attackTick;
    float moveSpeed;
    boolean continuousPathing;

    /** The PathEntity of our entity. */
    PathEntity entityPathEntity;
    Class classTarget;
    private int changeDirectionCooldown;
    
    float attackDistanceSq;
    int heightToFollow = 10;
    private EnumSet<EntityStates> setOfValidStates = EnumSet.allOf(EntityStates.class);
    
    public EntityAIVultureFollow(EntityGenericCreature par1EntityLiving, Class par2Class, float par3, boolean par4, float attackDistanceSq) {
        this.attackTick = 0;
        this.attacker = par1EntityLiving;
        this.worldObj = par1EntityLiving.worldObj;
        this.moveSpeed = par3;
        this.continuousPathing = par4;
        this.setMutexBits(3);
        
        this.classTarget = par2Class;
        this.attackDistanceSq = attackDistanceSq;
    }
    
    public EntityAIVultureFollow(EntityGenericCreature par1EntityLiving, Class par2Class, float par3, boolean par4) {
        this(par1EntityLiving, par2Class, par3, par4, par1EntityLiving.width * 2.0F * par1EntityLiving.width * 2.0F);
    }
    
    public EntityAIVultureFollow(EntityGenericCreature par1EntityLiving, float par2, boolean par3) {
    	this(par1EntityLiving, null, par2, par3);
    }
    public EntityAIVultureFollow(EntityGenericCreature par1EntityLiving, float par2, boolean par3, float attackDistanceSq ) {
        this(par1EntityLiving, null, par2, par3, attackDistanceSq);
    }
    
    public EntityAIVultureFollow setValidStates(EnumSet<EntityStates> setOfValidStates){
        this.setOfValidStates = setOfValidStates;
        return this;
    }
    
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {    	
        EntityLiving var1 = this.attacker.getAttackTarget();
        
        if(!setOfValidStates.contains(attacker.getEntityState())){
        	return false;
        }
        
        if (var1 == null){
            return false;
        }
        else if (this.classTarget != null && !this.classTarget.isAssignableFrom(var1.getClass())){
            return false;
        }
        else{
            this.entityTarget = var1;
            if(attacker.isEntityGrounded()){
                this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(this.entityTarget);
                return this.entityPathEntity != null;
            }else{
                return this.attacker.setTargetPosition(new ChunkCoordinates((int)entityTarget.posX, (int)entityTarget.posY+heightToFollow, (int)entityTarget.posZ));
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        EntityLiving var1 = this.attacker.getAttackTarget();
        if(var1 == null || !this.entityTarget.isEntityAlive()){
        	return false;
        }else if(!this.continuousPathing){
        	if(attacker.isEntityGrounded()){
            	return !this.attacker.getNavigator().noPath();
        	}else{
            	return !attacker.atTargetPosition() && attacker.isTargetPositionValid();
        	}
        }else{
            return this.attacker.isWithinHomeDistance(
            		MathHelper.floor_double(this.entityTarget.posX), 
            		MathHelper.floor_double(this.entityTarget.posY), 
            		MathHelper.floor_double(this.entityTarget.posZ));
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        if(attacker.isEntityGrounded()){
            this.attacker.getNavigator().setPath(this.entityPathEntity, this.moveSpeed);
        }else{
            this.attacker.setTargetPosition(new ChunkCoordinates((int)entityTarget.posX, (int)(entityTarget.posY+heightToFollow), (int)entityTarget.posZ));
        }
        this.changeDirectionCooldown = 0;

    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.entityTarget = null;
        this.attacker.getNavigator().clearPathEntity();
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        this.attacker.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
        
        /* Check if Entity can See Target and if ChangeDirectionCooldown is down so we should Search for a new Path */
        if ((this.continuousPathing || this.attacker.getEntitySenses().canSee(this.entityTarget)) && --this.changeDirectionCooldown <= 0) {
            this.changeDirectionCooldown = 4 + this.attacker.getRNG().nextInt(7);
            if(attacker.isEntityGrounded()){
                this.attacker.getNavigator().tryMoveToEntityLiving(this.entityTarget, this.moveSpeed);
            }else{
                this.attacker.setTargetPosition(new ChunkCoordinates((int)entityTarget.posX, (int)(entityTarget.posY+heightToFollow), (int)entityTarget.posZ));
            }
        }
        

        /* Decrement attackTick and Try to Attack if Target is Close Enough*/
        this.attackTick = Math.max(this.attackTick - 1, 0);
        double var1 = (attackDistanceSq);        
        if (this.attacker.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) <= var1) {
            if (this.attackTick <= 0)
            {
                this.attackTick = 20;

                if (this.attacker.getHeldItem() != null)
                {
                    this.attacker.swingItem();
                }

                this.attacker.attackEntityAsMob(this.entityTarget);
            }
        }
    }
}
