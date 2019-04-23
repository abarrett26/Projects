/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Order;
import com.mycompany.dto.Product;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public interface ProductDao {
   public List<Product> getAllProducts() throws ProductPersistenceException;
    
    public Product getProductbyName(String Product) throws ProductPersistenceException;
    
}
