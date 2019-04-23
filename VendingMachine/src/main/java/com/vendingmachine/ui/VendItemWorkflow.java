/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.ui;

import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.services.PrintItemsResponse;
import com.vendingmachine.services.VendItemResponse;
import com.vendingmachine.services.VendingService;
import java.util.List;

/**
 *
 * @author dsmelser
 */
public class VendItemWorkflow {

    public static void run(ConsoleIO ui, VendingService service) {
        PrintItemsResponse response = service.getAllItems();

        if (response.isSuccess()) {
            Item foundItem = null;
            while (foundItem == null) {
                int id = ui.readInt("Please list ID of Item.\n");
                List<Item> allItems = response.getAllItems();
                for (Item currentItem : allItems) {
                    if (id == currentItem.getId()) {
                        foundItem = currentItem;
                        break;

                    }

                }

            }
            VendItemResponse vendResponse = service.vendItem(foundItem.getId());
            if(vendResponse.isSuccess()){
                Item vendedItem = vendResponse.getVendedItem();
                Change returnedChange = vendResponse.getReturnedChange();
                
                ui.print("Here is your Item: " + vendedItem.getName() + "\n");
                ui.print("Here is your change: " + "Dollars: " + returnedChange.getDollars() + "  Quarters: " + returnedChange.getQuarters() +
                        "  Dimes:" + returnedChange.getDimes()  + "  Nickels: " + returnedChange.getNickels() +  "  Pennies: "+ returnedChange.getPennies() + "\n" );
            } else {
                ui.print("Error: " + response.getMessage() + "\n");
            }
           

        }
    }
}
