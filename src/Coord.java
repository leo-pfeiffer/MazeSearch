import java.util.HashSet;
import java.util.List;

/********************Starter Code
 * 
 * This represents the coordinate data structure (row, column)
 * and prints the required output
 *
 *
 * @author at258, 190026921
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

	/**
	 * Get the "A" coordinate of the current coordinate.
	 * // todo test
	 * */
	public int getACoord() {
		return -row;
	}

	/**
	 * Get the "B" coordinate of the current coordinate.
	 * // todo test
	 * */
	public double getBCoord() {
		return (double) (row + col - getDir()) / 2;
	}

	/**
	 * Get the "C" coordinate of the current coordinate.
	 * // todo test
	 * */
	public double getCCoord() {
		return (double) (row + col - getDir()) / 2 - row + getDir();
	}

	/**
	 * Calculate the distance between two coordinates on a triangle grid.
	 * // todo test
	 * */
	public double getManhattanDistance(Coord other) {
		double a = Math.abs(getACoord() - other.getACoord());
		double b = Math.abs(getBCoord() - other.getBCoord());
		double c = Math.abs(getCCoord() - other.getCCoord());
		return a + b + c;
	}

	/**
	 * Get a set of possible moves (as integers) from the current coordinate.
	 * RIGHT: 1
	 * DOWN: 2
	 * LEFT: 3
	 * UP: 4
	 * Upwards: Can move in directions 1, 2, 3
	 * Downwards: Can move in directions 1, 3, 4
	 * @return A set of possible moves.
	 * */
	public HashSet<Integer> getPossibleMoves() {

		if (getDir() == 0) {
			// Upwards: Can move in directions 1, 2, 3
			return new HashSet<>(List.of(new Integer[]{1, 2, 3}));
		} else {
			// Downwards: Can move in directions 1, 3, 4
			return new HashSet<>(List.of(new Integer[]{1, 3, 4}));
		}
	}

	/**
	 * Get all adjacent coordinates from the current coordinate.
	 * Upwards: Can move in directions RIGHT, DOWN, LEFT
	 * Downwards: Can move in directions RIGHT, LEFT, UP
	 * @return A list of adjacent coordinates.
	 * */
	public Coord[] getAdjacentCoords() {
		Coord RIGHT = new Coord(row, col + 1);
		Coord DOWN = new Coord(row + 1, col);
		Coord LEFT = new Coord(row, col - 1);
		Coord UP = new Coord(row - 1, col);

		if (getDir() == 0) {
			return new Coord[]{RIGHT, DOWN, LEFT};
		} else {
			return new Coord[]{RIGHT, LEFT, UP};
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Coord coord = (Coord) o;
		return coord.row == row && coord.col == col;
	}

	@Override
	public int hashCode() {
		return row * 31 + col;
	}
}
