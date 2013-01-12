package projectzulu.common.world.architects;

import java.util.Random;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.cemetary.BlueprintCemetaryFountain;
import projectzulu.common.world.structures.cemetary.BlueprintCemetaryFountain2;
import projectzulu.common.world.structures.cemetary.BlueprintCemeteryTomb;
import projectzulu.common.world.structures.cemetary.BlueprintCemeteryTomb2;
import projectzulu.common.world.structures.labyrinth.BlueprintScatteredTombstonesAndFlowers;

public class ArchitectCemetary extends Architect{
	

	public ArchitectCemetary(){
		unCarvedBlueprintList.add(new BlueprintScatteredTombstonesAndFlowers());
		carvedBlueprintList.add(new BlueprintCemeteryTomb());
		carvedBlueprintList.add(new BlueprintCemeteryTomb2());
		carvedBlueprintList.add(new BlueprintCemetaryFountain());
		carvedBlueprintList.add(new BlueprintCemetaryFountain2());
	}

	@Override
	public BlockWithMeta getCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection,
			int buildingIndex) {
		return carvedBlueprintList.get(carvedState).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
	}
	
	@Override
	public BlockWithMeta getUnCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection,
			int buildingIndex) {
		return unCarvedBlueprintList.get(unCarvedState).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
	}



}
