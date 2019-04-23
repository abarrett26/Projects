/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Order;
import com.mycompany.dto.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexbarrett
 */
public class ProductFileDao implements ProductDao {

    String path;

    public ProductFileDao(String path) {
        this.path = path;
    }

    @Override
    public List<Product> getAllProducts() throws ProductPersistenceException {
        List<Product> toReturn = new ArrayList();
        try {

            File productFile = new File(path);

            Scanner reader = new Scanner(new BufferedReader(new FileReader(productFile)));
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] cells = line.split(",");
                Product toAdd = new Product();
                toAdd.setProductType(cells[0]);
                toAdd.setMaterialCostPerSquareFoot(new BigDecimal(cells[1]));
                toAdd.setLaborCostPerSquareFoot(new BigDecimal(cells[2]));
                toReturn.add(toAdd);

            }

        } catch (FileNotFoundException ex) {
            throw new ProductPersistenceException("could not find product file", ex);
        }
        return toReturn;
    }

    @Override
    public Product getProductbyName(String productName) throws ProductPersistenceException {
        Product toReturn = null;
        
        List<Product> allProducts = getAllProducts();

        for (Product toCheck : allProducts) {
            if (toCheck.getProductType().equalsIgnoreCase(productName)) {
                toReturn = toCheck;
                break;
            }
        }

        return toReturn;
    }


}
