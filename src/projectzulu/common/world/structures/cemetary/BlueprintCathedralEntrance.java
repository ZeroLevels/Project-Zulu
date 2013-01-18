package projectzulu.common.world.structures.cemetary;

import java.util.Random;

import net.minecraft.block.Block;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;
import projectzulu.common.world.structures.BlueprintHelper;

public class BlueprintCathedralEntrance extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection) {
		switch (cellIndexDirection) {
		case SouthWestCorner:
		case NorthWestCorner:{
			BlockWithMeta returnValue = null;
			int rowIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0), cellSize-rowIndex-1, 1, curHeight, maxHeight-cellSize/3, cellIndex, cellSize, new BlockWithMeta(0));
			}else if(curHeight > maxHeight - 2*cellSize){
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-rowIndex-1, 2, curHeight, maxHeight - cellSize, cellIndex, cellSize);	
			}
			returnValue = returnValue != null ? returnValue : BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*3/10);
			if(rowIndex > 1 && returnValue == null){
				returnValue = BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-2);
			}
			if(returnValue != null){ return returnValue; }
			break;
		}
		case SouthEastCorner:
		case NorthEastCorner:{
			BlockWithMeta returnValue = null;
			int rowIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1), rowIndex, 1, curHeight, maxHeight-cellSize/3, cellIndex, cellSize, new BlockWithMeta(0));
			}else if(curHeight > maxHeight - 2*cellSize){
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), rowIndex, 2, curHeight, maxHeight - cellSize, cellIndex, cellSize);
			}
			returnValue = returnValue != null ? returnValue : BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*7/10);
			if(rowIndex < cellSize - 3 && returnValue == null){
				returnValue = BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-2);
			}
			if(returnValue != null){ return returnValue; }
			break;
		}			
		default: break;
		}
		return new BlockWithMeta(0);
	}
	
	@Override
	public String getIdentifier() {
		return "entrance";
	}
}
