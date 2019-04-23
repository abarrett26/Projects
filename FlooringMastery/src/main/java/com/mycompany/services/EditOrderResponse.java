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
public class EditOrderResponse extends Response{
    private Order editedOrder;
    

    /**
     * @return the editedOrder
     */
    public Order getEditedOrder() {
        return editedOrder;
    }

    /**
     * @param editedOrder the editedOrder to set
     */
    public void setEditedOrder(Order editedOrder) {
        this.editedOrder = editedOrder;
    }
    
}
