/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui.views;

import com.mycompany.dao.OrderDao;
import com.mycompany.dto.Order;
import com.mycompany.services.AddOrderResponse;

import com.mycompany.services.FlooringService;

import com.mycompany.ui.ConsoleIO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexbarrett
 */
public class AddOrderWorkflow {

    FlooringService service;

    public AddOrderWorkflow(FlooringService service) {
        this.service = service;

    }

    public void run(ConsoleIO ui) {

        Order toBuild = new Order();
        LocalDate chosenDate = getChosenDate(ui);
        String customerName = getCustomerName(ui);
        String state = getState(ui);
        String productType = getProductType(ui);
        BigDecimal area = getArea(ui);
        // BigDecimal taxRate = getTaxRate(ui);

        toBuild.setDate(chosenDate);
        toBuild.setCustomerName(customerName);
        toBuild.setStateAbbrevation(state);
        toBuild.setProductName(productType);
        toBuild.setArea(area);

        AddOrderResponse response = service.addOrder(toBuild);

        if (response.isSuccess()) {
            Order returnedOrder = response.getOrder();
            displayOrderDetails(returnedOrder, ui);
        } else {
            ui.print("Error: " + response.getMessage());
        }
    }

    private int getOrderNumber(ConsoleIO ui) {
        int order;

        order = ui.readInt("Please enter order: ");

        return order;
    }

    private String getCustomerName(ConsoleIO ui) {
        String customerName;

        do {
            customerName = ui.readString("Please enter Customer Name: ");
        } while (customerName.equals(""));

        return customerName;

    }

    private String getState(ConsoleIO ui) {
        String state;

        do {
            state = ui.readString("Please enter State Name(OH,PA, MI,IN): ");
        } while (state.equals(""));

        return state;
    }

//    private BigDecimal getTaxRate(ConsoleIO ui) {
//        BigDecimal taxRate;
//        
//        do{
//            taxRate = ui.readBigDecimal("Please enter Area: ");
//        } while (taxRate.equals( "" ));
//        return taxRate;
//    }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    private String getProductType(ConsoleIO ui) {
        String product;

        do {
            product = ui.readString("Please enter Product type(Carpet, Laminate, Tile or Wood): ");
        } while (product.equals(""));
        return product;
    }

    private BigDecimal getArea(ConsoleIO ui) {
        BigDecimal area;

        do {
            area = ui.readBigDecimal("Please enter Area: ");
        } while (area.equals(""));
        return area;
    }

    private LocalDate getChosenDate(ConsoleIO ui) {
        LocalDate date;

        do {
            date = ui.readDate("Please enter Order Date: ");

        } while (date.equals(""));
        return date;

    }

    private void displayOrderDetails(Order returnedOrder, ConsoleIO ui) {
        ui.print("==================================" + "\n");
        ui.print("=====ORDER ADDED=====\n");
        ui.print("===NEW ORDER DETAILS===" + "\n");
        ui.print("Order Number: " + returnedOrder.getOrderNumber() + "\n");
        ui.print("Customer Name: " + returnedOrder.getCustomerName() + "\n");
        ui.print("State: " + returnedOrder.getStateAbbrevation() + "\n");
        ui.print("Product Type: " + returnedOrder.getProductName() + "\n");
        ui.print("Area: " + returnedOrder.getArea() + "ft2" +"\n");
        ui.print("==================================" + "\n");

    }

}
