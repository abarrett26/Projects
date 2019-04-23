/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.OrderDao;
import com.mycompany.dao.OrderPersistenceException;
import com.mycompany.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alexbarrett
 */
public class InMemoryOrderDao implements OrderDao{
    Map<LocalDate, List<Order>> allOrders = new HashMap<>();
    
    
    public InMemoryOrderDao(){
    
    List<Order> firstOrder = new ArrayList<>();
    List<Order> secondOrder = new ArrayList<>();
    
    Order numberOneOrder = new Order();
    numberOneOrder.setDate(LocalDate.of(2020, 01, 01));
    numberOneOrder.setOrderNumber(1);
    numberOneOrder.setCustomerName("Alex");
    numberOneOrder.setStateAbbrevation("OH");
    numberOneOrder.setTaxRate(new BigDecimal("6.25"));
    numberOneOrder.setProductName("CARPET");
    numberOneOrder.setArea(new BigDecimal(100));
    numberOneOrder.setMatUnitCost(new BigDecimal("2.25"));
    numberOneOrder.setLaborUnitCost(new BigDecimal("2.10"));
    firstOrder.add(numberOneOrder);
    allOrders.put(numberOneOrder.getDate(), firstOrder);
    
    Order numberTwoOrder = new Order();
    numberTwoOrder.setDate(LocalDate.of(2021, 01, 01));
    numberTwoOrder.setOrderNumber(1);
    numberTwoOrder.setCustomerName("Bob");
    numberTwoOrder.setStateAbbrevation("PA");
    numberTwoOrder.setTaxRate(new BigDecimal("6.75"));
    numberTwoOrder.setProductName("TILE");
    numberTwoOrder.setArea(new BigDecimal(200));
    numberTwoOrder.setMatUnitCost(new BigDecimal("3.50"));
    numberTwoOrder.setLaborUnitCost(new BigDecimal("4.15"));
    secondOrder.add(numberTwoOrder);
    allOrders.put(numberTwoOrder.getDate(), secondOrder);
    
    
    
    
    
    
}
    @Override
    public Order addOrder(Order toAdd) throws OrderPersistenceException {
        
        List<Order> ordersForDate = getOrdersByDate(toAdd.getDate());
        int orderNumber = createOrderNumber(ordersForDate);
        toAdd.setOrderNumber(orderNumber);
        
        if(!allOrders.containsKey(toAdd.getDate())){
            allOrders.put(toAdd.getDate(), new ArrayList<>());
        } 
         allOrders.get(toAdd.getDate()).add(toAdd);
         
         return toAdd;        
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate lookup) throws OrderPersistenceException {
         List<Order> allOrdersDate = new ArrayList<>();
 
       if (allOrders.containsKey(lookup)) {
           allOrdersDate = allOrders.get(lookup);
       }
 
       return allOrdersDate;
    }

    @Override
    public Order editOrder(Order toEdit) throws OrderPersistenceException {
        
        
        List<Order> allOrders = getOrdersByDate(toEdit.getDate());
        int index = Integer.MIN_VALUE;
        int id = toEdit.getOrderNumber();
        for(int i = 0; i < allOrders.size(); i++){
            Order toCheck = allOrders.get(i);
            if(toCheck.getOrderNumber() == id){
                index = i;
                break;
            }
        }
        if(index >= 0 && index < allOrders.size()){
            
        allOrders.set(index, toEdit);

        }
         
         return toEdit;   
    }
    

    
    @Override
    public Order getOrderByOrderNumber(LocalDate date, int orderNumber) throws OrderPersistenceException {
        Order toReturn = null;

            List<Order> allOrdersNumber = getOrdersByDate(date);
            for (Order toCheck : allOrdersNumber) {
                if (toCheck.getOrderNumber() == orderNumber) {
                    toReturn = toCheck;
                    break;
                }
            }
            return toReturn;
    }

    @Override
    public boolean removeOrder(Order toRemove) throws OrderPersistenceException {
        List<Order> ordersForRemove = getOrdersByDate(toRemove.getDate());
        
        int orderNumber = toRemove.getOrderNumber();
        int index = Integer.MIN_VALUE;
        
        for(int i = 0; i < ordersForRemove.size(); i++){
            Order toCheck = ordersForRemove.get(i);
            if(toCheck.getOrderNumber()==orderNumber){
                index = i;
                break;
            }
        }
        ordersForRemove.remove(index);
        boolean success = true;
        return success;
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
    
}
