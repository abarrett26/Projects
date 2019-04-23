/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;

/**
 *
 * @author alexbarrett
 */
public class ChangeReturnResponse extends Response {

   
    private Change returnedChange;
    

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
