/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui.views;

import com.mycompany.dto.Order;
import com.mycompany.services.DisplayOrderResponse;
import com.mycompany.services.EditOrderResponse;
import com.mycompany.services.FlooringService;
import com.mycompany.ui.ConsoleIO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class EditOrderWorkflow {

    FlooringService service;

    public EditOrderWorkflow(FlooringService service) {
        this.service = service;
    }

    public void run(ConsoleIO ui) {
        LocalDate date = ui.readDate("Please enter Date: ");
        DisplayOrderResponse response = service.listOrders(date);

        if (response.isSuccess()) {
            if (response.getAllOrders().size() > 0) {
                Order toEdit = getOrderToEditByNumber(ui, response.getAllOrders());

                String newName = ui.readString("Enter new Name(Enter blank to keep: " + toEdit.getCustomerName() + ") ");
                if (!newName.isEmpty()) {
                    toEdit.setCustomerName(newName);
                }
                String newStateAbr = ui.readString("Enter new State(Enter blank to keep: " + toEdit.getStateAbbrevation() + ") ");
                if (!newStateAbr.isEmpty()) {
                    toEdit.setStateAbbrevation(newStateAbr);
                }
                String newProduct = ui.readString("Enter new product type(Enter blank to keep: " + toEdit.getProductName() + ") ");
                if (!newProduct.isEmpty()) {
                    toEdit.setProductName(newProduct);
                }

                BigDecimal newAreaMat = ui.editBigDecimal("Enter new Area:(blank to keep: " , toEdit.getArea());
                toEdit.setArea(newAreaMat);

                EditOrderResponse editResponse = service.editOrder(toEdit);

                if (editResponse.isSuccess()) {
                    Order editedOrder = editResponse.getEditedOrder();
                    displayOrder(ui, editedOrder);
                } else {
                    ui.print("Error: " + editResponse.getMessage());
                }
            }
        }

    }

    private Order getOrderToEditByNumber(ConsoleIO ui, List<Order> allOrders) {
        Order toReturn = null;

        if (allOrders.size() > 0) {
            boolean found = false;
            while (!found) {
                int orderNumber = ui.readInt("Please enter the Order Number to Edit: ");
                for (Order toCheck : allOrders) {
                    if (orderNumber == toCheck.getOrderNumber()) {
                        toReturn = toCheck;
                        found = true;
                        break;
                    }

                }
            }
        }

        return toReturn;
    }

    private void displayOrder(ConsoleIO ui, Order editedOrder) {
        ui.print("==================================" + "\n");
        ui.print("=====ORDER ADDED=====\n");
        ui.print("===NEW ORDER DETAILS===" + "\n");
        ui.print("Order Number: " + editedOrder.getOrderNumber() + "\n");
        ui.print("Customer Name: " + editedOrder.getCustomerName() + "\n");
        ui.print("State: " + editedOrder.getStateAbbrevation() + "\n");
        ui.print("Product Type: " + editedOrder.getProductName() + "\n");
        ui.print("Area: " + editedOrder.getArea() + "ft2" +"\n");
        ui.print("==================================" + "\n");
    }
}
