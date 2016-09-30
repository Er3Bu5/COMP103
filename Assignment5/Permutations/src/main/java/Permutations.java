// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name: antonio cacciamani
 * Usercode: cacciaanto
 * ID: 300388626
 */

import ecs100.*;
import java.util.*;

/** 
 *  Prints out all permuations of a string
 *  The static method permute constructs all the permutations.
 *  The main method gets the string, calls recPermute, and prints the result.
 */
public class Permutations {
    public static void calcPermute (String prefix, String suffix, List<String> perms){
        if(suffix.isEmpty()){
            perms.add(prefix); // adds found permutation to perms
            return;
        }
        for (int i = 0; i < suffix.length(); i++){ // iterates through each letter in the string
            calcPermute(prefix + suffix.charAt(i),suffix.substring(0, i)+ suffix.substring(i+1),perms); /// adds each char from the suffix to the prefex
        }
    }

    /**
     * @return a List of all the permutations of a String. 
     */
    public static List <String> recPermute(String string) {
        List <String> perms= new ArrayList<>();  // arraylist to hold all permutations
        calcPermute ("", string, perms); // call calc method passing the string in to suffix
        return perms;
    }

    // Main
    public static void main(String[] arguments){
        UI.initialise();
        UI.setWindowSize(500,400);
        UI.setDivider(1);
        String string = "";
        int count = 0;
        while (! string.equals("#")) {
            string = UI.askString("Enter string to permute - # to exit: ");
            if (string.length() < 11) {
                List<String> permutations = recPermute(string);

                for (String p : permutations){
                    UI.println(p);
                    count++;
                }

                UI.println("---------");
                UI.println(count);
            }
            else UI.println("Give a smaller string.");
        }
        UI.quit();
    }    
}
