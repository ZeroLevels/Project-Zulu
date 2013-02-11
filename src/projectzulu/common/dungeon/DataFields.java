package projectzulu.common.dungeon;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import org.lwjgl.util.Point;

public interface DataFields {
		
	public abstract boolean isEnabled();
	public abstract void setIsEnabled(boolean isEnabled);
	public abstract DataFields createFields(int rowIndex, FontRenderer fontRenderer, int screenWidth, int screenHeight, Point backgroundSize);
	public abstract void loadFromTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner);
	public abstract void saveToTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner);
	
	public abstract boolean keyboardInput(char keyChar, int keyID);
	public abstract void mouseClicked(GuiLimitedMobSpawner spawnerGUI, Minecraft mc, int par1, int par2, int par3);
	public abstract void mouseHover(int par1, int par2, int par3);
	public abstract void render(Minecraft mc, int par1, int par2, float par3, Point screenSize, Point backgroundSize);
	
}
