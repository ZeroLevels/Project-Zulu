package projectzulu.common.world.architects;

import java.util.Random;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralDome;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralHallway;
import projectzulu.common.world.structures.cemetary.BlueprintCemetaryFountain;
import projectzulu.common.world.structures.cemetary.BlueprintCemetaryFountain2;
import projectzulu.common.world.structures.cemetary.BlueprintCemeteryTomb;
import projectzulu.common.world.structures.cemetary.BlueprintCemeteryTomb2;
import projectzulu.common.world.structures.labyrinth.BlueprintScatteredTombstonesAndFlowers;

public class ArchitectCathedral extends Architect{
	
	public ArchitectCathedral(){
		super();
		unCarvedBlueprintList.add(new BlueprintCathedralDome());
		unCarvedBlueprintList.add(new BlueprintCathedralHallway());
//		carvedBlueprintList.add(new BlueprintCemeteryTomb());
//		carvedBlueprintList.add(new BlueprintCemeteryTomb2());
//		carvedBlueprintList.add(new BlueprintCemetaryFountain());
//		carvedBlueprintList.add(new BlueprintCemetaryFountain2());
	}
	
	@Override
	public BlockWithMeta getCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection,
			int buildingIndex) {
//		return carvedBlueprintList.get(carvedState).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
		return null;
	}
	
	@Override
	public BlockWithMeta getUnCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection,
			int buildingIndex) {
		return unCarvedBlueprintList.get(buildingIndex).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection);
	}
}
