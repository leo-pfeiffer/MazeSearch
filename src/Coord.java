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

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o instanceof Coord) {
			Coord coord = (Coord) o;
			return coord.row == row && coord.col == col;
		}
		return false;
	}

}
