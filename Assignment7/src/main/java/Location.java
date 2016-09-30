// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 7
 * Name:
 * Usercode:
 * ID:
 */
/* Name: pondy & marcus 
 */

/** Represents a position on the window
 */

public class Location {

    // Fields

    private double x;  
    private double y;  

    // Constructor

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Methods

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Location other) {
        return Math.hypot((x - other.x), (y - other.y));
    }

}
