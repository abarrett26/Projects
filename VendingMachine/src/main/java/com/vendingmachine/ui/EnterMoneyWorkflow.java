/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.ui;

import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.dtos.VendingMachine;
import com.vendingmachine.services.AddMoneyResponse;
import com.vendingmachine.services.VendingService;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author dsmelser
 */
public class EnterMoneyWorkflow {

    public static BigDecimal run(ConsoleIO ui, VendingService service) {

       
        
        ui.print("1. Enter 1 Dollar\n");
        ui.print("2. Enter 1 Quarter\n");
        ui.print("3. Enter 1 Dime\n");
        ui.print("4. Enter 1 Nickle\n");
        ui.print("5. Enter 1 Penny\n");
       
        
        int userChoice = ui.readInt("Please select a choice between 1 and 5: ", 1, 5);
        AddMoneyResponse response = null;
       switch( userChoice ){
           case 1:
               response = service.enterDollar();
               break;
           case 2:
               response = service.enterQuarter();
               break;
           case 3:
               response = service.enterDime();
               break;
           case 4:
               response = service.enterNickel();
               break;
           case 5:
               response = service.enterPenny();
               break;
           
                   
       }
       
     //  ui.print("Total money entered: " + response.getTotalMoneyEntered() + "\n");
        return response.getTotalMoneyEntered();

    }
}
