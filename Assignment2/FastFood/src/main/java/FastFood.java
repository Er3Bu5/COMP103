// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 Assignment 2
 * Name:antonio cacciamani
 * Usercode:cacciaanto
 * ID:300388626
 */

import ecs100.*;
import java.util.*;

/** The FastFood game involves customers who generate orders, and the player
 *  who has to fulfill the orders by assembling the right collection of food items.
 *  The goal of the game is to make as much money as possible before
 *  the player gets too far behind the customers and is forced to give up.
 *
 *  The game presents the player with a queue of orders in a fast food outlet.
 *  The player has to fullfill the customer orders by adding the correct items to
 *  the order at the head of the queue.  
 *  When the order is ready, the player can deliver the order, which will
 *  take it off the queue, and will add the price of the order to the balance.
 *  Whenever the player adds an item to the order that doesn't belong in the
 *  order, the price of the item is subtracted from the balance.
 *  The player can practice by generating orders using the Practice button.
 *  Once the game is started, the orders are generated automatically.
 */
public class FastFood{
    public static Map<String, Double> pricing; // hash map for pricing
    private Queue<Order> orders;
    private double balance;

    public FastFood() {
        orders = new ArrayDeque<Order>(); // new queue
        pricing = new HashMap <String, Double>(); // new map
        pricing.put("Fish",2.50); //populate map
        pricing.put("Chips",1.50);
        pricing.put("Burger",5.00);
        pricing.put("Nachos",6.50);
        pricing.put("Hotdog",3.50);
        
        UI.addButton("Practice Order", () -> {generateOrder(); drawOrders();});
        UI.addButton("Add Fish",       () -> {addItem("Fish"); drawOrders();});
        UI.addButton("Add Chips",      () -> {addItem("Chips"); drawOrders();});
        UI.addButton("Add Burger",     () -> {addItem("Burger"); drawOrders();});
        UI.addButton("Add Nachos",     () -> {addItem("Nachos"); drawOrders();});
        UI.addButton("Add Hotdog",     () -> {addItem("Hotdog"); drawOrders();});
        UI.addButton("Deliver Order",  () -> {deliverOrder(); drawOrders();});
        UI.addButton("Start Game",     () -> {startGame(); drawOrders();});

        drawOrders();
        this.run();

    }

    /** Creates a new order and puts it on the queue to be processed */
    public void generateOrder() {
        Order O= new Order();
        orders.offer(O);
    }

    /** As long as there is an order in the queue, adds the specified
     *  item to the order at the head of the queue,
     *  If adding the item fails (i.e., it isn't one of the items
     *  that are wanted by the order) then the price
     *  of the item is deducted from the current balance.
     */
    public void addItem(String item) {
        if(!orders.isEmpty()){//if orders not empy
            if(!orders.element().addItemToOrder(item)){// deduct price if not right item
                balance = balance - pricing.get(item);
            }
        }
    }

    /** As long as there is an order at the front of the queue and it is ready,
     *  take the first order off the queue, compute the price of the order,
     *  and update the total balance by adding the order price.
     *  If there is not a ready order on the queue, it prints a warning message.
     */
    public void deliverOrder() {
        if(!orders.isEmpty()){
            if(orders.peek().isReady()){
                balance = balance + orders.poll().getPrice(); // adds balance if order is ready
            }else if (orders.peek().isReady()){
                UI.println ("Order Not Ready on queue");
            }
        }
    }

    /** Draws the queue of orders on the Graphics pane.
     *  Also draws the current balance in the top left corner
     */
    public void drawOrders() {
        UI.clearGraphics();
        int count =0;// pacing incremetation
        for (Order o : orders){
            count=count+30;
            o.draw(count);
        }
        UI.drawString(Double.toString(balance), 10, 10);
    }

    // In the game version, the orders must be automatically generated.
    // The methods below will reset the queue and the current balance,
    // and will then set the gameRunning field to true. This will make
    // the run method start generating orders.
    // The run method is called from the main method, and therefore is in the main
    // thread, which executes concurrently with all the GUI buttons.
    // run  does nothing until the gameRunning field is set to be true
    // Once the gameRunning field is true, then it will generate orders automatically,
    // every timeBetweenOrders milliseconds. It will also makde the games speed up
    // gradually, by steadily reducing the timeBetweenOrders.
    // You do not need to write these methods code.

    private boolean gameRunning = false;
    private long timeBetweenOrders = 5000;

    private void startGame(){
        UI.clearGraphics();
        UI.clearText();
        orders.clear();
        balance = 0;
        timeBetweenOrders = 5000;
        gameRunning = true;
    }

    public void run() {
        long timeBetweenSpeedups = 2000;
        long timeNextOrder = 0;
        long timeNextSpeedup = 0;

        while (true) { // forever...
            UI.sleep(100); // Wait at least 100 milliseconds between actions.
            long now = System.currentTimeMillis();

            if (!gameRunning) 
                continue;  // if gameRunning is false, then don't generate orders

            // add another order, if the time has come
            if (now >= timeNextOrder) {
                timeNextOrder = now + timeBetweenOrders;
                generateOrder();
                drawOrders();
            }

            // speed up order generation, if the time has come
            if (now >= timeNextSpeedup) {   
                if (timeBetweenOrders> 200)    // as long as maximum speed has not been reached...
                    timeBetweenOrders -= 100;  // reduce interval
                timeNextSpeedup = now + timeBetweenSpeedups;
            }

            if (orders.size() > 20) {
                UI.println("Oh no! You have too many orders waiting! Game over...");
                orders.clear();
                gameRunning = false;
                break;
            }
        }
    }

    public static void main(String args[]) {
        FastFood ff = new FastFood();
    }
}
