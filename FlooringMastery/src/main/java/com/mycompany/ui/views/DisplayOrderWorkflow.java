/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui.views;

import com.mycompany.dto.Order;
import com.mycompany.services.DisplayOrderResponse;
import com.mycompany.services.FlooringService;
import com.mycompany.ui.ConsoleIO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class DisplayOrderWorkflow {

    FlooringService service;

    public DisplayOrderWorkflow(FlooringService service) {
        this.service = service;
    }

    public void run(ConsoleIO ui) {
        LocalDate date = ui.readDate("Please enter Date: ");
        DisplayOrderResponse response = service.listOrders(date);
       
   //     LocalDate chosenDate = service.validateDate();
        if (response.isSuccess()) {
            
            List<Order> allOrders = response.getAllOrders();
            
            for (Order order : allOrders) {
                ui.print("==================================\n");
                ui.print("====DISPLAYING ORDER(S)====" + "\n");
                ui.print("Order Number: " + order.getOrderNumber() + "\n");
                ui.print("Customer Name: " + order.getCustomerName()+ "\n");
                ui.print("State Abbrevation: " + order.getStateAbbrevation() + "\n");
                ui.print("State Tax: $" + order.getTaxRate() + "\n");
                ui.print("Product Type: " + order.getProductName() + "\n");
                ui.print("Area: " + order.getArea() + "ft2" +"\n");
                ui.print("Total material cost: $" + order.getTotalMatCost()+ "\n");
                ui.print("Total labor cost: $" + order.getTotalLaborCost()+ "\n");
                ui.print("Total Tax: $" + order.getTotalTax()+ "\n");
                ui.print("Order Total: $" + order.getOrderTotal() + "\n" +
                        "==================================\n");
                
            }
        } else {
            String failureMessage = response.getMessage();
            ui.print(failureMessage);
        }

    }
}
