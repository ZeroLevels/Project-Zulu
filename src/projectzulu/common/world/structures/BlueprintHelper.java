package projectzulu.common.world.structures;

import java.util.EnumSet;
import java.util.Set;

import net.minecraft.block.Block;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;

public class BlueprintHelper {
	
	/**
	 * Method used by Blueprints to Create a Slope
	 * @param directionIndex : integer indicating how far along the slope hte index is
	 * @param slopeSpacing : Affects the slope of the 'slope', increase decreases steepness, 0 Throw error
	 * @param curHeight : Height of the Index
	 * @param maxHeight : Height of the Cell
	 * @param cellIndex : Index of the Curent Cell
	 * @param cellSize : Size of The Cell
	 * @param fillerBlocks : varArgs That Specify if and with What block should be placed Above [0] and below [1]. Null can be passed and will be passed back as result
	 * @return Block that is Going to be Placed
	 * @return
	 */
	public static BlockWithMeta getSlopeBlock(BlockWithMeta placeableBlock, int directionIndex, int slopeSpacing, int curHeight, int maxHeight, int cellIndex, int cellSize, BlockWithMeta...fillerBlocks ){
		int distanceFromTop = maxHeight - curHeight;
		/* Create "Steps" in the Dome */
		if(directionIndex/slopeSpacing == distanceFromTop){
			return placeableBlock;
		}
		
		/* Above The Slope Blocks, Use a Filler if Provided */
		if(directionIndex/slopeSpacing > distanceFromTop && fillerBlocks.length > 0){
			return fillerBlocks[0];
		}
		
		/* At Max height, But NOT above the Placed Slope, Create a 'Roof' of StoneBrick */
		if(curHeight == maxHeight-1 && directionIndex/slopeSpacing < distanceFromTop){
			return placeableBlock;
		}		
		
		/* Below Maximum Height and 'Roof', Use a Filler if Provided */
		if(directionIndex/slopeSpacing < distanceFromTop && fillerBlocks.length > 1){
			return fillerBlocks[1];
		}
		
		return null;
	}
	
	/**
	 * Method used by Blueprints to Create a Solid Wall
	 * @param blockToBePlaced : Block to be Placed 
	 * @param curHeight : Height of the Index
	 * @param maxHeight : Height of the Cell
	 * @param cellIndex : Index of the Curent Cell
	 * @param cellSize : Size of The Cell
	 * @param wallDirection : Axis blocks will be placed in for valid see {@link CellIndexDirection#NorthWall}, {@link CellIndexDirection#SouthWall}, {@link CellIndexDirection#WestWall}, {@link CellIndexDirection#EastWall}
	 * @param wallIndex : Index indicating the correct row/column in the Cell is selected for the Wall to Exist on
	 * @return Block that is Going to be Placed
	 */
	public static BlockWithMeta getSolidWallBlock(BlockWithMeta blockToBePlaced, int curHeight, int maxHeight, int cellIndex, int cellSize, CellIndexDirection wallDirection, int... validWallIndex){
		
		switch (wallDirection) {
		case NorthWall:
		case SouthWall:
			for (int validIndex : validWallIndex) {
				if(validIndex == CellIndexDirection.getWestEastIndex(cellIndex, cellSize)){
					return blockToBePlaced;
				}
			}
			break;
		case WestWall:
		case EastWall:
			for (int validIndex : validWallIndex) {
				if(validIndex == CellIndexDirection.getNorthSouthIndex(cellIndex, cellSize)){
					return blockToBePlaced;
				}
			}
			break;
		default:
		}
		return null;
	}
	
	/**
	 * Method used by Blueprints to Create a Wall with alternateling vertical stips of two block types. 
	 * @param blockOne, blockTwo : Blocks that will Be Placed
	 * @param curHeight : Height of the Index
	 * @param maxHeight : Height of the Cell
	 * @param cellIndex : Index of the Current Cell
	 * @param cellSize : Size of The Cell
	 * @param wallDirection : Axis blocks will be placed in for valid see {@link CellIndexDirection#NorthWall}, {@link CellIndexDirection#SouthWall}, {@link CellIndexDirection#WestWall}, {@link CellIndexDirection#EastWall}
	 * @param wallIndex : Index indicating the correct row/column in the Cell is selected for the Wall to Exist on
	 * @param blockTwoReplaceIndex : Indicates the occurances where blockTwo should be placed instead of blockOne
	 * @return Block that is Going to be Placed
	 */
	public static BlockWithMeta getVerticalSlotWallBlock(BlockWithMeta blockOne, BlockWithMeta blockTwo, int curHeight, int maxHeight, 
			int cellIndex, int cellSize, CellIndexDirection wallDirection, int wallIndex, int blockTwoReplaceIndex){
		int EWIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
		int NSIndex = CellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);		
		switch (wallDirection) {
		case NorthWall:
		case SouthWall:
			if( EWIndex == wallIndex){
				return 0 == NSIndex % blockTwoReplaceIndex ? blockOne : blockTwo;
			}
			break;
		case WestWall:
		case EastWall:
			if( NSIndex == wallIndex){
				return 0 == EWIndex % blockTwoReplaceIndex ? blockOne : blockTwo;
			}
			break;
		default:
		}
		return null;	
	}
	
	
	
}
