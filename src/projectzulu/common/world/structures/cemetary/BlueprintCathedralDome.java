package projectzulu.common.world.structures.cemetary;

import java.util.Random;

import net.minecraft.block.Block;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;

public class BlueprintCathedralDome extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {
		/* Build Dome At Top of Cell */
		if(curHeight > maxHeight - cellSize){
			/* Starting From maxHeight-5, we want to Take the Edge Make it air, increase the # of diagonals that air as we rise  */
			int distanceFromTop = maxHeight - curHeight;
			int gradientDistance = maxHeight - cellSize;
			
			int diagonalIndex;
			switch (cellIndexDirection) {
			case NorthWestCorner:
				diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
				diagonalIndex -= cellSize-1;
				/* Create "Steps" in the Dome */
				if(diagonalIndex*-1/2 == distanceFromTop){
						return new BlockWithMeta(Block.stoneBrick.blockID, 0);
				}
				/* Ensure That Above the Steps There is Air  */
				if(diagonalIndex*-1/2 > distanceFromTop){
					return new BlockWithMeta(0, 0);
				}
				/* Otherwise, and at Max height, Create a Roof of StoneBrick */
				if(curHeight == maxHeight-1){
					return new BlockWithMeta(Block.stoneBrick.blockID, 0);
				}
				
				/* Create Walls For Sides That Is Not Facing Inwards */
				switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
				case NorthWall:
				case NorthEastCorner:
				case NorthMiddle:
					return cellIndexDirection.getWestEastIndex(cellIndex, cellSize) % 2 == 0 ? 
							new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
				case WestWall:
				case NorthWestCorner:
				case SouthWestCorner:
				case WestMiddle:
					return cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize) % 2 == 0 ? 
							new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
				default:
					break;
				}
				
				break;
			case SouthWestCorner:
				diagonalIndex = cellIndexDirection.getNESWDiagonalIndex(cellIndex, cellSize);
				if( diagonalIndex > 0 && diagonalIndex <= distanceFromTop){
					return new BlockWithMeta(Block.stoneBrick.blockID, 0);
				}
				break;
			case NorthEastCorner:
				diagonalIndex = cellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
				if( diagonalIndex < 0 && diagonalIndex < distanceFromTop ){
					return new BlockWithMeta(Block.glass.blockID, 2);
				}
				break;
			case SouthEastCorner:
				diagonalIndex = cellIndexDirection.getSENWDiagonalIndex(cellIndex, cellSize);
				if( diagonalIndex > 0 && diagonalIndex < distanceFromTop ){
					return new BlockWithMeta(Block.glass.blockID, 2);
				}
				break;
			default:
				break;
			}
		}else{
			switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
			case NorthWall:
			case NorthEastCorner:
			case NorthMiddle:
				return cellIndexDirection.getWestEastIndex(cellIndex, cellSize) % 2 == 0 ? 
						new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
			case WestWall:
			case NorthWestCorner:
			case SouthWestCorner:
			case WestMiddle:
				return cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize) % 2 == 0 ? 
						new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
			default:
				break;
			}
		}
		return new BlockWithMeta(0);
	}
	
	@Override
	public String getIdentifier() {
		return "dome";
	}

}
