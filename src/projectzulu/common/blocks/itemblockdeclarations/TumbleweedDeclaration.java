package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockTumbleweed;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TumbleweedDeclaration extends BlockDeclaration {

    public TumbleweedDeclaration() {
        super("Tumbleweed");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.tumbleweed = Optional.of((new BlockTumbleweed(iD)).setUnlocalizedName(DefaultProps.blockKey + ":"
                + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.tumbleweed.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        LanguageRegistry.addName(block, "Tumbleweed");
    }
}
