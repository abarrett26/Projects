/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.ProductDao;
import com.mycompany.dao.ProductPersistenceException;
import com.mycompany.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class InMemoryProductDao implements ProductDao{
    
    
    List<Product> allProducts = new ArrayList();
    public InMemoryProductDao() {
      
 
       Product carpet = new Product();
       carpet.setProductType("Carpet");
       carpet.setMaterialCostPerSquareFoot(new BigDecimal("2.25"));
       carpet.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
 
       Product laminate = new Product();
       laminate.setProductType("Laminate");
       laminate.setMaterialCostPerSquareFoot(new BigDecimal("1.75"));
       laminate.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
 
       Product tile = new Product();
       tile.setProductType("Tile");
       tile.setMaterialCostPerSquareFoot(new BigDecimal("3.50"));
       tile.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
 
       Product wood = new Product();
       wood.setProductType("Wood");
       wood.setMaterialCostPerSquareFoot(new BigDecimal("5.15"));
       wood.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
 
       allProducts.add(carpet);
       allProducts.add(laminate);
       allProducts.add(tile);
       allProducts.add(wood);
   }

    @Override
    public List<Product> getAllProducts() throws ProductPersistenceException {
        return allProducts;
    }

    @Override
    public Product getProductbyName(String Product) throws ProductPersistenceException {
       Product toReturn = new Product();
 
       List<Product> getAllProducts = getAllProducts();
       for (int i = 0; i < getAllProducts.size(); i++){
           Product toCheck = getAllProducts().get(i);
           if(toCheck.getProductType().equalsIgnoreCase(Product)){
               toReturn.setLaborCostPerSquareFoot(toCheck.getLaborCostPerSquareFoot());
               toReturn.setProductType(toCheck.getProductType());
               toReturn.setMaterialCostPerSquareFoot(toCheck.getMaterialCostPerSquareFoot());
               break;
           }
           
       }
       return toReturn;
    }
    
}
