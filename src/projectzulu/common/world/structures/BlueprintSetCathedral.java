package projectzulu.common.world.structures;

import java.util.Random;

import org.lwjgl.util.Point;

import projectzulu.common.world.architects.Architect;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.cell.CellType;
import projectzulu.common.world.cell.MazeCell;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralDome;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralEastTower;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralEntrance;
import projectzulu.common.world.structures.cemetary.BlueprintCathedralHallway;

public class BlueprintSetCathedral implements BlueprintSet {
	public BlueprintSetCathedral(){
		bluePrints.add(new BlueprintCathedralDome());
		bluePrints.add(new BlueprintCathedralHallway());
		bluePrints.add(new BlueprintCathedralEntrance());
		bluePrints.add(new BlueprintCathedralEastTower());
	}
	
	@Override
	public void assignCellsWithBlueprints(MazeCell[][] cellList, Point numCells, Random random, Point buildCoords, int buildingSetIndex){
		applyBlueprintToCell(cellList, buildCoords, new Point(2,2), Architect.searchListForIdentifer("dome", bluePrints), CellIndexDirection.NorthWestCorner, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(3,2), Architect.searchListForIdentifer("dome", bluePrints), CellIndexDirection.NorthEastCorner, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(2,3), Architect.searchListForIdentifer("dome", bluePrints), CellIndexDirection.SouthWestCorner, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(3,3), Architect.searchListForIdentifer("dome", bluePrints), CellIndexDirection.SouthEastCorner, buildingSetIndex);
		
		applyBlueprintToCell(cellList, buildCoords, new Point(2,4), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.WestWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(3,4), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.EastWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(2,1), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.WestWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(3,1), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.EastWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(2,0), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.WestWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(3,0), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.EastWall, buildingSetIndex);
		
		applyBlueprintToCell(cellList, buildCoords, new Point(1,2), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.NorthWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(1,3), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.SouthWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(4,2), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.NorthWall, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(4,3), Architect.searchListForIdentifer("hallway", bluePrints), CellIndexDirection.SouthWall, buildingSetIndex);
		
		applyBlueprintToCell(cellList, buildCoords, new Point(2,5), Architect.searchListForIdentifer("entrance", bluePrints), CellIndexDirection.NorthWestCorner, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(3,5), Architect.searchListForIdentifer("entrance", bluePrints), CellIndexDirection.NorthEastCorner, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(2,6), Architect.searchListForIdentifer("entrance", bluePrints), CellIndexDirection.SouthWestCorner, buildingSetIndex);
		applyBlueprintToCell(cellList, buildCoords, new Point(3,6), Architect.searchListForIdentifer("entrance", bluePrints), CellIndexDirection.SouthEastCorner, buildingSetIndex);
		
		int tempCellHeight = cellList[buildCoords.getX()+0][buildCoords.getY()+5].getCellHeight();
		applyBlueprintToCell(cellList, buildCoords, new Point(0,5), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.NorthWestCorner, buildingSetIndex, tempCellHeight*2);
		applyBlueprintToCell(cellList, buildCoords, new Point(1,5), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.NorthEastCorner, buildingSetIndex, tempCellHeight*2);
		applyBlueprintToCell(cellList, buildCoords, new Point(0,6), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.SouthWestCorner, buildingSetIndex, tempCellHeight*2);
		applyBlueprintToCell(cellList, buildCoords, new Point(1,6), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.SouthEastCorner, buildingSetIndex, tempCellHeight*2);
		
		applyBlueprintToCell(cellList, buildCoords, new Point(4,5), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.NorthWestCorner, buildingSetIndex, tempCellHeight*2);
		applyBlueprintToCell(cellList, buildCoords, new Point(5,5), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.NorthEastCorner, buildingSetIndex, tempCellHeight*2);
		applyBlueprintToCell(cellList, buildCoords, new Point(4,6), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.SouthWestCorner, buildingSetIndex, tempCellHeight*2);
		applyBlueprintToCell(cellList, buildCoords, new Point(5,6), Architect.searchListForIdentifer("east_tower", bluePrints), CellIndexDirection.SouthEastCorner, buildingSetIndex, tempCellHeight*2);
	}
	
	public BlockWithMeta getBlockFromBlueprint(int bluePrintIndex, Point cellCoord, CellIndexDirection cellIndexDirection, int cellIndex, int cellSize, int curHeight, int maxHeight, Random random){
		return bluePrints.get(bluePrintIndex).getBlockFromBlueprint(cellIndex, cellSize, curHeight, maxHeight, cellCoord.getX(), cellCoord.getY(), random, cellIndexDirection);
	}
	
	@Override
	public Point getRequiredFootPrint(){
		return new Point(6,7);
	}
	
	@Override
	public String getIdentifier(){
		return "Cathedral";
	}

	private void applyBlueprintToCell(MazeCell[][] cellList, Point baseBuildCoords, Point localBuildCoords, int buildingSchematic, CellIndexDirection cellDirection, int buildingSetIndex){
		cellList[baseBuildCoords.getX()+localBuildCoords.getX()][baseBuildCoords.getY()+localBuildCoords.getY()].setCellSubType(CellType.BuildingSet);
		cellList[baseBuildCoords.getX()+localBuildCoords.getX()][baseBuildCoords.getY()+localBuildCoords.getY()].setBuildingSchematic(buildingSchematic);
		cellList[baseBuildCoords.getX()+localBuildCoords.getX()][baseBuildCoords.getY()+localBuildCoords.getY()].setCellType(2);
		cellList[baseBuildCoords.getX()+localBuildCoords.getX()][baseBuildCoords.getY()+localBuildCoords.getY()].setCellIndexDirection(cellDirection);
		cellList[baseBuildCoords.getX()+localBuildCoords.getX()][baseBuildCoords.getY()+localBuildCoords.getY()].setBuildingSet(buildingSetIndex);
	}
	
	private void applyBlueprintToCell(MazeCell[][] cellList, Point baseBuildCoords, Point localBuildCoords, int buildingSchematic, CellIndexDirection cellDirection, int buildingSetIndex, int cellHeight){
		applyBlueprintToCell(cellList, baseBuildCoords, localBuildCoords, buildingSchematic, cellDirection, buildingSetIndex);
		cellList[baseBuildCoords.getX()+localBuildCoords.getX()][baseBuildCoords.getY()+localBuildCoords.getY()].setCellHeight(cellHeight);
	}

}
