package projectzulu.common.world.structures;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.Point;

import projectzulu.common.world.architects.Architect;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.cell.CellType;
import projectzulu.common.world.cell.MazeCell;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralDome;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralHallway;

public interface BlueprintSet {
	ArrayList<Blueprint> bluePrints = new ArrayList<Blueprint>();
	
	public abstract void assignCellsWithBlueprints(MazeCell[][] cellList, Point cellCoord, Point numCells, Random random, Point buildCoords, int buildingSetIndex);
	public abstract BlockWithMeta getBlockFromBlueprint(int bluePrintIndex, Point cellCoord, CellIndexDirection cellIndexDirection, int cellIndex, int cellSize, int curHeight, int maxHeight, Random random);
	public abstract Point getRequiredFootPrint();
	
	public abstract String getIdentifier();
}
