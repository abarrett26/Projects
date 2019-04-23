/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexbarrett
 */
public class OrderFileDao implements OrderDao {

    String directory;

    public OrderFileDao(String directory) {
        this.directory = directory;

    }

    @Override
    public Order addOrder(Order toAdd) throws OrderPersistenceException {
        Order toReturn = toAdd;

        List<Order> allOrders = getOrdersByDate(toAdd.getDate());

        int newOrder = createOrderNumber(allOrders);

        toAdd.setOrderNumber(newOrder);

        allOrders.add(toAdd);

        boolean success = writeFile(allOrders, toAdd.getDate());

        if (!success) {
            toReturn = null;
        }

        return toReturn;

    }

    @Override
    public List<Order> getOrdersByDate(LocalDate lookup) {
        List<Order> ordersForDate = new ArrayList<Order>();
        try {
            String path = dateToPath(lookup);

            Scanner reader = new Scanner(new BufferedReader(new FileReader(path)));
            //this will skip header Row
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] cells = line.split(",");

                Order toBuild = new Order();
                toBuild.setDate(lookup);
                toBuild.setOrderNumber(Integer.parseInt(cells[0]));
                toBuild.setCustomerName(cells[1]);
                toBuild.setStateAbbrevation(cells[2]);
                toBuild.setTaxRate(new BigDecimal(cells[3]));
                toBuild.setProductName(cells[4]);
                toBuild.setArea(new BigDecimal(cells[5]));
                toBuild.setMatUnitCost(new BigDecimal(cells[6]));
                toBuild.setLaborUnitCost(new BigDecimal(cells[7]));

                ordersForDate.add(toBuild);

            }

        } catch (FileNotFoundException ex) {

            //do nothing here...this is very normal
            //the user could pick a date with no orders
        }

       

        return ordersForDate;
    }

    @Override
    public Order editOrder(Order toEdit) throws OrderPersistenceException {
        Order toReturn = null; //orNull??????

        List<Order> allOrders = getOrdersByDate(toEdit.getDate());

        int index = Integer.MIN_VALUE;

        int id = toEdit.getOrderNumber();

        for (int i = 0; i < allOrders.size(); i++) {
            Order toCheck = allOrders.get(i);
            if (toCheck.getOrderNumber() == id) {
                index = i;
                break;

            }
        }
        if (index >= 0 && index < allOrders.size()) {

            allOrders.set(index, toEdit);
            boolean success = writeFile(allOrders, toEdit.getDate());
            if (success) {
                toReturn = toEdit;
            }

        }
        return toReturn;
    }

    @Override
    public boolean removeOrder(Order toRemove) throws OrderPersistenceException {
        
        
        
       List<Order> allOrders = getOrdersByDate(toRemove.getDate());

        
        int orderNumber = toRemove.getOrderNumber();
        int index = Integer.MIN_VALUE;

        for (int i = 0; i < allOrders.size(); i++) {
            Order toCheck = allOrders.get(i);
            if (toCheck.getOrderNumber() == orderNumber) {

                index = i;
                break;
            }
        }
            if(index < 0 || index >= allOrders.size()){
                throw new OrderPersistenceException("COULD NOT FIND INDEX... ");
            } else {
                allOrders.remove(index);
            }
        

        
       

        boolean success = writeFile(allOrders, toRemove.getDate());

        return success;
    }

    private String dateToPath(LocalDate date) {
        String convertedDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fileName = "Orders_" + convertedDate + ".txt";
        String path = directory + "/" + fileName;
        return path;

    }

    private int createOrderNumber(List<Order> allOrders) {
        int toReturn = Integer.MIN_VALUE;

        if (allOrders.isEmpty()) {
            toReturn = 1;

        } else {
            for (Order toCheck : allOrders) {
                if (toCheck.getOrderNumber() > toReturn) {
                    toReturn = toCheck.getOrderNumber();
                }
            }
            toReturn++;
        }
        return toReturn;
    }

    private boolean writeFile(List<Order> allOrders, LocalDate date) throws OrderPersistenceException {
        PrintWriter pw = null;

        String path = dateToPath(date);
        boolean success = false;
        try {

            pw = new PrintWriter(new FileWriter(path));

            pw.println();
            for (Order toWrite : allOrders) {
                String line = orderToLine(toWrite);
                pw.println(line);
            }
            pw.flush();
            pw.close();
            success = true;

        } catch (IOException ex) {
            throw new OrderPersistenceException("could not write to file: " + path);
        } finally {
            pw.close();
        }
        return success;
    }

    private String orderToLine(Order toWrite) {
        String toReturn
                = toWrite.getOrderNumber() + ","
                + toWrite.getCustomerName() + ","
                + toWrite.getStateAbbrevation() + ","
                + toWrite.getTaxRate() + ","
                + toWrite.getProductName() + ","
                + toWrite.getArea() + ","
                + toWrite.getMatUnitCost() + ","
                + toWrite.getLaborUnitCost() + ","
                + toWrite.getTotalMatCost() + ","
                + toWrite.getTotalLaborCost() + ","
                + toWrite.getTotalTax()+ ","
                + toWrite.getOrderTotal();

        return toReturn;
    }

    @Override
    public Order getOrderByOrderNumber(LocalDate date, int orderNumber) throws OrderPersistenceException {

        
            Order toReturn = null;

            List<Order> allOrders = getOrdersByDate(date);
            for (Order toCheck : allOrders) {
                if (toCheck.getOrderNumber() == orderNumber) {
                    toReturn = toCheck;
                    break;
                }
            }
            return toReturn;
         
    }

}



//OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSqureFoot,MaterialCost,LaborCost,Tax,Total
