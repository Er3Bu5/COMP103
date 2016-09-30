// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 7
 * Name:
 * Usercode:
 * ID:
 */
import ecs100.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * This is the main class of the entire program. It sets up the input side
 * of the user interface, and maintains a GeneralTree object. Note that this
 * GeneralTree object is not null even if there are no actual nodes inside the tree.
 * 
 * The actual display of the tree is handled in the GeneralTree class itself.
 * This class deals with input, and the output of responses to report queries.
 *
 */
public class TaxonomyApp {

    private GeneralTree taxonomy;  // the tree representing the taxonomy

    /** 
     * Constructs a new TaxonomyApp object.
     * Sets up the GUI
     */
    public TaxonomyApp() {

        taxonomy = new GeneralTree();

        // General buttons
        UI.addButton("New taxonomy", this::newTaxonomy);
        UI.addButton("Load taxonomy", this::loadTaxonomy);
        UI.addButton("Save taxonomy", taxonomy::save);

        UI.addButton("", null);

        // Nodes buttons
        UI.addButton("Add node", this::addNode);
        UI.addButton("Remove node", this::removeNode);
        UI.addButton("Move subtree", this::moveNode);

        UI.addButton("", null);

        // Report buttons
        UI.addButton("Report all below", this::reportBelow);
        UI.addButton("Report all above", this::reportAbove);
        UI.addButton("Report all same depth nodes", this::reportSameDepth);
        UI.addButton("Find closest ancestor", this::findClosestAncestor);

        taxonomy.redraw();
    }

    public void newTaxonomy() {
        String rootString = UI.askString("Name of root node:");

        taxonomy = new GeneralTree();
        taxonomy.addNode(rootString, null);
        taxonomy.redraw();
    }

    public void loadTaxonomy() {

        String fname = UIFileChooser.open("Filename to read text from");

        if (fname == null) {
            JOptionPane.showMessageDialog(null, "No file name specified");
            return;
        }
        try {
            Scanner scan = new Scanner(new File(fname));
            taxonomy.load(scan);
            scan.close();
        }
        catch(IOException ex) {
            UI.println("File loading failed: " + ex);
        } 
        taxonomy.redraw();
    }

    public void addNode() {
        String name;
        String parentName = UI.askString("Parent to add under:");

        // translate an empty input into the value required by 'addNode'
        if (parentName.length() == 0) {
            parentName = null;
            name = UI.askString("Name to add above root:");
        }
        else
            name = UI.askString("Name to add under " + parentName + ":");

        taxonomy.addNode(name, parentName);
        taxonomy.redraw();
    }

    public void removeNode() {
        String name = UI.askString("Name to remove:");
        taxonomy.removeNode(name);
        taxonomy.redraw();
    }

    public void moveNode() {
        String subtreeName = UI.askString("Name at root of subtree:");
        String destinationName = UI.askString("To new parent destination");
        taxonomy.moveSubtree(subtreeName, destinationName);
        taxonomy.redraw();
    }

    //Reports
    public void reportBelow() {
        UI.println("Subtree:");
        String subtreeRootName = UI.askString("Name at root of subtree:");
        taxonomy.printSubtreeFrom(subtreeRootName);
    }

    public void reportAbove() {
        String targetName = UI.askString("Name of target node:");
        taxonomy.printPathToRootFrom(targetName);
    }

    public void reportSameDepth() {
        int depth = UI.askInt("Report at depth:");
        taxonomy.printAllAtDepth(depth);
    }

    public void findClosestAncestor() {
        String name1 = UI.askString("First name:");
        String name2 = UI.askString("Second name:");
        String closestAncestor = taxonomy.findClosestCommonAncestor(name1, name2);
        UI.println("Closest ancestor is: " + closestAncestor);
    }

    public static void main(String[] arguments) {
        new TaxonomyApp();
    }   

}
