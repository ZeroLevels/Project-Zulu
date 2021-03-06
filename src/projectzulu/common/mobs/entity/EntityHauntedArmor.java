package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityHauntedArmor extends EntityGenericAnimal implements IMob {

    protected int wakeUpTimer = 0;

    public int getWakeUpTimer() {
        return wakeUpTimer;
    }

    protected int randomDirection = 0;
    boolean shouldHover = false;

    public EntityHauntedArmor(World par1World) {
        super(par1World);
        randomDirection = rand.nextInt(16);

        this.moveSpeed = 0.2f;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

        this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
        // this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));

        // this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
        // this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Block.tallGrass.blockID, false));
        // this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
        this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        this.targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        // this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking,
        // EntityStates.looking), EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
    }

    public EntityHauntedArmor(World par1World, double parx, double pary, double parz, boolean shouldHover) {
        this(par1World);
        setLocationAndAngles(parx, pary, parz, 1, 1);
        setPosition(parx, pary, parz);
        this.shouldHover = shouldHover;
    }

    public void setPersistenceRequired(boolean persistenceRequired) {
        try {
            ObfuscationHelper.setCatchableFieldUsingReflection("field_82179_bU", EntityLiving.class, this, true, true);
        } catch (NoSuchFieldException e) {
            ObfuscationHelper.setFieldUsingReflection("persistenceRequired", EntityLiving.class, this, true, true);
        }
    }

    public void setRandomArmor(World world) {
        int number = world.rand.nextInt(2);
        switch (number) {
        case 0:
            setCurrentItemOrArmor(0, new ItemStack(Item.swordIron));
            setCurrentItemOrArmor(1, new ItemStack(Item.helmetIron));
            setCurrentItemOrArmor(2, new ItemStack(Item.plateIron));
            setCurrentItemOrArmor(3, new ItemStack(Item.legsIron));
            setCurrentItemOrArmor(4, new ItemStack(Item.bootsIron));
            break;
        case 1:
            setCurrentItemOrArmor(0, new ItemStack(Item.swordGold));
            setCurrentItemOrArmor(1, new ItemStack(Item.helmetGold));
            setCurrentItemOrArmor(2, new ItemStack(Item.plateGold));
            setCurrentItemOrArmor(3, new ItemStack(Item.legsGold));
            setCurrentItemOrArmor(4, new ItemStack(Item.bootsGold));
        }
    }

    @Override
    public void initCreature() {
        super.initCreature();
        setRandomArmor(worldObj);
    }

    @Override
    protected int getAttackStrength(World par1World) {
        ItemStack var2 = this.getHeldItem();
        int var3 = 4;
        if (var2 != null) {
            var3 += var2.getDamageVsEntity(this);
        }
        return var3;
    }

    @Override
    public int getMaxHealth() {
        return 25;
    }

    @Override
    protected String getHurtSound() {
        return "random.break";
    }

    @Override
    protected String getDeathSound() {
        return "random.break";
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        int var1 = super.getTotalArmorValue() + 2;
        if (var1 > 20) {
            var1 = 20;
        }
        return var1;
    }

    @Override
    public void onLivingUpdate() {
        if (shouldHover == true) {
            this.motionY = 0;
            if (ticksExisted > (20 * 10)) {
                shouldHover = false;
            }
        }

        super.onLivingUpdate();

        if (getEntityState() == EntityStates.idle && worldObj.getClosestPlayerToEntity(this, 5D) != null) {
            angerLevel = 400;
            wakeUpTimer = 30;
        }

        wakeUpTimer = Math.max(wakeUpTimer - 1, 0);
    }

    @Override
    protected void dropEquipment(boolean par1, int par2) {
        if (worldObj.rand.nextInt(4) == 0) {
            super.dropEquipment(par1, par2);
        }
    }
}
