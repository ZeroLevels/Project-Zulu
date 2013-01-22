package projectzulu.common.world.structures.cemetary;

import java.util.Random;

import net.minecraft.block.Block;

import projectzulu.common.core.BoundaryPair;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.blockdataobjects.Returnable;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.Blueprint;
import projectzulu.common.world.structures.BlueprintHelper;

public class BlueprintCathedralEntrance extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {
		Returnable<BlockWithMeta> returnableValue = new Returnable<BlockWithMeta>();
		/* Create Ceiling */
		switch (cellIndexDirection) {
		case NorthWestCorner:{
			int colIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			/* Top Ceiling Roof */
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0), cellSize-colIndex-1, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 1, curHeight, maxHeight-cellSize/3, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0),
						new BlockWithMeta(0)));
			}
			/* Secondary Ceiling Roof */
			else if(curHeight > maxHeight - 2*cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-colIndex-1, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 2, curHeight, maxHeight - cellSize, new BlockWithMeta(Block.stoneBrick.blockID, 0)));
			}
			
			/* West Wall */
			if(curHeight+1 < cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*3/10));
			}
			/* Create Floor, Ground and Balcony*/
			if(curHeight == 0){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;
		}
		case SouthWestCorner:{
			int colIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			/* Top Ceiling Roof */
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0), cellSize-colIndex-1, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 1, curHeight, maxHeight-cellSize/3, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0),
						new BlockWithMeta(0)));
			}
			/* Secondary Ceiling Roof */
			else if(curHeight > maxHeight - 2*cellSize && ((colIndex > 1 && rowIndex < cellSize-3) || rowIndex < cellSize-2)){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), cellSize-colIndex-1, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 2, curHeight, maxHeight - cellSize, new BlockWithMeta(Block.stoneBrick.blockID, 0)));
			}
			/* West Wall */
			returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0),
					curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*3/10));
			
			/* Outside 'Front' Wall */
			if(colIndex > 1){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-3));
			}else{
				returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0),
						curHeight, maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-2));
			}
			
			/* Create Floor, Ground and Balcony*/
			if(curHeight == 0){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;
		}		
		case SouthEastCorner:{
			int colIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			/* Top Ceiling Roof*/
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1), colIndex, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 1, curHeight, maxHeight-cellSize/3, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1),
						new BlockWithMeta(0)));
			}
			/* Secondary Ceiling Roof */
			else if(curHeight > maxHeight - 2*cellSize && ( (colIndex<cellSize-3 && rowIndex < cellSize-3) || rowIndex < cellSize-2)){ //|
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), colIndex, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 2, curHeight, maxHeight - cellSize, new BlockWithMeta(Block.stoneBrick.blockID, 0)));
			}
			/* East Wall */
			returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight,
					cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*7/10));
			/* Outside 'Front' Wall */
			if(colIndex < cellSize - 3){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight,
						maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-3));
			}else{
				returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight,
						maxHeight, cellIndex, cellSize, CellIndexDirection.WestWall, cellSize-2));
			}
			
			/* Create Floor, Ground and Balcony*/
			if(curHeight == 0 && ((colIndex<cellSize-3 && rowIndex < cellSize-3) || rowIndex < cellSize-2)){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize 
					&& ( (colIndex<cellSize-3 && rowIndex < cellSize-3) || rowIndex < cellSize-2)){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;
		}
		case NorthEastCorner:{
			int colIndex = CellIndexDirection.getWestEastIndex(cellIndex, cellSize);
			int rowIndex = cellIndexDirection.getNorthSouthIndex(cellIndex, cellSize);
			/* Top Ceiling Roof*/
			if(curHeight > maxHeight - cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1), colIndex, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 1, curHeight, maxHeight-cellSize/3, new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1),
						new BlockWithMeta(0)));
			}
			/* Secondary Ceiling Roof */
			else if(curHeight > maxHeight - 2*cellSize){
				returnableValue.acceptIfEmpty(BlueprintHelper.getSlopeBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), colIndex, 
						BoundaryPair.createPair(0, (cellSize-1)*2), 2, curHeight, maxHeight - cellSize, new BlockWithMeta(Block.stoneBrick.blockID, 0)));
			}
			/* East Wall */
			returnableValue.acceptIfEmpty(BlueprintHelper.getSolidWallBlock(new BlockWithMeta(Block.stoneBrick.blockID, 0), curHeight, maxHeight,
					cellIndex, cellSize, CellIndexDirection.NorthWall, cellSize*7/10));
			
			/* Create Floor, Ground and Balcony*/
			if(curHeight == 0){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}else if(curHeight+1 == cellSize + curHeight-1 - (cellSize + curHeight-2) % cellSize){
				returnableValue.acceptIfEmpty(new BlockWithMeta(Block.stoneBrick.blockID, 0));
			}
			break;
		}		
		default: break;
		}		
		return returnableValue.isReturnablePresent() ? returnableValue.getReturnableObject() : new BlockWithMeta(0);
	}
	
	@Override
	public String getIdentifier() {
		return "entrance";
	}
}
