package projectzulu.common.world.buildingmanager;

import java.util.Random;
import java.util.Set;

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
		return false;
	}
	boolean created = false;
	
	@Override
	public boolean evaluateUnCarvedCells(MazeCell[][] cellList, int xIndex, int zIndex, int numCellsX, int numCellsZ, Random random) {
		if(!created){
			cellList[xIndex][zIndex].addCellSubType(CellType.BuildingSet);
			cellList[xIndex][zIndex].setBuildingSchematic(getArchitect().searchUncarvedFor("dome"));
			cellList[xIndex][zIndex].setCellIndexDirection(CellIndexDirection.NorthWestCorner);
			created = true;
			return true;
		}
		return false;
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
				case NorthWall:
				case SoutherWall:
				case EastWall:
				case WestWall:
					break;
				case InnerWall:
					break;
				case DeadEnd:
					break;
				case BuildingSet:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(getArchitect().getUnCarvedBlock(cellIndex, cellSize, j, floorHeight, xIndex, zIndex, random,
								cellList[xIndex][zIndex].getCellIndexDirection(), cellList[xIndex][zIndex].getBuildingSchematic()),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				case RandomUnCarved:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(getArchitect().getUnCarvedBlock(cellIndex, cellSize, j, floorHeight, xIndex, zIndex, random),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				case AirCell:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				default:
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
