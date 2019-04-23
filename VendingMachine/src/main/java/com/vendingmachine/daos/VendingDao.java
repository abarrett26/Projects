/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.daos;

import com.vendingmachine.dtos.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author dsmelser
 */
public interface VendingDao {

    public List<Item> getAllItems() throws PersistenceException;

    public void vendItem(Item toVend) throws PersistenceException;

    public Item getItemById(int itemId);
    public boolean removeItem(int itemId);

    public int generateNewId(List<Item> allItems);

    public void addItem(Item toAdd);

    public void editItem(Item toEdit);
        
   
}
