/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.ProductDao;
import com.mycompany.dao.ProductPersistenceException;
import com.mycompany.dto.Product;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class ProductAlwaysFailDao implements ProductDao {

    @Override
    public List<Product> getAllProducts() throws ProductPersistenceException {
        throw new ProductPersistenceException("Failed to retrieve data");
    }

    @Override
    public Product getProductbyName(String Product) throws ProductPersistenceException {
        throw new ProductPersistenceException("Failed to retrieve data");
    }
    
}
