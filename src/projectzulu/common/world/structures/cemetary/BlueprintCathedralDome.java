package projectzulu.common.world.structures.cemetary;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import com.google.common.base.Optional;

import net.minecraft.block.Block;

import projectzulu.common.core.BoundaryPair;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.blockdataobjects.Returnable;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;
import projectzulu.common.world.structures.BlueprintHelper;

public class BlueprintCathedralDome extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {
		Returnable<BlockWithMeta> returnableValue = new Returnable<BlockWithMeta>();		
		switch (cellIndexDirection) {
		case NorthWestCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1, 
						BoundaryPair.createPair(cellSize-1-3, (cellSize)*2),2,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0),
						new BlockWithMeta(0)));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2));
			}
			if( diagonalIndex == -(cellSize-2) ){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;

		}
		case SouthEastCorner:{
			int diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 
						BoundaryPair.createPair(cellSize-1-3, (cellSize)*2),2,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0),
						new BlockWithMeta(0)));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2));
			}
			if(diagonalIndex == cellSize-2){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;	
		}
		case NorthEastCorner:{
			int diagonalIndex = cellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize+diagonalIndex-1, 
						BoundaryPair.createPair(cellSize-1-3, (cellSize)*2),2,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0),
						new BlockWithMeta(0)));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize-1, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, 0, 2));
			}
			if(diagonalIndex == cellSize-2){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;
		}
		case SouthWestCorner:{
			int diagonalIndex = CellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-diagonalIndex-1, 
						BoundaryPair.createPair(cellSize-1-3, (cellSize)*2),2,curHeight, maxHeight, new BlockWithMeta(Block.stoneBrick.blockID, 0),
						new BlockWithMeta(0)));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, 0, 2));
				returnableValue.acceptIfEmpty(BlueprintHelper.getVerticalSlotWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), new BlockWithMeta(0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-1, 2));
			}
			if(diagonalIndex == -(cellSize-2)){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;	
		}
		default:
			break;
		}
		return returnableValue.isReturnablePresent() ? returnableValue.getReturnableObject() : new BlockWithMeta(0);
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
