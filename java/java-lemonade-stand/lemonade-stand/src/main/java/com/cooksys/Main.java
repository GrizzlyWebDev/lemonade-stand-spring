package com.cooksys;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cooksys.model.Customer;
import com.cooksys.model.Lemonade;
import com.cooksys.model.LemonadeStand;
import com.cooksys.model.Order;

public class Main {
    public static void main(String[] args) {
        Customer phill = new Customer("Phill", "518-369-5460");
        Customer joe = new Customer("Joe", "518-369-1320");

        Order order1 = new Order(phill);

        order1.addLemonade(new Lemonade(5, 1, 1, .5));
        order1.addLemonade(new Lemonade(5, 1, 1, .5));
        order1.addLemonade(new Lemonade(5, 1, 1, .5));
        order1.addLemonade(new Lemonade(5, 1, 1, .5));

        // System.out.println("Customer 1: " + order1.getCustomer().getName());
        // System.out.println("Order 1 total: " + order1.getTotal());
        // System.out.println(order1.toString());

        Order order2 = new Order(joe);

        order2.addLemonade(new Lemonade(5, 1, 1, 2.5));
        order2.addLemonade(new Lemonade(5, 3, 1, .5));
        order2.addLemonade(new Lemonade(5, 1, 4, .5));
        order2.addLemonade(new Lemonade(5, 1, 1, .5)); 

        // System.out.println("Customer 2: " + order2.getCustomer().getName());
        // System.out.println("Order 2 total: " + order2.getTotal());
        // System.err.println(order2.toString());

    //     Box<Order> b1 = new Box(order1);
    //     Box<Customer> b2 = new Box(joe);

    //     b1.setObj(order2);

    //     b1.getObj().addLemonade(new Lemonade(5, 1, 1, 2.5));

    //     b2.setObj(phill);

    //    /*  System.out.println(b1.getObj()); */
    //     System.out.println(b2.getObj());
    //     b2.printValue(phill, order2);

    //     Box<Integer> b3 = new Box(1);  

        LemonadeStand lemonadeStand1 = new LemonadeStand("Brads First stand");
        LemonadeStand lemonadeStand2 = new LemonadeStand("Brads Second stand");

        Map<LemonadeStand, List<Order>> lemonadeStandOrders = new HashMap<>();

        lemonadeStandOrders.put(lemonadeStand1, Arrays.asList(new Order[] {order1, order2}));

        lemonadeStandOrders.put(lemonadeStand2, Arrays.asList(new Order[] {order2}));
    
        System.out.println(lemonadeStandOrders.get(lemonadeStand1));

        System.out.println(lemonadeStandOrders.keySet());
    }
}