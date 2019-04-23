/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui.views;

import com.mycompany.dto.Order;
import com.mycompany.services.DisplayOrderResponse;
import com.mycompany.services.FlooringService;
import com.mycompany.services.RemoveOrderResponse;
import com.mycompany.ui.ConsoleIO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class RemoveOrderWorkflow {

    FlooringService service;

    public RemoveOrderWorkflow(FlooringService service) {
        this.service = service;
    }

    public void run(ConsoleIO ui) {

        LocalDate date = ui.readDate("Please enter Date: ");
        DisplayOrderResponse removeResponse = service.listOrders(date);

        if(removeResponse.isSuccess()){
            if(removeResponse.getAllOrders().size() > 0){

                    List<Order> allOrders = removeResponse.getAllOrders();

                    int orderNumber = getOrderNumberToRemove(ui, allOrders);

                    RemoveOrderResponse response = service.removeOrder(date, orderNumber);

                    if (response.isSuccess()) {
                        ui.print("* * * * * * * * * * * * * *" + "\n");
                        ui.print("===REMOVED ORDER===" + "\n");
                        ui.print("Removed Order Number " + "#" + orderNumber + "\n");
                        
 
                    } else {
                        ui.print("Error: " + response.getMessage() + "\n");
                    }
                }
            }
        
    }

    private int getOrderNumberToRemove(ConsoleIO ui, List<Order> allOrders) {
        int toReturn = Integer.MIN_VALUE;

        boolean found = false;

        while (!found) {
            int orderNumber = ui.readInt("Please enter the Order Number to remove: ");

            for (Order toCheck : allOrders) {

                found = toCheck.getOrderNumber() == orderNumber;
                if (found) {

                    toReturn = toCheck.getOrderNumber();
                    break;
                }
            }
        }

        return toReturn;
    }

}
