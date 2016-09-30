// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 Assignment 2
 * Name:antonio cacciamani
 * Usercode:cacciaanto
 * ID:300388626
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ecs100.UI;

/** 
 * This class is provided as a bad example.
 * Don't do this at home!
 */
public class Order {

    private List <String> orders;//array to store wanted order
    private List <Boolean> ready; 

    public Order() {
        orders= new ArrayList<String>(); // array to store wanted order
        ready= new ArrayList<Boolean>(); //stores if ready, parallel index with orders
        int count= (int)(Math.random() * 7);// random size order
        if (orders.isEmpty()){
            for (int i = 0; i<=count; i++){
                int choice = (int)(Math.random() * 5); // randomises order
                if (choice==0){ 
                    orders.add(i,"Fish");// adds fish
                    ready.add(i, true);//sets ready to true
                }else if (choice==1){
                    orders.add(i,"Chips");
                    ready.add(i, true);
                }else if (choice==2) {
                    orders.add(i,"Burger");
                    ready.add(i, true);}
                else if (choice==3) {
                    orders.add(i,"Nachos");
                    ready.add(i, true);
                }else if (choice==4) {
                    orders.add(i,"Hotdog");
                    ready.add(i, true);
                }
            }
        }
    }

    /** 
     *  The order is ready as long as every item that is
     *  wanted is also ready.
     */
    public boolean isReady() {
        for (boolean r: ready){//if there is any index in ready that is true, return false
            if (r == true){
                return false;
            }
        }
        return true;
    }

    /** 
     *  If the item is wanted but not already in the order,
     *  then put it in the order and return true, to say it was successful.
     *  If the item not wanted, or is already in the order,
     *  then return false to say it failed.
     */
    public boolean addItemToOrder(String item){
        for (int i=0; i<orders.size(); i++){ // iterate through arrays
            if (ready.get(i) == true) { //if the index is ready
                if (orders.get(i).equals(item)) { //if wanted order is the same as given order
                    ready.set(i, false);
                    return true;
                }
            }
        }
        return false;
    }

    /** 
     *  Computes and returns the price of an order.
     *  [CORE]: Uses constants: 2.50 for fish, 1.50 for chips, 5.00 for burger
     *  to add up the prices of each item
     *  [COMPLETION]: Uses a map of prices to look up prices
     */
    public double getPrice() {
        double price = 0; 
        for (String o:orders){ // for each order increase the price
            price = price +  FastFood.pricing.get(o);
        }
        return price;
    }

    public void draw(int y) {
       for (int i=0; i < ready.size(); i++){ // increment through arrays
            if(ready.get(i) == true){ 
                UI.drawImage(orders.get(i)+ "-grey.png", 10+40*i, y);// determies grey img
            }else{
                UI.drawImage(orders.get(i) + ".png", 10+40*i, y);// normal img
            }  
        }
    }
}
