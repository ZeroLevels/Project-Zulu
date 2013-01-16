package projectzulu.common.world.buildingmanager;

import java.util.Random;
import java.util.Set;

import org.lwjgl.util.Point;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.world.architects.Architect;
import projectzulu.common.world.architects.ArchitectCathedral;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.cell.CellIndexDirection;
import projectzulu.common.world.cell.CellType;
import projectzulu.common.world.cell.MazeCell;
import projectzulu.common.world.structures.BlueprintSet;


public class BuildingManagerCathedral extends BuildingManager{

	public BuildingManagerCathedral(World world) {
		super(world);
	}

	@Override
	protected Architect getDefaultArchitect(){
		return new ArchitectCathedral();
	}

	@Override
	public void createFloor(Vec3 startingPos, int width, int floorHeight, int floorNumber, int cellSize) {}

	@Override
	public boolean evaluateCarvedCells(MazeCell[][] cellList, int xIndex, int zIndex, int numCellsX, int numCellsZ, Random random) {
		cellList[xIndex][zIndex].setCellSubType(CellType.AirCell);
		return false;
	}
	boolean created = false;
	
	@Override
	public boolean evaluateUnCarvedCells(MazeCell[][] cellList, int xIndex, int zIndex, int numCellsX, int numCellsZ, Random random) {
		if(!created && random.nextInt(10) == 0){
			
			/* Try to Line Up an Rectangle MxN Centered around xIndex, zIndex. */
			int buildingSetIndex = getArchitect().searchBlueprintSetFor("Cathedral");
			BlueprintSet bluePrintSet = getArchitect().getBlueprintSet(buildingSetIndex);
			Point desiredSize = bluePrintSet.getRequiredFootPrint();
			Point cellCoordsToBuild = searchForValidBuildingSquare(desiredSize, new Point(xIndex,zIndex), new Point(numCellsX,numCellsZ));

			if(cellCoordsToBuild.getX() != -1 && cellCoordsToBuild.getY() != -1){
				bluePrintSet.assignCellsWithBlueprints(cellList, new Point(xIndex,zIndex), new Point(numCellsX,numCellsZ), random, cellCoordsToBuild, buildingSetIndex);				
				created = true;
				return true;
			}
		}
		return false;
	}
	
	private Point searchForValidBuildingSquare(Point desiredSize, Point cellCoord, Point numCells){
		Point validPoint = new Point(-1, -1);
		for (int i = -desiredSize.getX(); i <= desiredSize.getX(); i++){
			for (int k = -desiredSize.getY(); k <= desiredSize.getY(); k++){
				validPoint.setLocation(cellCoord.getX()+i, cellCoord.getY()+k);
				/* Evaluate Each Point To if It Outside Bound, if all are valid Return Point*/
				if(validPoint.getX() >= 0 && validPoint.getX() + desiredSize.getX() <= numCells.getX()
						&& validPoint.getY() >= 0 && validPoint.getY() + desiredSize.getY() <= numCells.getY()){
					return validPoint;
				}
			}
		}
		return new Point(-1, -1);
	}
	
	@Override
	public void createBuilding(MazeCell[][] cellList, int xIndex, int zIndex,
			int floorHeight, Random random) {
		int cellSize = cellList[xIndex][zIndex].getSize();
		System.out.println("DOOOM");

		/* Randomise the Architects State for this cell, Used to Determine what should be built */
		getArchitect().randomiseState(random);

		/* Loop through ann units within the Given Cell */
		for (int cellIndex = 0; cellIndex < (cellSize*cellSize); cellIndex++){
			/* Get Important Properties From Cell */
			Set<CellType> cellSubType = cellList[xIndex][zIndex].getCellSubType();
			ChunkCoordinates position = new ChunkCoordinates(
					(int)cellList[xIndex][zIndex].getLocation(cellIndex).xCoord,
					(int)cellList[xIndex][zIndex].getLocation(cellIndex).yCoord,
					(int)cellList[xIndex][zIndex].getLocation(cellIndex).zCoord);

			for (CellType cellType : cellSubType){
				switch (cellType){
				case BuildingSet:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(getArchitect().getBlueprintSetBlock(new Point(xIndex,zIndex), cellList[xIndex][zIndex].getCellIndexDirection(), 
								cellList[xIndex][zIndex].getBuildingSet(), cellList[xIndex][zIndex].getBuildingSchematic(),
								cellIndex, cellSize, j, floorHeight, random),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);

						
//						HandleBlockPlacement(getArchitect().getBlueprintSetBlock(cellIndex, cellSize, j, floorHeight, xIndex, zIndex, random,
//								cellList[xIndex][zIndex].getCellIndexDirection(), cellList[xIndex][zIndex].getBuildingSchematic()),
//								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				default:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				}

			}
		}
	}

	@Override
	public void createSpecial(Vec3 startingPos, int width, int floorHeight,
			int floorNumber, int cellSize) {
		// TODO Auto-generated method stub
	}
}
