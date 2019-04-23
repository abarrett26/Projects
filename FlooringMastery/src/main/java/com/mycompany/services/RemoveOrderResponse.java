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
public class RemoveOrderResponse extends Response{
    private Order removedOrder;

    /**
     * @return the removedOrder
     */
    public Order getRemovedOrder() {
        return removedOrder;
    }

    /**
     * @param removedOrder the removedOrder to set
     */
    public void setRemovedOrder(Order removedOrder) {
        this.removedOrder = removedOrder;
    }
    
     
}
