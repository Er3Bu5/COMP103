// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 8
 * Name:
 * Usercode:
 * ID:
 */
/** 
 * Represents information about a pizza that has to be delivered.
 * 
 * Pizzas are comparable (in terms of their delivery deadlines).
 * 
 */

public class Pizza implements Comparable<Pizza> {
    static final double             HandlingCost = 1.5;             // dollars
    static final double             PerMinuteDeliveryCost = 0.25;   // dollars 

    static private final double     UrgentPrice = 20;               // dollars
    static private final double     StandardPrice = 10;             // dollars
    static private final double     LatePenaltyFactor = 2;          // refund factor
    static private final int        UrgentDeliveryMinutes = 30;     // minutes   
    static private final int        StandardDeliveryMinutes = 120;  // minutes

    String destination;   // place to deliver to
    int deliveryTime;     // time it will take to deliver the pizza
    int orderedTime;      // the time when the pizza was ordered
    boolean isUrgent;     // is the delivery urgent?

    /**
     * Constructs a new Pizza object
     * 
     * @param destination - the name of the destination  
     * @param deliveryTime - the time it takes to deliver this pizza
     * @param orderedTime - the time stamp when the order for this pizza came in
     * @isUrgent - is true when the pizza has been ordered as being "urgent"
     */
    public Pizza(String destination, int deliveryTime, int orderedTime, boolean isUrgent) {
        this.destination = destination;
        this.deliveryTime = deliveryTime;
        this.orderedTime = orderedTime;
        this.isUrgent = isUrgent;
    }

    public String destination()   {return destination;}

    public int deadline()           {return orderedTime + (isUrgent ? UrgentDeliveryMinutes : StandardDeliveryMinutes);}

    public int deliveryTime()     {return deliveryTime;}

    public int orderedTime()    {return orderedTime;}

    public boolean isUrgent()  {return isUrgent;}

    /**
     * Pizza price.
     * 
     * @returns the price of the pizza, depending on whether it is an urgent delivery or not. 
     */
    public double price() {
        return isUrgent ? UrgentPrice : StandardPrice;
    }

    /**
     * Pizza price if delivered lated.
     * 
     * @returns the prize of the pizza, multiplied by the 'late delivery' penalty factor.
     */
    public double latePrice() {
        return LatePenaltyFactor * this.price();
    }

    /** 
     * Compares two pizzas in terms of their deadline.
     * 
     * This pizza is comes before another other pizza in an ordering if
     * its deadline is earlier.
     */
    public int compareTo(Pizza other) {
        return (this.deadline() - other.deadline());
    }

    public String toString () {
        return "Pizza received:" + orderedTime + " to "+destination +" by "+this.deadline()+(isUrgent?"(urgent) ":" ")+ deliveryTime+ "mins";
    }
}
