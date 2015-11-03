/* 
* Michael Green
* masc0338
* Program 3 CS310
* @author Michael Green
* @description Finds the shortest path using a breadth first search of a Maze given to us.
*/
package data_structures;

public class MazeSolver {
    private MazeGrid maze;
    private Queue < GridCell > queue;
    private Stack < GridCell > stack;
    private int dimension;

    public MazeSolver(int dimension) {
        this.dimension = dimension - 1;
        maze = new MazeGrid(this, dimension);
        queue = new Queue < GridCell > ();
        stack = new Stack < GridCell > ();
    };

    /* Finds the shortest distance from the origin to each block and puts it in each square */
    public void mark() {
        /* Starting from the origin */
        queue.enqueue(maze.getCell(0, 0));
        queue.peek().setDistance(0);
        /* As long as there's more squares we can keep going*/
        while (!queue.isEmpty()) {
            /* Getting info about the point and marking it's distance */
            GridCell point = queue.dequeue();
            int x = point.getX(), y = point.getY(), d = point.getDistance();
            maze.markDistance(point);
            /* Getting all the valid cells around the current point */
            GridCell[] adjacentCells = {
                maze.getCell(x - 1, y), maze.getCell(x + 1, y),
                maze.getCell(x, y - 1), maze.getCell(x, y + 1)
            };
            for (GridCell cell : adjacentCells) {
                if (maze.isValidMove(cell) && !cell.wasVisited()) {
                    cell.setDistance(d + 1);
                    queue.enqueue(cell);
                }
            };
        };
    };

    public boolean move() {
        /* Starting from the endpoint */
        GridCell point = maze.getCell(dimension, dimension);
        int x = point.getX(), y = point.getY(), d = point.getDistance();
        if (d == -1) {
            return false;
        };
        while (d != 0) {
            /* Possibly find a way to create a utility function that creates adj cells */
            GridCell[] adjacentCells = {
                maze.getCell(x - 1, y), maze.getCell(x + 1, y),
                maze.getCell(x, y - 1), maze.getCell(x, y + 1)
            };
            GridCell minCell = null;
            for (GridCell cell : adjacentCells) {
                /* We unfortunately still have to check cell validity even though we did this already */
                if (maze.isValidMove(cell) && cell.wasVisited()) {
                    int cellD = cell.getDistance();
                    /* Finding the smallest Distance in the adjacent squares*/
                    if (minCell == null || cellD < minCell.getDistance()) {
                        minCell = cell;
                    }
                }
            }
            d = minCell.getDistance();
            x = minCell.getX();
            y = minCell.getY();
            stack.push(minCell);
        };
        while (!stack.isEmpty()) {
            maze.markMove(stack.pop());
        }
        return true;
    };

    public void reset() {
        stack.makeEmpty();
        queue.makeEmpty();
    };
}