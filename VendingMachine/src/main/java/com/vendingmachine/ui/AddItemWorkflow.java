/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.ui;

import com.vendingmachine.daos.PersistenceException;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.services.VendingService;

/**
 *
 * @author alexbarrett
 */
public class AddItemWorkflow  {
    VendingService service;
    
    public AddItemWorkflow(VendingService service){
        this.service = service;
    }
    public static void run(ConsoleIO ui, VendingService service) throws PersistenceException{
      String itemName = ui.readString("Please enter name of item: ");
      int quantity = ui.readInt("Please enter quantity of item: ");
      int priceInPennies = ui.readInt("Please enter cost of item in pennies: ");
      
      service.addItem(itemName, quantity, priceInPennies);
    }
}
