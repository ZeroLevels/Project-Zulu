package projectzulu.common.world.cell;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


import net.minecraft.util.Vec3;

public class MazeCell {

	/** Width of Cell, all Cells are assumed Square */
	private int size;
	/**
	 * Location Holds the Real World (Minecraft) (X,Z) Coordinates of Each Block inside
	 * The Y Coord Represents the Start Height of the maze gen
	 * Cell Locations Are Assigned Across Z first, then by X
	 * Note: South is +Z, East is +X
	 *  i.e.
	 *  	Cell 1 = (0,0), Cell 3 = (0,1) 
	 *  	Cell 2 = (1,0), Cell 4 = (1,1)
	 */
	private List<Vec3> locations = new ArrayList<Vec3>();
	/** Used to Markt he Direction of the Building in This Cell
	 * Primarily used for Multi-part buildings to Mark different parts, also to determine which way a hallway is going */
	private CellIndexDirection cellIndexDirection;
	/** used to Determine What building should go here, used for multi part buildings where memory of what building should go here must be remembered
	 * -1 Indicated that random building of type depending on cellSubType should be selected */
	private int buildingSchematic = -1;
	/**
	 *  Used by MazeGen To mark the Type this Cell should become
	 *  Marked at Different Stages of processing in conjunction with cellType
	 */
	private Set<CellType> cellSubType = EnumSet.noneOf(CellType.class) ;
	
	/**
	 *  Used by MazeGen to carve out the Maze when differentiating between Carved(Wall) / UnCarved(Open Space).
	 *  Is Essentially used to Mark if the Cell has Been Processed, Successive integers are used to mark multiple paces
	 */
	private int cellType;
	
	public MazeCell(int size){
		this.size = size;
	}
	public int getSize(){return size;}

	public void setCellIndexDirection(CellIndexDirection cellIndexDirection){this.cellIndexDirection = cellIndexDirection;}
	public CellIndexDirection getCellIndexDirection(){return this.cellIndexDirection;}
	
	public void setBuildingSchematic(int buildingSchematic){this.buildingSchematic = buildingSchematic;}
	public int getBuildingSchematic(){return this.buildingSchematic;}
	
	public void setCellType(int cellType){ this.cellType = cellType;}
	public int getCellType(){ return cellType; }
	
	public Set<CellType> getCellSubType(){	return cellSubType; }
	public Boolean ContainedInCellSubType(CellType subType){
		if(subType.equals(CellType.Wall)){
			if(cellSubType.contains(CellType.NorthWall)  || cellSubType.contains(CellType.SoutherWall) 
					|| cellSubType.contains(CellType.EastWall) || cellSubType.contains(CellType.WestWall)  ){
				return true;
			}else{
				return false;
			}
		}
		return cellSubType.contains(subType) ? true : false; 
	}

	public void addCellSubType(CellType cellSubType){ this.cellSubType.add(cellSubType);}
	public void setCellSubType(CellType cellSubType){ 
		this.cellSubType.clear();
		this.cellSubType.add(cellSubType);
		}

	public void addLocation(Vec3 position){	locations.add(position); }
	public Vec3 getLocation(int index){		
		if(locations.size() > index){
			return locations.get(index);
		}else{
			return null;
		}
	}
	
	public List getAllLocation(){ return locations;	}
	public void setAllLocations(List allLocations){	locations = allLocations;}
}
