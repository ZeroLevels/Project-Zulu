package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTumbleweed extends Block
{
	boolean prepareToSummonBoss = false;

	public BlockTumbleweed(int par1)
    {
        super(par1, Material.plants);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(0.7F);
        setStepSound(Block.soundWoodFootstep);
    }
	
    @SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return DefaultProps.blockSpriteSheet;
    }

	@Override
    public int quantityDropped(Random random){
		return 1;
	}

	@Override
    public int idDropped(int i, Random random, int j){
		
		return this.blockID;
	}

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	super.onBlockAdded(par1World, par2, par3, par4);
    }
    
}
