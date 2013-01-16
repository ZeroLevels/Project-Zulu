package projectzulu.common.world.structures.cemetary;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;
import projectzulu.common.world.structures.BlueprintHelper;

public class BlueprintCathedralHallway extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {
			switch (cellIndexDirection) {
			case WestWall:{
				BlockWithMeta tempReturn = null;
				int rowIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
				if(curHeight > maxHeight - cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0), cellSize-rowIndex-1, 1, curHeight, maxHeight-cellSize/3, cellIndex, cellSize, new BlockWithMeta(0));
				}else if(curHeight > maxHeight - 2*cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-rowIndex-1, 2, curHeight, maxHeight - cellSize, cellIndex, cellSize);	
				}
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*3/10);
				if(tempReturn != null){ return tempReturn; }
				break;
			}
			case EastWall:{
				BlockWithMeta tempReturn = null;
				int rowIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
				if(curHeight > maxHeight - cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1), rowIndex, 1, curHeight, maxHeight-cellSize/3, cellIndex, cellSize, new BlockWithMeta(0));
				}else if(curHeight > maxHeight - 2*cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), rowIndex, 2, curHeight, maxHeight - cellSize, cellIndex, cellSize);
				}
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*7/10);
				if(tempReturn != null){ return tempReturn; }
				break;	
			}
			case NorthWall:{
				BlockWithMeta tempReturn = null;
				int colIndex = CellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
				if(curHeight > maxHeight - cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 2), cellSize-colIndex-1, 1, curHeight, maxHeight-cellSize/3, cellIndex, cellSize, new BlockWithMeta(0));
				}else if(curHeight > maxHeight - 2*cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-colIndex-1, 2, curHeight, maxHeight - cellSize, cellIndex, cellSize);	
				}
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize*3/10);
				if(tempReturn != null){ return tempReturn; }
				break;
			}
			case SouthWall:{
				BlockWithMeta tempReturn = null;
				int colIndex = CellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
				if(curHeight > maxHeight - cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 3), colIndex, 1, curHeight, maxHeight-cellSize/3, cellIndex, cellSize, new BlockWithMeta(0));
				}else if(curHeight > maxHeight - 2*cellSize){
					tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), colIndex, 2, curHeight, maxHeight - cellSize, cellIndex, cellSize);	
				}
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize*7/10);
				if(tempReturn != null){ return tempReturn; }
				break;
			}
			default:
				break;
		}
		return new BlockWithMeta(0);
	}
	
	@Override
	public String getIdentifier() {
		return "hallway";
	}

}
