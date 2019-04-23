/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the dwadtemplate in the editor.
 */
package com.mycompany.services;

import com.mycompany.dto.Order;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class DisplayOrderResponse extends Response{

    List<Order> allOrders;


    public List<Order> getAllOrders() {
        return allOrders;
    }

    void setAllOrders(List<Order> allOrders) {
        this.allOrders = allOrders;
    }

    

    

   
    
}
