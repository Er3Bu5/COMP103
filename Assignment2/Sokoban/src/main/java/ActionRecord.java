// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103 Assignment 2 */

/** 
 * Records an action (move or push) in a given direction.
 */

public class ActionRecord {
    private boolean isPush;  // if it is not a "push", it is a "move"
    private String direction;

    public ActionRecord(String action, String dir) {
        if (action.equalsIgnoreCase("push"))
            isPush = true;
        direction = dir;
    }

    public boolean isPush() {
        return isPush;
    }

    public boolean isMove() {
        return !isPush;
    }

    public String direction() {
        return direction;
    }

    public String toString() {
        return ((isPush ? "Push" : "Move") + " to " + direction);
    }

    /** Test method */
    public static void main(String[] args) {
        ActionRecord A = new ActionRecord("push", "up");
        System.out.println(A + " is a push: " + A.isPush());
        System.out.println(A + " is a Move: " + A.isMove());
        ActionRecord B = new ActionRecord("move", "right");
        System.out.println(B + " is a push: " + B.isPush());
        System.out.println(B + " is a Move: " + B.isMove());
    }
}
