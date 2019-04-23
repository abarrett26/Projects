/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.OrderDao;
import com.mycompany.dao.OrderPersistenceException;
import com.mycompany.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class OrderAlwaysFailDao implements OrderDao{

    @Override
    public Order addOrder(Order toAdd) throws OrderPersistenceException {
        throw new OrderPersistenceException("Failed to retrieve data");
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate lookup) throws OrderPersistenceException {
        throw new OrderPersistenceException("Failed to retrieve data");
    }

    @Override
    public Order editOrder(Order toEdit) throws OrderPersistenceException {
        throw new OrderPersistenceException("Failed to retrieve data");
    }

    

    @Override
    public Order getOrderByOrderNumber(LocalDate date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeOrder(Order matching) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
