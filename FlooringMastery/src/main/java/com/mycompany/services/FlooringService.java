/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.dao.OrderDao;
import com.mycompany.dao.OrderPersistenceException;
import com.mycompany.dao.ProductDao;
import com.mycompany.dao.ProductPersistenceException;
import com.mycompany.dao.TaxDao;
import com.mycompany.dao.TaxPersistenceException;
import com.mycompany.dto.Order;
import com.mycompany.dto.Product;
import com.mycompany.dto.Taxes;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexbarrett
 */
public class FlooringService {

    OrderDao dao;
    ProductDao pDao;
    TaxDao tDao;

    public FlooringService(OrderDao dao, ProductDao pDao, TaxDao tDao) {
        this.dao = dao;
        this.pDao = pDao;
        this.tDao = tDao;
    }

//    public DisplayOrderResponse displayOrder(FlooringService toDisplay) {
//      
//    }
    public AddOrderResponse addOrder(Order toBuild) {
        AddOrderResponse toReturn = new AddOrderResponse();

        try {
            Product p = pDao.getProductbyName(toBuild.getProductName());
            Taxes t = tDao.getTaxbyState(toBuild.getStateAbbrevation());
            boolean success = true;
            String message = validate(toBuild);

            toBuild.setTaxRate(t.getTaxRate());
            toBuild.setLaborUnitCost(p.getLaborCostPerSquareFoot());
            toBuild.setMatUnitCost(p.getMaterialCostPerSquareFoot());

            Order created = dao.addOrder(toBuild);
            if (created == null) {
                toReturn.setSuccess(false);
                toReturn.setMessage("Failed to add Order.\n");
            } else {
                toReturn.setOrder(created);
                toReturn.setSuccess(true);
            }

        } catch (NullDataException |
                BeforePastDataException |
                EmptyDataException |
                LessThanException |
                ProductPersistenceException |
                TaxPersistenceException |
                invalidStateException |
                invalidProductException |
                OrderPersistenceException ex) {

            toReturn.setSuccess(false);
            toReturn.setMessage(ex.getMessage());
        }

        return toReturn;
    }

    public DisplayOrderResponse listOrders(LocalDate date) {
        DisplayOrderResponse toReturn = new DisplayOrderResponse();
        try {

            List<Order> allOrders = dao.getOrdersByDate(date);

            if (allOrders == null) {

                toReturn.setSuccess(false);
                toReturn.setMessage("Failed to retrieve Orders.");
            } else {
                toReturn.setSuccess(true);
                toReturn.setAllOrders(allOrders);
            }

        } catch (OrderPersistenceException ex) {
            toReturn.setSuccess(false);
            toReturn.setMessage(ex.getMessage());
        }
        return toReturn;
    }

    private String validate(Order toBuild) throws NullDataException, BeforePastDataException, EmptyDataException, LessThanException, invalidStateException, invalidProductException {
        boolean success = true;
        String message = null;
        if (toBuild == null) {
            throw new NullDataException("Order is null.. \n");
        }

        if (toBuild.getDate() == null) {
            throw new NullDataException("Order date is null.. \n");
        }
        if(toBuild.getDate().equals("")){
            throw new EmptyDataException("Please enter a valid date.");
        }
        if (toBuild.getDate().compareTo(LocalDate.now()) < 0) {
            throw new BeforePastDataException("Order is in the past...\n");
        }
        if (toBuild.getCustomerName().equals("")) {
            success = false;
            throw new EmptyDataException("You can't enter nothing for Customer Name.\n");
        }
        if (toBuild.getStateAbbrevation().equals("")) {
            success = false;
            throw new EmptyDataException("You can't enter nothing\n");
        }
        if (toBuild.getProductName().equals("")) {
            success = false;
            throw new EmptyDataException("You can't enter nothing\n");
        }
        if (toBuild.getArea().compareTo(BigDecimal.ZERO) <= 0) {
            throw new LessThanException("Must be greater than 0\n");
        }
        if (success
                && !(toBuild.getStateAbbrevation().equalsIgnoreCase("OH")
                || toBuild.getStateAbbrevation().equalsIgnoreCase("PA")
                || toBuild.getStateAbbrevation().equalsIgnoreCase("MI")
                || toBuild.getStateAbbrevation().equalsIgnoreCase("IN"))) {
            success = false;
            throw new invalidStateException("State Must be in either OH, PA, MI, IN. \n");
        }
        if (success
                && !(toBuild.getProductName().equalsIgnoreCase("Carpet")
                || toBuild.getProductName().equalsIgnoreCase("Laminate")
                || toBuild.getProductName().equalsIgnoreCase("Tile")
                || toBuild.getProductName().equalsIgnoreCase("Wood"))) {
            success = false;
            throw new invalidProductException("Product type must be Carpet, Laminate, Tile or Wood. \n");
        }

        return message;

    }

//    public EditOrderResponse listToEditOrders(LocalDate date) {
//        throw new UnsupportedOperationException("Not supported yet.\n"); //To change body of generated methods, choose Tools | Templates.
//    }
    public RemoveOrderResponse removeOrder(LocalDate date, int orderNumber) {
        RemoveOrderResponse response = new RemoveOrderResponse();
        try {

            List<Order> allOrders = dao.getOrdersByDate(date);

            boolean isValid = validateOrderNumber(orderNumber, allOrders);

            if (isValid) {
                Order matching = dao.getOrderByOrderNumber(date, orderNumber);
                boolean success = dao.removeOrder(matching);
                if (success) {
                    response.setSuccess(true);
                    response.setRemovedOrder(matching);
                } else {
                    response.setSuccess(false);
                    response.setMessage("Failed to Remove Order.");
                }
            } else {
                response.setSuccess(false);
                response.setMessage("Invalid Order #.");
            }

        } catch (OrderPersistenceException ex) {
            response.setSuccess(true);
            response.setMessage("could not retrieve orders for date.");
        }
        return response;
    }

    private boolean validateOrderNumber(int orderNumber, List<Order> allOrders) {
        boolean isValid = false;

        for (Order toCheck : allOrders) {
            if (orderNumber == toCheck.getOrderNumber()) {
                isValid = true;
                break;
            }
        }

        return isValid;

    }

    public EditOrderResponse editOrder(Order toEdit) {
        EditOrderResponse response = new EditOrderResponse();
        try {
            String message = validate(toEdit);

            
            Product p = pDao.getProductbyName(toEdit.getProductName());
            Taxes t = tDao.getTaxbyState(toEdit.getStateAbbrevation());
            boolean success = true;
            
            toEdit.setTaxRate(t.getTaxRate());
            toEdit.setLaborUnitCost(p.getLaborCostPerSquareFoot());
            toEdit.setMatUnitCost(p.getMaterialCostPerSquareFoot());

            if (success) {

                Order edited = dao.editOrder(toEdit);
                if (edited == null) {
                    response.setMessage("Error: could not edit movie.");
                    response.setSuccess(false);
                } else {
                    response.setEditedOrder(edited);
                    response.setSuccess(true);
                }

            } else {
                response.setSuccess(false);
                response.setMessage(message);
            }

        } catch (NullDataException |
                BeforePastDataException |
                EmptyDataException |
                LessThanException |
                ProductPersistenceException |
                TaxPersistenceException |
                invalidStateException |
                invalidProductException |
                OrderPersistenceException ex) {

            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
}

//    public  DisplayOrderResponse displayOrder(Order toDisplay){
//        
//      
//    }

