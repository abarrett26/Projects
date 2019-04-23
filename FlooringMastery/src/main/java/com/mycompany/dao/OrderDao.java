/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public interface OrderDao {
    public Order addOrder(Order toAdd ) throws OrderPersistenceException;
    public List<Order> getOrdersByDate ( LocalDate lookup) throws OrderPersistenceException;
    public Order editOrder(Order toEdit) throws OrderPersistenceException;
   // public void removeOrder (LocalDate date, int orderNumber) throws OrderPersistenceException ;
    public Order getOrderByOrderNumber(LocalDate date, int orderNumber)throws OrderPersistenceException;
    public boolean removeOrder(Order toRemove) throws OrderPersistenceException;
//    public boolean removeOrder(Order matching);
}
