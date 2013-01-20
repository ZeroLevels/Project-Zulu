package projectzulu.common.world.structures.cemetary;

import java.util.Random;

import net.minecraft.block.Block;

import projectzulu.common.core.BoundaryPair;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.blockdataobjects.Returnable;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;
import projectzulu.common.world.structures.BlueprintHelper;

public class BlueprintCathedralWestTower extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {
		Returnable<BlockWithMeta> returnableValue = new Returnable<BlockWithMeta>();

		switch (cellIndexDirection) {
		case NorthEastCorner:{
			int diagonalIndex = cellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int verticalDisplacement = (cellSize/2-1)*0-(cellSize-2);
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);
			/* Create Ceiling */
			if(curHeight > maxHeight - cellSize*2){
				/* Ceiling Steps */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 
						BoundaryPair.createPair(1, (cellSize)*2),1,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0)));
				/* Ceiling Walls */
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2));
			}
			/* Create Outside Walls */
			if(diagonalIndex == cellSize-2){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}

			/* Create Stairs Walls */
			if(0 < rowIndex && rowIndex < 3){
				/* Rising West to East */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-colIndex-1-2,
						BoundaryPair.createPair(1,1), 1, curHeight, topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0)));
			}else if(cellSize-4 < colIndex && colIndex < cellSize-1){
				/* Rising South To North */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-rowIndex-1,
						BoundaryPair.createPair(0,1), 1, curHeight,	topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 2)));
			}
			/* Create Inner Room: Doors */
			else if(rowIndex == 3 && colIndex == cellSize-5 && curHeight == (cellSize + curHeight-1 - (curHeight+cellSize) % cellSize - cellSize+3)){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.doorWood.blockID, 9)); // 9
			}else if(rowIndex == 3 && colIndex == cellSize-5 && curHeight == (cellSize + curHeight-1 - (curHeight+cellSize) % cellSize - cellSize+2)){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.doorWood.blockID, 1)); // 1	
			}
			/* Create Inner Room: Floor */
			else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Inner Room: Walls */
			else if(colIndex < cellSize-1 && rowIndex == cellSize-1-3 || rowIndex > 0 && colIndex == cellSize-1-3){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Floor */
			if(curHeight == 0){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;
		}
		case SouthEastCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int verticalDisplacement = (cellSize/2-1)*1-(cellSize-2);
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);
			if(curHeight > maxHeight - cellSize*2){
				/* Ceiling Steps */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 
						BoundaryPair.createPair(1, (cellSize)*2),1,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0)));
				/* Ceiling Walls */
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2));
			}
			/* Create Outside Walls */
			if(diagonalIndex == cellSize-2){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Stairs Walls */
			if(cellSize-4 < rowIndex && rowIndex < cellSize-1){
				/* Rising West to East */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), colIndex,
						BoundaryPair.createPair(0,1), 1, curHeight, topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1)));
			}else if(cellSize-4 < colIndex && colIndex < cellSize-1){
				/* Rising South To North */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-rowIndex-1-2,
						BoundaryPair.createPair(1,2), 1, curHeight,	topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 2)));
			}
			/* Create Inner Room: Floor */
			else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Inner Room: Walls */
			else if(colIndex < cellSize-1 && rowIndex == cellSize-1-3 || rowIndex < cellSize-1 && colIndex == cellSize-1-3){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Floor */
			if(curHeight == 0){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;	
		}
		case SouthWestCorner:{
			int diagonalIndex = CellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int verticalDisplacement = (cellSize/2-1)*2-(cellSize-2);
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);

			if(curHeight > maxHeight - cellSize*2){
				/* Ceiling Steps */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1, 
						BoundaryPair.createPair(1, (cellSize)*2),1,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0),
						new BlockWithMeta(0)));
				/* Ceiling Walls */
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2));
			}
			/* Create Outside Walls */
			if(diagonalIndex == -(cellSize-2)){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Stairs Walls */
			if(cellSize-4 < rowIndex && rowIndex < cellSize-1){
				/* Rising West to East */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), colIndex-2,
						BoundaryPair.createPair(1,2), 1, curHeight, topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1)));
			}else if(0 < colIndex && colIndex < 3){
				/* Rising North To South */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), rowIndex,
						BoundaryPair.createPair(0,1), 1, curHeight,	topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 3)));
			}
			/* Create Inner Room: Floor */
			else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Inner Room: Walls */
			else if(colIndex > 0 && rowIndex == cellSize-1-3 || rowIndex < cellSize-1 && colIndex == cellSize-1-3){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Floor */
			if(curHeight == 0){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;	
		}
		case NorthWestCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			int colIndex = cellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int verticalDisplacement = (cellSize/2-1)*3-(cellSize-2);
			int topOfSegment = (cellSize + curHeight-1 - (curHeight-verticalDisplacement) % cellSize);

			if(curHeight > maxHeight - cellSize*2){
				/* Ceiling Steps */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1,
						BoundaryPair.createPair(1, (cellSize)*2),1,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0)));
				/* Ceiling Walls */
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2));
			}

			/* Create Outside Walls */
			if(diagonalIndex == -(cellSize-2)){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}

			/* Create Stairs */
			if(0 < rowIndex && rowIndex < 3){
				/* Rising East-West */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-colIndex-1, 
						BoundaryPair.createPair(0, 1), 1, curHeight, topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0)));
			}else if(0 < colIndex && colIndex < 3){
				/* Rising North-South */
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), rowIndex-2,
						BoundaryPair.createPair(1, 2), 1, curHeight, topOfSegment, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 3)));
			}
			/* Create Inner Wall */
			else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Inner Room: Wall */
			else if(colIndex > 0 && rowIndex == cellSize-1-3 || rowIndex > 0 && colIndex == cellSize-1-3){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			/* Create Floor at Bottom of Tower Wall */
			if(curHeight == 0){
				returnableValue.setReturnableObject(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;
		}

		default: break;
		}

		return returnableValue.isReturnablePresent() ? returnableValue.getReturnableObject() : new BlockWithMeta(0);
	}
	
	@Override
	public String getIdentifier() {
		return "west_tower";
	}

}
