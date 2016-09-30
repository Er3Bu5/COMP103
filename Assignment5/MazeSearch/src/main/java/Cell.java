// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:
 * Usercode:
 * ID:
 */

public class Cell {
    public final int x;
    public final int y;

    private boolean visited = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public String toString() {
        return String.format("%d,%d (%b)", x, y, visited);
    }
}
