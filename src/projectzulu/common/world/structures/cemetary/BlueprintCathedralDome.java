package projectzulu.common.world.structures.cemetary;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;
import projectzulu.common.world.structures.BlueprintHelper;

public class BlueprintCathedralDome extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {		
		/* Build Dome At Top of Cell */
		/* Starting From maxHeight-5, we want to Take the Edge Make it air, increase the # of diagonals that air as we rise */
		int distanceFromTop = maxHeight - curHeight;
		int gradientDistance = maxHeight - cellSize;

		switch (cellIndexDirection) {
		case NorthWestCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				BlockWithMeta returnValue = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1, 2, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				returnValue = returnValue != null ? returnValue : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2);
				returnValue = returnValue != null ? returnValue : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2);
				if(returnValue != null){ return returnValue; }
			}
			if(diagonalIndex == -(cellSize-2)){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}
			break;

		}
		case SouthEastCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				BlockWithMeta tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 2, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2);
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2);
				if(tempReturn != null){	return tempReturn; }
			}
			if(diagonalIndex == cellSize-2){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}
			break;	
		}
		case NorthEastCorner:{
			int diagonalIndex = cellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				BlockWithMeta tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 2, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2);
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2);
				if(tempReturn != null){	return tempReturn; }
			}
			if(diagonalIndex == cellSize-2){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}
			break;
		}
		case SouthWestCorner:{
			int diagonalIndex = CellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				BlockWithMeta tempReturn = BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1, 2, curHeight, maxHeight, cellIndex, cellSize, new BlockWithMeta(0));
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2);
				tempReturn = tempReturn != null ? tempReturn : BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0), curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2);
				if(tempReturn != null){	return tempReturn; }
			}
			if(diagonalIndex == -(cellSize-2)){
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}
			break;	
		}
		default:
			break;
		}
		return new BlockWithMeta(0);
	}

	private BlockWithMeta buildDome(int diagonalIndex, int curHeight, int maxHeight, int cellIndex, int cellSize, EnumSet<CellIndexDirection> validWalls){
		int distanceFromTop = maxHeight - curHeight;

		/* Create "Steps" in the Dome */
		if(diagonalIndex/2 == distanceFromTop){
			return new BlockWithMeta(Block.stoneBrick.blockID, 0);
		}
		/* Ensure That Above the Steps There is Air  */
		if(diagonalIndex/2 > distanceFromTop){
			return new BlockWithMeta(0, 0);
		}
		/* Otherwise, and at Max height, Create a Roof of StoneBrick */
		if(curHeight == maxHeight-1){
			return new BlockWithMeta(Block.stoneBrick.blockID, 0);
		}

		/* Create Walls For Sides That Is Not Facing Inwards */
		Set<CellIndexDirection> actualCellWalls = CellIndexDirection.calcIfWall(cellIndex, cellSize);
		for (CellIndexDirection cellValidWall : validWalls) {
			if(actualCellWalls.contains(cellValidWall)) {
				switch (cellValidWall) {
				case NorthWall:
				case SouthWall:
					return CellIndexDirection.getWestEastIndex(cellIndex, cellSize) % 2 == 0 ? 
							new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
				case EastWall:
				case WestWall:
					return CellIndexDirection.getNorthSouthIndex(cellIndex, cellSize) % 2 == 0 ? 
							new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
				default:
					break;
				}
			}
		}
		return null;
	}

	@Override
	public String getIdentifier() {
		return "dome";
	}

}
