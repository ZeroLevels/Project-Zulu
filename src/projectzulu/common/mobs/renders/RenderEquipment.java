package projectzulu.common.mobs.renders;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.models.ModelHauntedArmor;

public class RenderEquipment extends RenderGenericLiving {

    ModelHauntedArmor hauntedModel;

    public RenderEquipment(ModelBase modelBase, float shadowSize) {
        super(modelBase, shadowSize);
        hauntedModel = (ModelHauntedArmor) modelBase;
    }

    @Override
    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
        renderItemInHand(par1EntityLiving);
    }

    private void renderItemInHand(EntityLiving par1EntityLiving) {
        ItemStack var3 = par1EntityLiving.getHeldItem();
        if (var3 != null) {
            GL11.glPushMatrix();
            hauntedModel.swordhand.postRender(0.0625F);
            float var4;

            if (var3.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType())) {
                var4 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                var4 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
            } else if (var3.itemID == Item.bow.itemID) {
                var4 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (Item.itemsList[var3.itemID].isFull3D()) {
                var4 = 0.625F;
                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                var4 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(var4, var4, var4);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }
            this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, 0);

            if (var3.getItem().requiresMultipleRenderPasses()) {
                this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, 1);
            }
            GL11.glPopMatrix();
        }
    }
}
