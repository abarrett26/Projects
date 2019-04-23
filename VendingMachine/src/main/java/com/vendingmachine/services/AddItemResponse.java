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
public class AddItemResponse extends Response {
    private Item addedItem;

    /**
     * @return the addedItem
     */
    public Item getAddedItem() {
        return addedItem;
    }

    /**
     * @param addedItem the addedItem to set
     */
    public void setAddedItem(Item addedItem) {
        this.addedItem = addedItem;
    }
    
}
