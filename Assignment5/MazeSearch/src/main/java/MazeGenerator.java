// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:
 * Usercode:
 * ID:
 */

import java.util.*;

public class MazeGenerator {

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST;
    }

    private static final Random RANDOM = new Random();

    private final int size;
    private final Cell[][] maze;

    public MazeGenerator(int size) {
        if (size <= 0 ) size = 1;
        this.size = size;
        this.maze = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }
    }

    public void generate(Map<Cell, Set<Cell>> nodes) {
        carvePassagesFrom(maze, maze[0][0], nodes);

        for (Cell cell : nodes.keySet()) {
            cell.setVisited(false);
        }
    }

    public Cell getEntrance() {
        return maze[0][0];
    }

    public Cell getExit() {
        return maze[size - 1][size - 1];
    }

    private void carvePassagesFrom(Cell[][] maze, Cell cell, Map<Cell, Set<Cell>> neighbours) {
        cell.setVisited(true);

        for (Direction d : getDirectionsShuffled()) {
            Cell neighbour = getNeighbour(cell, d);

            if (neighbour != null) {
                carve(cell, neighbour, neighbours);
                carve(neighbour, cell, neighbours);
                carvePassagesFrom(maze, neighbour, neighbours);
            }
        }
    }

    private void carve(Cell cell, Cell neighbour, Map<Cell, Set<Cell>> neighbours) {
        Set<Cell> n = neighbours.get(cell);

        if (n == null) {
            n = new HashSet<Cell>(5);
            neighbours.put(cell, n);
        }

        n.add(neighbour);
    }

    private Iterable<Direction> getDirectionsShuffled() {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions, RANDOM);
        return directions;
    }

    private Cell getNeighbour(Cell cell, Direction direction) {
        int x = cell.x;
        int y = cell.y;

        switch (direction) {
            case NORTH: return checkValid(maze, x, y - 1);
            case SOUTH: return checkValid(maze, x, y + 1);
            case EAST:  return checkValid(maze, x + 1, y);
            case WEST:  return checkValid(maze, x - 1, y);
            default:    return null;
        }
    }

    private Cell checkValid(Cell[][] maze, int x, int y) {
        if (x < 0 || y < 0) 
            return null;

        if (x >= maze.length) 
            return null;

        Cell[] col = maze[x];

        if (y >= col.length) 
            return null;

        Cell cell = col[y];

        if (!cell.isVisited()) 
            return cell;

        return null;
    }
}
