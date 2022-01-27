/********************Starter Code
 *
 * This class contains some examples on how to handle the required inputs and outputs 
 * and other debugging options
 *
 * @author at258, 190026921
 *
 * run with 
 * java A1main <Algo> <ConfID>
 *
 */

public class A1main {

    public static void main(String[] args) {

        // Retrieve input and configuration and run search algorithm
        Search search;
        try {
            search = parseArguments(args);
        } catch (Exception e) {
            e.printStackTrace();
            exitWithException();
            return;
        }

        // run search algorithm
        search.runSearch();
    }

    private static void exitWithException() {
        System.out.println("Invalid arguments.");
        System.out.println("Usage: java A1main <Algo> <ConfID>");
        System.exit(1);
    }

    private static Search parseArguments(String[] args) throws SearchFactory.InvalidSearch {
        Conf conf = Conf.valueOf(args[1]);
        Map map = conf.getMap();
        Coord start = conf.getS();
        Coord goal = conf.getG();
        return new SearchFactory().makeSearch(args[0], map, start, goal);
    }

    private static void printMap(Map m, Coord init, Coord goal) {

        int[][] map = m.getMap();

        System.out.println();
        int rows = map.length;
        int columns = map[0].length;

        // top row
        System.out.print("  ");
        for (int c = 0; c < columns; c++) {
            System.out.print(" " + c);
        }
        System.out.println();
        System.out.print("  ");
        for (int c = 0; c < columns; c++) {
            System.out.print(" -");
        }
        System.out.println();

        // print rows
        for (int r = 0; r < rows; r++) {
            boolean right;
            System.out.print(r + "|");

            if (r % 2 == 0) {    // even row, starts right [=starts left & flip right]
                right = false;
            } else {        // odd row, starts left [=starts right & flip left]
                right = true;
            }
            for (int c = 0; c < columns; c++) {
                System.out.print(flip(right));
                if (isCoord(init, r, c)) {
                    System.out.print("S");
                } else {
                    if (isCoord(goal, r, c)) {
                        System.out.print("G");
                    } else {
                        if (map[r][c] == 0) {
                            System.out.print(".");
                        } else {
                            System.out.print(map[r][c]);
                        }
                    }
                }
                right = !right;
            }
            System.out.println(flip(right));
        }
        System.out.println();
    }

    private static boolean isCoord(Coord coord, int r, int c) {
        // check if coordinates are the same as current (r,c)
        return coord.getRow() == r && coord.getCol() == c;
    }


    public static String flip(boolean right) {
        // prints triangle edges
        if (right) {
            return "\\"; // right return left
        } else {
            return "/"; // left return right
        }
    }
}
