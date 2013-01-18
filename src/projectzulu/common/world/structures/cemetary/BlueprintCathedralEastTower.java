package projectzulu.common.world.structures.cemetary;

import java.util.Random;

import net.minecraft.block.Block;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;
import projectzulu.common.world.structures.BlueprintHelper;

public class BlueprintCathedralEastTower  extends Blueprint{
	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {
		switch (cellIndexDirection) {
		case NorthWestCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize*2){
				BlockWithMeta returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1, 1, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				returnValue = returnValue != null ? returnValue : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2);
				returnValue = returnValue != null ? returnValue : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2);
				if(returnValue != null){ return returnValue; }
			}
			/* Create Outside Walls */
			if(diagonalIndex == -(cellSize-2)){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}

			/* Create Stairs Walls */
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int botOfSegment = (cellSize + curHeight - curHeight % cellSize) - cellSize;
			int verticalDisplacement = 0;
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);
//			int topOfSegment = (cellSize + curHeight - curHeight % cellSize) - cellSize/2;
			BlockWithMeta returnValue = null;
			if(0 < rowIndex && rowIndex < 3){
				/* Rising East-West */
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), colIndex, 3, cellSize-3, 1, curHeight, 
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0));
			}else if(0 < colIndex && colIndex < 3){
				/* Rising North-South */
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-rowIndex-1, 0, cellSize-4, 1, curHeight,
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0));
			}
			if(returnValue != null){ return returnValue; }
			break;
		}
		case SouthEastCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize*2){
				BlockWithMeta tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 1, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2);
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2);
				if(tempReturn != null){	return tempReturn; }
			}
			if(diagonalIndex == cellSize-2){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}

			/* Create Stairs Walls */
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int botOfSegment = 1;
			int verticalDisplacement = (cellSize/2+1)*2;
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);
			BlockWithMeta returnValue = null;
			if(cellSize-4 < rowIndex && rowIndex < cellSize-1){
				/* Rising East to West */
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-colIndex-1, 3, cellSize-3, 1, curHeight, 
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0) );
			}else if(cellSize-4 < colIndex && colIndex < cellSize-1){
				/* Rising North To South */
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), rowIndex, 0, cellSize-3, 1, curHeight,
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0) );
			}
			if(returnValue != null){ return returnValue; }
			break;	
		}
		case NorthEastCorner:{
			int diagonalIndex = cellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize*2){
				BlockWithMeta tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 1, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2);
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2);
				if(tempReturn != null){	return tempReturn; }
			}
			if(diagonalIndex == cellSize-2){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}

			/* Create Stairs Walls */
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int botOfSegment = 1;
			int verticalDisplacement = (cellSize/2+1)*3;
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);
			BlockWithMeta returnValue = null;
			if(0 < rowIndex && rowIndex < 3){
				/* Rising East to West */
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), colIndex, 0, cellSize-4, 1, curHeight, 
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0) );
			}else if(cellSize-4 < colIndex && colIndex < cellSize-1){
				/* Rising North To South */
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), rowIndex, 0, cellSize-3, 1, curHeight,
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0) );
			}
			if(returnValue != null){ return returnValue; }

			break;
		}
		case SouthWestCorner:{
			int diagonalIndex = CellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize*2){
				BlockWithMeta tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1, 1, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2);
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2);
				if(tempReturn != null){	return tempReturn; }
			}
			if(diagonalIndex == -(cellSize-2)){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}

			/* Create Stairs Walls */
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int botOfSegment = 1;
			int verticalDisplacement = (cellSize/2+1)*1;
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);
			BlockWithMeta returnValue = null;
			if(cellSize-4 < rowIndex && rowIndex < cellSize-1){
				/* Rising East to West */
				System.out.println("BLERG " + cellIndex + " " + rowIndex + " " + colIndex);
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-colIndex-1, 0, cellSize-4, 1, curHeight, 
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0) );
			}else if(0 < colIndex && colIndex < 3){
				/* Rising North To South */
				returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-rowIndex-1, 0, cellSize-3, 1, curHeight,
						botOfSegment, topOfSegment,  cellIndex, cellSize, new BlockWithMeta(Block.dirt.blockID, 0) );
			}
			if(returnValue != null){ return returnValue; }

			break;	
		}

		
		
		default: break;
		}
		return new BlockWithMeta(0);

	}
	
	private int clampHelper(int value, int min, int max){
		return Math.min(Math.max(value, min), max);
	}
	
	@Override
	public String getIdentifier() {
		return "east_tower";
	}

}
