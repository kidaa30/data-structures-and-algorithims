/* Michael Green
 * masc0338
 * Program 3 CS310
 */
package data_structures;

public class MazeSolver {
    private final int DIMENSION;
    private MazeGrid maze;
    private Queue < GridCell > queue = new Queue < GridCell > ();
    private Stack < GridCell > stack = new Stack < GridCell > ();

    public MazeSolver(int dimension) {
        maze = new MazeGrid(this, dimension);
        DIMENSION = dimension - 1;
    };

    /* Sets the distance away from the origin in each square */
    public void mark() {
        queue.enqueue(maze.getCell(0, 0));
        queue.peek().setDistance(0);
        /* As long as there's more squares we can keep going*/
        while (!queue.isEmpty()) {
            /* Getting info about the point and marking it's distance */
            GridCell point = queue.dequeue();
            int d = point.getDistance();
            maze.markDistance(point);
            /* Getting all the valid cells around the current point */
            for (GridCell cell: getAdjacentCells(point)) {
                if (maze.isValidMove(cell) && !cell.wasVisited()) {
                    cell.setDistance(d + 1);
                    queue.enqueue(cell);
                }
            };
        };
    };
    /* From the endpoint, looks for the shortest path by comparing adjacent distances */
    public boolean move() {
        GridCell point = maze.getCell(DIMENSION, DIMENSION);
        int d = point.getDistance();
        if (d == -1) return false;
        GridCell minCell = point;
        while (d != 0) {
            for (GridCell cell: getAdjacentCells(minCell)) {
                if (maze.isValidMove(cell) && cell.wasVisited()) {
                    /* Finding the smallest distance in the adjacent squares */
                    if (cell.getDistance() < minCell.getDistance()) {
                        minCell = cell;
                    }
                }
            };
            /* Jumping out right away if there wasn't a min cell */
            if (minCell == point) return false;
            d = minCell.getDistance();
            stack.push(minCell);
        };
        while (!stack.isEmpty()) {
            maze.markMove(stack.pop());
        };
        return true;
    };

    public void reset() {
        stack.makeEmpty();
        queue.makeEmpty();
    };

    /* Utility Function that returns an array of adjacent cells given a point*/
    private GridCell[] getAdjacentCells(GridCell point) {
        int x = point.getX(), y = point.getY();
        return new GridCell[] {
            maze.getCell(x - 1, y), maze.getCell(x + 1, y),
            maze.getCell(x, y - 1), maze.getCell(x, y + 1)
        };
    };
}