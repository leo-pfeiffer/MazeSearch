/********************Starter Code
 * 
 * This class contains the maps to be used for evaluation 
 * 
 * @author at258
 *
 */

public enum Map {


	//************************TEST MAPS as discussed in lectures ******************** 

	JMAP00(new int [][] { //JMAP00 is the map in the spec  
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,1,1,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}

	}),


	JMAP01(new int [][] { //JMAP01 is used in the given tests
		{0,0,0},
		{0,0,0},
		{0,0,0}
	}),
	

	JMAP02(new int [][] { //JMAP03 is used in the given tests
		{0,0,0},
		{0,0,0},
		{0,1,0}
	}),

	//************************MAPS for evaluation ********************
	MAP0(new int [][] {
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}
	}),
	MAP1(new int [][] {
		{0,0,0,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
	}),
	MAP2(new int [][] {
		{0,0,0,0,0,0},
		{0,0,1,1,0,0},
		{0,0,1,0,1,0},
		{0,0,1,1,1,0},
		{0,0,0,0,0,0},
		{0,0,1,1,1,0}
	}),
	MAP3(new int [][] {
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,1,0,0,1,0},
		{0,0,0,1,0,0,0,0,0,0},
		{0,0,0,0,0,0,1,1,0,0},
		{0,0,1,1,0,1,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},

	}),
	MAP4(new int [][] {
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,1,0,0},
		{1,0,0,1,1,0,0,0},
		{0,0,0,1,0,0,0,0},
		{0,0,1,0,0,0,1,0},
		{0,0,1,1,0,0,0,0},
		{0,0,1,1,0,0,0,0},
		{0,0,0,0,0,0,0,0}
	})
	;

	public final int[][] map; 

	Map(int[][] map){
		this.map=map;

	}

	public int[][] getMap() {
		return map;
	}

	public boolean isOnMap(Coord coord) {
		boolean vertical = coord.getRow() >= 0 && coord.getRow() < map.length;
		boolean horizontal = coord.getCol() >= 0 && coord.getCol() < map[0].length;
		return vertical && horizontal;
	}

	public boolean isIsland(Coord coord) {
		return map[coord.getRow()][coord.getCol()] == 1;
	}



}