// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name: antonio cacciamani
 * Usercode: cacciaanto
 * ID: 300388626
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;

/** 
 * Mondrian Painting Generator
 * 
 * Generates Mondrian Paintings by recursivley dividing frames into
 * sub-frames and filling them with the typical Mondrian colours. 
 * 
 */
public class Mondrian {
    // constants

    static final int MondrianLineWidth = 12;    // width of the black separating lines
    static final int MondrianSideMinimum = 48;  // minimum side below which no more splitting takes place

    // Mondrian Colours
    static final Color mondrianBlack   = new Color(25,23,26);
    static final Color mondrianWhite   = new Color(229,227,228);
    static final Color mondrianYellow  = new Color(255,221,10);
    static final Color mondrianRed     = new Color(223, 12, 29);
    static final Color mondrianBlue    = new Color(8,56,138);

    // fields
    static int level = 2;   // how deeply sub-Mondrians may be nested
    static int chance = 45; // chance to create a sub-Mondrian in %

    static List<Color> mondrianColours = new ArrayList<Color>(); // collection to pick Mondrian colours from

    // create UI and initialise Mondrian colours
    public Mondrian() {
        UI.setWindowSize(900,600);
        UI.setDivider(0.1); // leave some space for printing level and chance values

        // create buttons
        UI.addButton("Create", this::drawMondrian);
        UI.addButton("Level+", this::increaseLevel);
        UI.addButton("Level-", this::decreaseLevel);
        UI.addButton("More", this::increaseChance);
        UI.addButton("Less", this::decreaseChance);

        // make white three times more likely to occur than the other colours
        mondrianColours.add(mondrianWhite);
        mondrianColours.add(mondrianWhite);
        mondrianColours.add(mondrianWhite);
        mondrianColours.add(mondrianYellow);
        mondrianColours.add(mondrianRed);
        mondrianColours.add(mondrianBlue);

        drawMondrian();
    }

    /**
     * Increases the level, up to a maximum of 9.
     */
    public void increaseLevel() {
        if (level < 9)
            level++;

        drawMondrian(); 
    }

    /**
     * Decreases the level, up to a minimum of 0.
     */
    public void decreaseLevel() {
        if (level > 0)
            level--;

        drawMondrian();
    }

    /**
     * Increases the likelihood of creating a sub-Mondrian
     * up to a maximum of 1.
     */
    public void increaseChance() {
        if (chance <= 95)
            chance += 5;    // increase by 5%

        drawMondrian();
    }

    /**
     * Decreases the likelihood of creating a sub-Mondrian
     * up to a minimum of 0.
     */
    public void decreaseChance() {
        if (chance >= 5)
            chance -= 5;  // decrease by 5%

        drawMondrian();
    }

    /**
     * @return a random Mondrian colour.
     */
    Color randomMondrianColour() {
        return mondrianColours.get((int)(mondrianColours.size()*Math.random()));
    }

    int randomRange(int min, int max) { // helper method to find a random number within a range, end points inclusive.
        int range = (max - min) + 1;     
        return (int)(Math.random() * range) + min;
    }

    /** 
     * Draws a Mondrian within a frame specified by
     * the coordinates (x1, y1) and (x2, y2).
     * 
     * Always fills the specified frame with one (random) Mondrian colour.
     * 
     * Generates a split point within the frame to potentially 
     * draw further sub-Mondrians. The split point is within a subframe that 
     * is centred in the original frame with margins extending from 25%-75%
     * of the original frame. 
     * 
     * Only draws the dividing black lines and further sub-Mondrians, if all of the following hold:
     *    1. the currentLevel is not zero yet. 
     *    2. the filled rectangle has no sides that are smaller than MondrianSideMinimum.
     *    3. the chance of creating a sub-Mondrian is > 0%.
     * 
     */
    public void drawMondrian(int x1, int y1, int x2, int y2, int currentLevel) {
        // draw the colour patch that may or may not become divided
        UI.setColor(randomMondrianColour());
        UI.fillRect(x1+MondrianLineWidth/2, y1+MondrianLineWidth/2,    // do not paint over already painted lines
            x2-x1-MondrianLineWidth, y2-y1-MondrianLineWidth);

        /*# YOUR CODE HERE */
        if (currentLevel <= 0 || x2-x1 < MondrianSideMinimum || y2-y1 < MondrianSideMinimum  || chance <= 0 ){
            return;
        }
        int xRng = randomRange(25,75); // random percentage between 25-75%, x axis
        int yRng = randomRange(25,75); // random percentage between 25-75%, y axis

        int xSplit = (int) (x1+(xRng /100d  * (x2 - x1))); // x coord of split intersection lines
        int ySplit = (int) (y1+(yRng /100d  * (y2 - y1))); // y coord of split intersection lines

        UI.setColor(Color.black);
        UI.drawLine(xSplit, y1, xSplit, y2); // draw the split lines
        UI.drawLine(x1, ySplit, x2, ySplit);

        for(int i = 0; i < 4; i++){ // 4 trys
            double rNum = Math.random()*100; // random number
            if(rNum <= chance){ 
                drawMondrian(x1, y1, xSplit, ySplit, currentLevel-1); // recurse
                break; // try again
            }
        }

        for(int i = 0; i < 4; i++){
            double rNum = Math.random()*100;
            if(rNum <= chance){
                drawMondrian(xSplit, y1, x2, ySplit, currentLevel-1); // top right
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            double rNum = Math.random()*100;
            if(rNum <= chance){
                drawMondrian(xSplit, ySplit, x2, y2, currentLevel-1); // bottom right
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            double rNum = Math.random()*100;
            if(rNum <= chance){      
                drawMondrian(x1, ySplit, xSplit, y2, currentLevel-1); // bottom left
                break;
            }
        }
    }

    /**
     * Paints a Mondrian painting by drawing the outer frame and
     * calling drawMondrian.
     * 
     */
    public void drawMondrian() {
        int margin = 20;    // margin to canvas edges

        // get canvas size
        int width = UI.getCanvasWidth() - margin;
        int height = UI.getCanvasHeight();

        UI.clearGraphics();
        UI.setLineWidth(MondrianLineWidth);

        UI.clearText();
        UI.print("Level: " + level + "\n");
        UI.print("Chance: " + chance + "%");

        // calculate coordinates of the first, largest Mondrian patch
        int x1 = margin;
        int y1 = margin;
        int x2 = margin + (width - margin);
        int y2 = margin + (height - 2*margin);

        // draw the first, largest Mondrian patch and all smaller ones
        drawMondrian(x1, y1, x2, y2, level);

        // draw the frame last to cover unfinished areas
        UI.setColor(mondrianBlack);
        UI.drawRect(x1, y1, x2-x1, y2-y1);
    }

    // Create a new masterpiece
    public static void main(String[] arguments) {
        new Mondrian();
    }   
}
