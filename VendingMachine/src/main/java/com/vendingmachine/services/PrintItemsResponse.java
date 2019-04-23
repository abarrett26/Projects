/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

import com.vendingmachine.dtos.Item;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class PrintItemsResponse extends Response {
    private List<Item> allItems;

 
    public List<Item> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<Item> allItems) {
        this.allItems = allItems;
    }
    
    
}
