/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

import com.vendingmachine.dtos.Item;

/**
 *
 * @author alexbarrett
 */
public class RemoveItemResponse extends Response {
    private Item removedItem;
    
    public Item getRemovedItem() { 
        return removedItem;
    }

    /**
     * @param removedItem the removedItem to set
     */
    public void setRemovedItem(Item removedItem) {
        this.removedItem = removedItem;
    }
    
}
