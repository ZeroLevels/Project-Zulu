package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityCentipede extends EntityMaster implements IMob{

	public EntityCentipede(World par1World) {
		super(par1World);
		moveSpeed = 0.25f;
		setSize(0.65f, 0.5f);
		
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false, 2.5f*2.5f));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

//		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
//		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Block.tallGrass.blockID, false));
//		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 20));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
	}

	@Override
	public String getTexture() {
		texture = DefaultProps.mobDiretory + "serpent.png";
		return texture;
	}
	
	@Override
    public int getMaxHealth(){
		return 20;
	}
	
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    @Override
    protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord) {
        int var1 = xCoord;
        int var2 = yCoord;
        int var3 = zCoord;
        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > this.rand.nextInt(32)) {
            return false;
        } else {
            int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);

            if (this.worldObj.isThundering()) {
                int var5 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
                this.worldObj.skylightSubtracted = var5;
            }

            return var4 <= this.rand.nextInt(8);
        }
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }
	
	@Override
	public int getTotalArmorValue() {
		return 2;
	}
	
	@Override
	public void updateAITick() {
		super.updateAITick();
		/* Become Hostile at Night */
		float var3 = this.getBrightness(1.0F);
		if(var3 < 0.5){
			angerLevel = 120;
		}
	}
}
