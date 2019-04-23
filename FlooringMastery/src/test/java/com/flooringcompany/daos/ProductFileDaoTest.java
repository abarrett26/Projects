/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.ProductFileDao;
import com.mycompany.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexbarrett
 */
public class ProductFileDaoTest {
    String seedPath = "/Users/alexbarrett/Desktop/ProductsSeed.txt";
    String testPath = "/Users/alexbarrett/Desktop/ProductsTest.txt";
    
    public ProductFileDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllProducts method, of class ProductFileDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
       ProductFileDao testDao = new ProductFileDao(testPath);
       List<Product> products = testDao.getAllProducts();
       Assert.assertNotNull(products);
       Assert.assertEquals(4, products.size());
       Product correctProductDetails = products.get(0);
       Assert.assertEquals("Carpet", correctProductDetails.getProductType());
       Assert.assertEquals(new BigDecimal("2.25"), correctProductDetails.getMaterialCostPerSquareFoot());
       Assert.assertEquals(new BigDecimal("2.10"), correctProductDetails.getLaborCostPerSquareFoot());    
   
    }

    /**
     * Test of getProductbyName method, of class ProductFileDao.
     */
    @Test
    public void testGetProductbyName() throws Exception {
       ProductFileDao testDao = new ProductFileDao(testPath);
       String input = "Carpet";
       Product testProduct = testDao.getProductbyName(input);
       Assert.assertEquals("Carpet", testProduct.getProductType());
       Assert.assertEquals(new BigDecimal("2.25"), testProduct.getMaterialCostPerSquareFoot());
       Assert.assertEquals(new BigDecimal("2.10"), testProduct.getLaborCostPerSquareFoot());
        
    }
    
}
