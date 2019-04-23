/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.dto.Order;

/**
 *
 * @author alexbarrett
 */
public class AddOrderResponse extends Response {
    Order order;
    
    public Order getOrder(){
        return order;
    }    
    void setOrder(Order created) {
        this.order = created;
    }
    
}
