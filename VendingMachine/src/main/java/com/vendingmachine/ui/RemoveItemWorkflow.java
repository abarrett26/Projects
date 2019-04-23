/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.ui;

import com.vendingmachine.daos.PersistenceException;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.services.PrintItemsResponse;
import com.vendingmachine.services.RemoveItemResponse;
import com.vendingmachine.services.VendingService;
import java.util.List;

/**
 *
 * @author alexbarrett
 */

public class RemoveItemWorkflow {
    
   VendingService service;
   
   public RemoveItemWorkflow(VendingService service){
       this.service = service;
   }
   
   public static void run(ConsoleIO ui, VendingService service) throws PersistenceException{
       PrintItemsResponse allItemsResponse = service.getAllItems();
       
       if(!allItemsResponse.isSuccess()){
           ui.print("Error: " + allItemsResponse.getMessage());
       } else {
           List<Item> allItems = allItemsResponse.getAllItems();
           int itemId = getItemIdToRemove(ui, allItems);
           RemoveItemResponse response = service.removeItem(itemId);
           if (response.isSuccess()){
               Item removedItem = response.getRemovedItem();
               ui.print("This Item was removed: " + removedItem.getName());
           } else {
               ui.print("Error not removed: " + response.getMessage());
           }
                   
       }
               
   }

    private static int getItemIdToRemove(ConsoleIO ui, List<Item> allItems) {
        int toReturn = Integer.MIN_VALUE;
        
        boolean found = false;
        
        while(!found){
            toReturn = ui.readInt("Enter ID to remove: ");
            
            for(Item toCheck : allItems){
                if(toCheck.getId() == toReturn){
                    found = true;
                    break;
                }
                    
            }
        }
       return toReturn;
    }
    
   
}
