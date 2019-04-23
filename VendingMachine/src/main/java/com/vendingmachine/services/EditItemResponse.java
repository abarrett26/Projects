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
public class EditItemResponse extends Response{
    private Item toEdit;

    /**
     * @return the toEdit
     */
    public Item getToEdit() {
        return toEdit;
    }

    /**
     * @param toEdit the toEdit to set
     */
    public void setToEdit(Item toEdit) {
        this.toEdit = toEdit;
    }
    
}
