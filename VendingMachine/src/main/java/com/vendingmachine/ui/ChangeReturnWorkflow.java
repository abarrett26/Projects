/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.ui;

import com.vendingmachine.dtos.Change;
import com.vendingmachine.services.ChangeReturnResponse;
import com.vendingmachine.services.VendItemResponse;
import com.vendingmachine.services.VendingService;

public class ChangeReturnWorkflow {

    public static void run(ConsoleIO ui, VendingService service) {
        ChangeReturnResponse response = service.returnedChange();
        if (response.isSuccess()) {
            Change returnedChange = response.getReturnedChange();
            ui.print("Here is your change: "
                    + "  Dollars: " + response.getReturnedChange().getDollars()
                    + "  Quarters: " + response.getReturnedChange().getQuarters()
                    + "  Dimes: " + response.getReturnedChange().getDimes()
                    + "  Nickels: " + response.getReturnedChange().getNickels()
                    + "  Pennies: " + response.getReturnedChange().getPennies() + "\n");

        }

    }
}