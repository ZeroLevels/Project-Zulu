package projectzulu.common.world.cell;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
/**
 * It should be noted That in its Current form, most of its functionality assumes an cellSize that is an odd number
 * i.e. Grabbing the Ceneter doesn't exist for odd numbers
 * It should be extended eventually for even numbers to achieve features such as cenetered double doors, but for now it's not and care should be taken.
 * A minimum Cell Size of 2-3 is assumed. As a Cell converges to a point features disapear. 
 */
public enum CellIndexDirection{
	Unknown, // Used for a Function if Nothing else should fit

	Middle,
	Inner,
	NorthMiddle,
	SouthMiddle,
	WestMiddle,
	EastMiddle,
	NorthWestCorner,
	NorthEastCorner,
	SouthWestCorner,
	SouthEastCorner,
	NorthWall,
	SouthWall,
	WestWall,
	EastWall,
	
	NorthEastSouthWestDiagonal,
	SouthEastNorthWestDiagonal;

	/* Cell Index Format
	 * CS = Cell Size
	 * 0	CS		2*CS	3*CS 	... 	CS*(CS-1)
	 * 1
	 * 2
	 * 3
	 * ...
	 * CS-1	2CS-1	3CS-1	4*CS-1	...		CS*CS-1
	 */
	
	public CellIndexDirection randomCardinalDirection(Random random){
		switch (random.nextInt(4)) {
		case 0:
			return NorthMiddle;
		case 1:
			return SouthMiddle;
		case 2:
			return WestMiddle;
		case 3:
			return EastMiddle;
		default:
			return NorthMiddle;
		}
	}
	
	
	/**
	 * Effectively Gets the Column of the Matrix represented by Index. Western Edge is 0, Eastern Edge is CellSize - 1.
	 */
	public static int getWestEastIndex(int cellIndex, int cellSize){
		return cellIndex / cellSize;
	}
	
	/**
	 * Effectively Gets the Row of the Matrix represented by Index. Northern Edge is 0, Southern Edge is CellSize - 1.
	 */
	public static int getNorthSouthIndex(int cellIndex, int cellSize){
		return cellIndex % cellSize;
	}
	
	/** 
	 * Return an Index Between between -(CellSize-1) and +(CellSize-1) representing the Offset from the NorthEastSouthWest Diagonal 
	 * - is to the West, + is to the East; Main Diagonal is 0 */
	public static int getNESWDiagonalIndex(int cellIndex, int cellSize){
		for (int i = -(cellSize-1); i < cellSize; i++){
			int indexRow = cellIndex % cellSize;
			int indexCol = cellIndex / cellSize;
			if( cellSize-1-indexCol - indexRow + i == 0){
				return i;
			}
		}
		/* This Should *Never* Run, Thus we pass a value greater than Cell Size (which is an Impossible index)
		 * The only way this happens is if index is less than zero or greater than (cellSize^2) - 1 which by how index is declared is impossible */
		return cellSize+1;
	}
	
	/** 
	 * Return an Index Between between -CellSize and +CellSize representing the Offset from the SouthEastNorthWestDiagonal Diagonal
	 * - is to the West, + is to the East */
	public static int getSENWDiagonalIndex(int cellIndex, int cellSize){
		for (int i = -(cellSize-1); i < cellSize; i++){
			int indexRow = cellIndex % cellSize;
			int indexCol = cellIndex / cellSize;
			if( indexRow - indexCol + i == 0){
				return i;
			}
		}
		/* This Should *Never* Run, Thus we pass a value greater than Cell Size (which is an Impossible index)
		 * The only way this happens is if index is less than zero or greater than (cellSize^2) - 1 which by how index is declared is impossible */
		return cellSize+1;
	}
	
	/**
	 * Returns the Main Diagonal SENW or NESW diagonals if the index is along either, returns unknown if not on either
	 * Should Probably use either {@link #getNESWDiagonalIndex} or {@link #getSENWDiagonalIndex} though these are faster due to the absence of a loop
	 */
	public static CellIndexDirection getMainDiagonalNESW(int cellIndex, int cellSize){
		int indexRow = cellIndex % cellSize;
		int indexCol = cellIndex / cellSize;
		if(cellSize-1-indexCol - indexRow == 0){
			return NorthEastSouthWestDiagonal;
		}
		return Unknown;
	}
	public CellIndexDirection getMainDiagonalSENW(int cellIndex, int cellSize){
		int indexRow = cellIndex % cellSize;
		int indexCol = cellIndex / cellSize;
		if(indexRow - indexCol == 0){
			return SouthEastNorthWestDiagonal;
		}
		return Unknown;
	}
	
	/**
	 * Similar to {@link #calcDirection} with the Exception That it only return Walls and Returns EnumSet
	 * Reminder: Corner Pieces belong to Two Walls
	 * {@link #NorthWall}, {@link #SouthWall}, {@link #WestWall}, {@link #EastWall}
	 */
	public static Set<CellIndexDirection> calcIfWall(int cellIndex, int cellSize){
		EnumSet<CellIndexDirection> enumSet = EnumSet.noneOf(CellIndexDirection.class);
		/* Check if Outer Edge */
		if( (cellIndex % cellSize == (0)) ){
			enumSet.add(NorthWall);
		}
		if( (cellIndex % cellSize == (cellSize-1)) ){
			enumSet.add(SouthWall);
		}
		if( cellIndex >= cellSize*(cellSize-1) && cellIndex < cellSize*cellSize ){
			enumSet.add(EastWall);
		}
		if( cellIndex >= 0 && cellIndex < cellSize ){
			enumSet.add(WestWall);
		}
		return enumSet;
	}
	
	public CellIndexDirection calcDirection(int cellIndex, int cellSize){
		/* Check if Middle 
		 * Only Exists if cellSize is an odd Number */
		if(cellSize % 2 == 1){
			if(cellIndex == cellSize*(cellSize-1)/2+(cellSize-1)/2){
				return Middle;
			}else if( cellIndex == (cellSize-1)/2 ){
				return WestMiddle;
			}else if( cellIndex == cellSize*(cellSize-1)/2){
				return NorthMiddle;
			}else if( cellIndex == cellSize*(cellSize-1) + (cellSize-1)/2){
				return EastMiddle;
			}else if( cellIndex == cellSize*(cellSize-1)/2 + (cellSize-1)){
				return SouthMiddle;
			}
		}
		
		/* Check if Corner */
		if( cellIndex == 0){
			return NorthWestCorner;
		}else if(cellIndex == cellSize - 1){
			return SouthWestCorner;
		}else if(cellIndex == cellSize*cellSize-1){
			return SouthEastCorner;
		}else if(cellIndex == cellSize*cellSize-cellSize){
			return NorthEastCorner;
		}
		
		/* Check if Outer Edge */
		if( (cellIndex % cellSize == (0)) ){
			return NorthWall;
		}else if( (cellIndex % cellSize == (cellSize-1)) ){
			return SouthWall;
		}else if( cellIndex >= cellSize*(cellSize-1) && cellIndex < cellSize*cellSize ){
			return EastWall;
		}else if( cellIndex >= 0 && cellIndex < cellSize ){
			return WestWall;
		}
		/* If Nothing Else Mark as Inner */
		return Inner;
	}
}
