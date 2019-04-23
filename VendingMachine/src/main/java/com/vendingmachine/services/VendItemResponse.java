/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class VendItemResponse extends Response {
    private Item vendedItem;
    private Change returnedChange;

    /**
     * @return the vendedItem
     */
    public Item getVendedItem() {
        return vendedItem;
    }

    /**
     * @param vendedItem the vendedItem to set
     */
    public void setVendedItem(Item vendedItem) {
        this.vendedItem = vendedItem;
    }

    /**
     * @return the returnedChange
     */
    public Change getReturnedChange() {
        return returnedChange;
    }

    /**
     * @param returnedChange the returnedChange to set
     */
    public void setReturnedChange(Change returnedChange) {
        this.returnedChange = returnedChange;
    }
    
}
