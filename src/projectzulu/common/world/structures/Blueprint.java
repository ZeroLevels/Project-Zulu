package projectzulu.common.world.structures;

import java.util.Random;

import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;

public abstract class Blueprint {
	
	public abstract BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection);	
	
	public int getWeight(){
		return 1;
	}
	
	
	/**
	 * Used to Search Architect list for a specific Building Type
	 * Should be All lowercase
	 */
	public String getIdentifier(){
		return "generic";
	}
}
