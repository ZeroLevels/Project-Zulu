package projectzulu.common.blocks;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.DefaultProps;

public class GuiContainerUniversalFlowerPot extends GuiContainer{

	public GuiContainerUniversalFlowerPot (InventoryPlayer inventoryPlayer, TileEntityUniversalFlowerPot tileEntity) {
		super(new ContainerUniversalFlowerPot(inventoryPlayer, tileEntity));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Flower Pot", 8, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(DefaultProps.coreDiretory + "gui/FlowerPotGUI.png");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
