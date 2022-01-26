import java.util.HashSet;
import java.util.List;

/********************Starter Code
 * 
 * This represents the coordinate data structure (row, column)
 * and prints the required output
 *
 *
 * @author at258
 *   
 */

public class Coord {
	private final int row;
	private final int col;

	public Coord(int row, int column) {
		this.row = row;
		this.col = column;
	}

	public String toString() {
		return "(" + row + "," + col + ")";
	}

	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}

	/**
	 * Get the direction of the coordinate.
	 * Triangle points upwards: 0
	 * Triangle points downwards: 1
	 * */
	public int getDir() {
		if (row % 2 == 0) {
			return col % 2 == 0 ? 0 : 1;
		} else {
			return col % 2 == 0 ? 1 : 0;
		}
	}

	public HashSet<Integer> getPossibleMoves() {

		if (getDir() == 0) {
			// Upwards: Can move in directions 1, 2, 3
			return new HashSet<>(List.of(new Integer[]{1, 2, 3}));
		} else {
			// Downwards: Can move in directions 1, 3, 4
			return new HashSet<>(List.of(new Integer[]{1, 3, 4}));
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Coord coord = (Coord) o;
		return coord.row == row && coord.col == col;
	}

}
