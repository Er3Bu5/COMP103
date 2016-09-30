/* Code for COMP 103 Assignment 1 
 * Name:Antonio Cacciamani
 * Usercode: cacciaanto 
 * ID: 300388626
 */
import ecs100.*;
import java.util.*;
import java.awt.*;
import java.io.*;

public class Spiral{
    int [] lineArray;  //line array feild
    int [][] squareArray; //square array feild
    int [][] spiralArray;  //spiral array feild
    int mar= 20;   //margin
    int width= 50;  //square width
        public void line (){
        this.lineArray = new int [10]; //new instance of line int array
        //traverse array, add count +1 to each index in array
        for (int i=0; i < lineArray.length; i++) {
            lineArray[i]= i+1;
        }

        for (int j=0; j < lineArray.length; j++) {
            String temp= Integer.toString(lineArray [j]); // converts each index to string
            UI.setColor(new Color(25*lineArray[j]));  // colour
            UI.fillRect(mar+(width*j),mar,width,width); // square draw
            UI.setColor(Color.white);
            UI.drawString(temp, mar+(width*j)+20, mar+25); // value printout
        }

    }

    public void square (){
        this.squareArray = new int [10][10]; //new instance of 2d square array
        //traverse 2d array, add count +1 to index in array
        for (int j = 0; j<squareArray[0].length; j++){
            for (int i = 0; i<squareArray.length; i++){
                int tempSq = j*10+i+1;
                squareArray [i][j] = tempSq;
            }
        }

        for (int j = 0; j<squareArray[0].length; j++){
            for (int i = 0; i<squareArray.length; i++){
                String temp= Integer.toString(squareArray [i][j]);
                UI.setColor(new Color(2*squareArray[i][j])); // colour
                UI.fillRect(mar+(width*i),mar+(width*j),width,width);//square draw
                UI.setColor(Color.white);
                UI.drawString(temp, 2*mar+(width*i), 2*mar+(width*j)+10);//value printout
            }
        }
    }

    public void spiral (){ 
        int arrayL=10; // array length feild
        int p1=1; // direction feild
        int p2= 0; // arbitrary step count feild
        this.spiralArray= new int [arrayL][arrayL];  // new 2d array
        //traverse 2d array, add count +1 to each index in array
        for (int i = 0; i<spiralArray.length; i++){
            for (int j = 0; j<spiralArray[0].length; j++){
                int tempSp=j+1; 
                spiralArray [i][j] = tempSp;
            }
        }
        //sorting algorythm to rearrange values into spiral shape
        for (int a = arrayL-1; a>0; a--){
            //  creating a walk
            for(int b = p2;b<a; b++){
                spiralArray[p2][b]= p1++;
            }
            for(int b = p2; b <a; b++){
                spiralArray[b][a]=p1++;
            }
            for(int b = a; b>p2; b--){
                spiralArray[a][b]=p1++;
            }
            for(int b = a; b>p2; b--){
                spiralArray[b][p2]= p1++;
            }
            p2++; // increment step count
        }

        for (int j = 0; j<spiralArray[0].length; j++){
            for (int i = 0; i<spiralArray.length; i++){
                String temp= Integer.toString(spiralArray [i][j]);
                UI.setColor(new Color(2*spiralArray[i][j])); // colour
                UI.fillRect(mar+(width*j),mar+(width*i),width,width);//square draw
                UI.setColor(Color.white);
                UI.drawString(temp, 2*mar+(width*j), 2*mar+(width*i)+10);//value printout
            }
        }
    }

    // Constructors
    /**
     * Construct a new ImageProcessor object
     * and set up the GUI
     */
    public Spiral(){
        UI.addButton("Line", this::line);
        UI.addButton("Square", this::square);
        UI.addButton("Spiral", this::spiral);
        UI.addButton("Clear", UI::clearGraphics);        
        UI.addButton("Quit", UI::quit) ;              
    }

}
