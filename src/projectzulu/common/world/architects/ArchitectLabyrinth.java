package projectzulu.common.world.architects;

import java.util.Random;

import org.lwjgl.util.Point;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.labyrinth.BlueprintDeadEndChest;
import projectzulu.common.world.structures.labyrinth.BlueprintLabyrinthCobweb;
import projectzulu.common.world.structures.labyrinth.BlueprintLabyrinthHiddenWall;
import projectzulu.common.world.structures.labyrinth.BlueprintLabyrinthRandomWall;

public class ArchitectLabyrinth extends Architect{

	public ArchitectLabyrinth(){
		unCarvedBlueprintList.add(new BlueprintDeadEndChest()); // DeadEnd Chest
		unCarvedBlueprintList.add(new BlueprintLabyrinthCobweb()); // Cobwebs Chest
		carvedBlueprintList.add(new BlueprintLabyrinthRandomWall()); // Generic Wall with Random Stone + Air
		carvedBlueprintList.add(new BlueprintLabyrinthHiddenWall()); // Hidden Room With Broken Wall
	}
	
	@Override
	public BlockWithMeta getCarvedBlock(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection,
			int buildingIndex) {
		if(buildingIndex >= 0 && buildingIndex < carvedBlueprintList.size() ){
			return carvedBlueprintList.get(buildingIndex).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
		}
		
		return carvedBlueprintList.get(carvedState).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
	}

	@Override
	public BlockWithMeta getUnCarvedBlock(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection,
			int buildingIndex) {
		if(buildingIndex >= 0 && buildingIndex < unCarvedBlueprintList.size() ){
			return unCarvedBlueprintList.get(buildingIndex).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
		}
		return unCarvedBlueprintList.get(unCarvedState).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
	}

	@Override
	public BlockWithMeta getBlueprintSetBlock(Point cellCoord, CellIndexDirection cellIndexDirection, 
			int bluePrintIndex,	int buildingIndex, int cellIndex, int cellSize, int curHeight, int maxHeight, Random random) {
		return null;
	}
	
	
	
}
