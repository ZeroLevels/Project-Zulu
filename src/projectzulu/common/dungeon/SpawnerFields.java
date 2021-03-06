package projectzulu.common.dungeon;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.core.DefaultProps;

import com.google.common.base.CharMatcher;

public class SpawnerFields implements DataFields{
	private boolean isEnabled = true;
	
	private GuiTextField minSpawnDelay;
	private GuiTextField maxSpawnDelay;
	private GuiTextField maxToSpawn;
	private GuiTextField requiredPlayerRange;
	private GuiTextField maxNearbyEntities;
	
	private GuiButton toggleDebug;
	private GuiButton resetDebug;

	private GuiLimitedMobSpawner parent;
	SpawnerFields(GuiLimitedMobSpawner parent){
		this.parent = parent;
	}
	
	public DataFields createFields(Minecraft mc, int screenWidth, int screenHeight, Point backgroundSize){
		minSpawnDelay = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(177,22+2), new Point(20,14),
				minSpawnDelay != null ? minSpawnDelay.getText() : "");
		maxSpawnDelay = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(201,22+2), new Point(20,14),
				maxSpawnDelay != null ? maxSpawnDelay.getText() : "");

		requiredPlayerRange = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(201,39+2), new Point(39,14),
				requiredPlayerRange != null ? requiredPlayerRange.getText() : "");
		
		maxToSpawn = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(201,58), new Point(20,14),
				maxToSpawn != null ? maxToSpawn.getText() : "");
		maxNearbyEntities = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(201,58+17), new Point(39,14),
				maxNearbyEntities != null ? maxNearbyEntities.getText() : "");
		
		toggleDebug = new GuiButton(1,
				(screenWidth - (int)backgroundSize.getX())/2+5,
				(screenHeight - (int)backgroundSize.getY())/2+175, 70, 20, "Toggle Debug");
		resetDebug = new GuiButton(1,
				(screenWidth - (int)backgroundSize.getX())/2+151,
				(screenHeight - (int)backgroundSize.getY())/2+175, 70, 20, "Reset Debug");

		
		return this;
	}
	
	private GuiTextField setupTextField(FontRenderer fontRenderer, Point screenSize, Point backgroundSize, Point position, Point boxSize, String text){
		GuiTextField newTextField = new GuiTextField( fontRenderer,
				(screenSize.getX() - (int)backgroundSize.getX())/2+position.getX(),
				(screenSize.getY() - (int)backgroundSize.getY())/2+position.getY(),
				boxSize.getX(), boxSize.getY());
		newTextField.setText(text);
		newTextField.setTextColor(-1);
		newTextField.setDisabledTextColour(-1);
		newTextField.setEnableBackgroundDrawing(false);
		newTextField.setMaxStringLength(3);		
		return newTextField;
	}
	
	@Override
	public void loadFromTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner, int elementID){
		minSpawnDelay.setText(Integer.toString(limitedMobSpawner.getMinSpawnDelay()/20));
		maxSpawnDelay.setText(Integer.toString(limitedMobSpawner.getMaxSpawnDelay()/20));
		maxToSpawn.setText(Integer.toString(limitedMobSpawner.getMaxSpawnableEntities()));
		requiredPlayerRange.setText(Integer.toString(limitedMobSpawner.getRequriedPLayerRange()));
		maxNearbyEntities.setText(Integer.toString(limitedMobSpawner.getMaxNearbyEntities()));		
	}
	
	@Override
	public void saveToTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner){
		limitedMobSpawner.setMinMaxSpawnDelay(Integer.parseInt(minSpawnDelay.getText())*20, Integer.parseInt(maxSpawnDelay.getText())*20);
		limitedMobSpawner.setMaxSpawnableEntities(Integer.parseInt(maxToSpawn.getText()));
		limitedMobSpawner.setRequiredPlayerRange(Integer.parseInt(requiredPlayerRange.getText()));
		limitedMobSpawner.setMaxNearbyEntities(Integer.parseInt(maxNearbyEntities.getText()));		
	}
	
	public void setIsEnabled(boolean isEnabled){
		this.isEnabled = isEnabled;
	}

	public boolean isEnabled(){
		return isEnabled;
	}

	public boolean keyboardInput(char keyChar, int keyID){
		if(isEnabled){
			return correctIfInvalid(minSpawnDelay, keyChar, keyID) 
					|| correctIfInvalid(maxSpawnDelay, keyChar, keyID)
					|| correctIfInvalid(maxToSpawn, keyChar, keyID)
					|| correctIfInvalid(maxNearbyEntities, keyChar, keyID )
					|| correctIfInvalid(requiredPlayerRange, keyChar, keyID);
		}
		return false;
	}
	
	private boolean correctIfInvalid(GuiTextField guiTextField, char keyChar, int keyID){
		if(guiTextField.textboxKeyTyped(keyChar, keyID)){
			String originalString = guiTextField.getText();
			String numericString = CharMatcher.anyOf("0123456789").retainFrom(guiTextField.getText()).replaceAll("^0*", "");
			if(!originalString.equals(numericString)){
				guiTextField.setText(numericString);
			}
			if(guiTextField.getText().length() == 0 ){
				guiTextField.setText("0");
			}
			return true;
		}
		return false;
	}
	
	public void mouseClicked(GuiLimitedMobSpawner spawnerGUI, Minecraft mc, int par1, int par2, int par3){
		if(isEnabled){
			minSpawnDelay.mouseClicked(par1, par2, par3);
			maxSpawnDelay.mouseClicked(par1, par2, par3);
			maxToSpawn.mouseClicked(par1, par2, par3);
			maxNearbyEntities.mouseClicked(par1, par2, par3);
			requiredPlayerRange.mouseClicked(par1, par2, par3);
			
			if(par3 == 0 && toggleDebug.mousePressed(mc, par1, par2)){
				if(parent.limitedMobSpawner.isDebugEnabled()){
					parent.limitedMobSpawner.setDebugMode(new NBTTagCompound());
					parent.limitedMobSpawner.syncToServer();
				}else{
					NBTTagCompound tagToSave = new NBTTagCompound();
					parent.limitedMobSpawner.writeToNBT(tagToSave);
					parent.limitedMobSpawner.setDebugMode(tagToSave);
					parent.limitedMobSpawner.syncToServer();
				}
				
				mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			}	
			if(par3 == 0 && resetDebug.mousePressed(mc, par1, par2)){
				if(parent.limitedMobSpawner.isDebugEnabled()){
					parent.limitedMobSpawner.loadDebugNBT();
					parent.limitedMobSpawner.setDebugMode(new NBTTagCompound());
					parent.limitedMobSpawner.syncToServer();
					parent.loadGuiFromTileEntity();
				}				
				mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			}	
		}
	}

	public void mouseHover(int par1, int par2, int par3){
		if(isEnabled){
			
		}
	}
	
	public void render(Minecraft mc, int par1, int par2, float par3, Point screenSize, Point backgroundSize){
		if(isEnabled){
			if(parent.limitedMobSpawner.isDebugEnabled()){
				toggleDebug.enabled = false;
				toggleDebug.drawButton(mc, par1, par2);
				toggleDebug.enabled = true;
			}else{
				toggleDebug.drawButton(mc, par1, par2);
			}
			
			resetDebug.drawButton(mc, par1, par2);

			/* Draw Raw Text */
			mc.fontRenderer.drawString("Spawn Delay [Min / Max]",
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 26, 4210752); // White: 16777215
			mc.fontRenderer.drawString("Player Activation Range",
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 26+17, 4210752);
			mc.fontRenderer.drawString("Maximum To Spawn",
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 75-17, 4210752);
			mc.fontRenderer.drawString("Maximum Nearby",
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 75, 4210752);
			
			/* Draw TextBox Background Objects */
			mc.renderEngine.bindTexture(DefaultProps.dungeonDiretory+"creaturelistgui.png");
//	        int textureID = mc.renderEngine.getTexture(DefaultProps.dungeonDiretory+"creaturelistgui.png");
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//	        mc.renderEngine.bindTexture(textureID); //TODO: Commented
	        
	        Point smallBoxImageLocation = new Point(154,0);
	        Point smallBoxSize = new Point(22,14);
	        drawBackgroundBox(new Point(175,21), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        drawBackgroundBox(new Point(199,21), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        drawBackgroundBox(new Point(199,21+17), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);

	        drawBackgroundBox(new Point(199,21+17*2), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        drawBackgroundBox(new Point(199,21+17*3), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        
			/* Draw Interactive Text Boxes */
			minSpawnDelay.drawTextBox();
			maxSpawnDelay.drawTextBox();
			maxToSpawn.drawTextBox();
			maxNearbyEntities.drawTextBox();
			requiredPlayerRange.drawTextBox();	
		}
	}
	
	private void drawBackgroundBox(Point position, Point screenSize, Point backgroundSize, Point imageLocation, Point imageSize){
        int xCoord = (screenSize.getX() - backgroundSize.getX()) / 2+position.getX();
        int yCoord = (screenSize.getY() - backgroundSize.getY()) / 2+position.getY();
        this.drawTexturedModalRect(xCoord, yCoord, imageLocation.getX(), imageLocation.getY(), imageSize.getX(), imageSize.getY());
	}
	
	/**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6){
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        float zLevel = 0;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
        var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
        var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
        var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
        var9.draw();
    }

}
