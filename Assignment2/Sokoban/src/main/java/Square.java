// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103 Assignment 2  */

/** 
 *  The possible squares, along with useful methods.
 *  Would be better represented by an Enum class.
 */

public class Square {
    private String type;

    Square(String t){
        type = t;
    }

    public String imageName() {
        return type +".gif";
    }

    /** Does this shelf still miss its box? */
    public boolean isEmptyShelf() {
        return type == "emptyShelf";
    }

    /** Whether there is a box on this square */
    public boolean hasBox() {
        return (type.contains("box"));
    }

    /** Whether the square is free to move onto */
    public boolean isFree() {
        return (type.contains("empty"));    }

    /** The square you get if you push a box off this square */
    public void moveBoxOff() {
        if (type=="box") {
            type = "empty";
            return;
        }

        if (type=="boxOnShelf") {
            type = "emptyShelf";
            return;
        }
    }

    /** The square you get if you push a box on to this square */
    public void moveBoxOn() {
        if (type=="empty") {
            type = "box";
            return;
        }

        if (type=="emptyShelf") {
            type = "boxOnShelf";
            return;
        }
    }
}
