/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.OrderFileDao;
import com.mycompany.dao.OrderPersistenceException;
import com.mycompany.dto.Order;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
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
public class OrderFileDaoTest {
    String seedPath = ("/Users/alexbarrett/Desktop/Data 2Seed");
    String testPath = ("/Users/alexbarrett/Desktop/Data 2Test");
    
    public OrderFileDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
       File testFolder = new File(testPath);
       File seedFolder = new File(seedPath);
 
         File[] testFiles = testFolder.listFiles();
       for (File testFile : testFiles) {
           testFile.delete();
 
       }
 
       File[] seedFiles = seedFolder.listFiles();
       for (File seedFile : seedFiles) {
           Path destinationPath = Paths.get(testPath, seedFile.getName());
           Files.copy(seedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
       }
      
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class OrderFileDao.
     */
    @Test
    public void testAddOrder() throws Exception {
        OrderFileDao testDao = new OrderFileDao(testPath);
        Order newOrder = new Order();
        LocalDate dateToAdd = LocalDate.of(2020,01,01);
        newOrder.setDate(dateToAdd);
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Alex");
        newOrder.setStateAbbrevation("OH");
        newOrder.setTaxRate(new BigDecimal("6.25"));
        newOrder.setProductName("CARPET");
        newOrder.setArea(new BigDecimal("100"));
        newOrder.setMatUnitCost(new BigDecimal("2.25"));
        newOrder.setLaborUnitCost(new BigDecimal("2.10"));
        testDao.addOrder(newOrder);
        
        List<Order> allOrders = testDao.getOrdersByDate(dateToAdd);
        Order addedOrder = allOrders.get(1);
        Assert.assertEquals(LocalDate.of(2020, 01, 01), addedOrder.getDate());
        Assert.assertEquals("Alex", addedOrder.getCustomerName());
        Assert.assertEquals("OH", addedOrder.getStateAbbrevation());
        Assert.assertEquals(new BigDecimal("6.25"), addedOrder.getTaxRate());
        Assert.assertEquals("CARPET", addedOrder.getProductName());
        Assert.assertEquals(new BigDecimal("100"), addedOrder.getArea());
        Assert.assertEquals(new BigDecimal("2.25"), addedOrder.getMatUnitCost());
        Assert.assertEquals(new BigDecimal("2.10"), addedOrder.getLaborUnitCost());
        
        
       
    }

    /**
     * Test of getOrdersByDate method, of class OrderFileDao.
     */
    @Test
    public void testGetOrdersByDate() throws OrderPersistenceException {
       OrderFileDao testDao = new OrderFileDao(testPath);
       LocalDate date = LocalDate.of(2020, 01, 01);
       List<Order> ordersForDate = testDao.getOrdersByDate(date);
 
       assertEquals(1, ordersForDate.size());
       //Order edited = testDao.getOrderByOrderNumber(date, 1);
       Order edited = ordersForDate.get(0);
       Assert.assertEquals("Alex", edited.getCustomerName());
       Assert.assertEquals("OH", edited.getStateAbbrevation());
       Assert.assertEquals(new BigDecimal("6.25"), edited.getTaxRate());
       Assert.assertEquals("CARPET", edited.getProductName());
       Assert.assertEquals(new BigDecimal("100"), edited.getArea());
       Assert.assertEquals(new BigDecimal("2.25"), edited.getMatUnitCost());
       Assert.assertEquals(new BigDecimal("2.10"), edited.getLaborUnitCost());
       
       //TOOO: assert that the right data exists on the one order in this list
 
   }
    @Test
    public void testGetOrdersByDateNOPE() {
       OrderFileDao testDao = new OrderFileDao(testPath);
       LocalDate date = LocalDate.of(2022, 01, 01);
       
       testDao.getOrdersByDate(date);
 
       assertEquals(0, testDao.getOrdersByDate(date).size());
 
   }
    

    /**
     * Test of editOrder method, of class OrderFileDao.
     */
    @Test
    public void testEditOrder() throws Exception {
       OrderFileDao testDao = new OrderFileDao(testPath);
       LocalDate dateToEdit = LocalDate.of(2020, 01, 01);
       int orderNum = 1;
       Order edit = testDao.getOrderByOrderNumber(dateToEdit, orderNum);
       //edit.setDate(dateToEdit);
       
       //TODO: need to edit to NEW values
       
       edit.setCustomerName("BOB");
       edit.setStateAbbrevation("IN");
       edit.setTaxRate(new BigDecimal("6.00"));
       edit.setProductName("LAMINATE");
       edit.setArea(new BigDecimal("250"));
       edit.setMatUnitCost(new BigDecimal("1.75"));
       edit.setLaborUnitCost(new BigDecimal("2.10"));     
       testDao.editOrder(edit);
       Order edited = testDao.getOrderByOrderNumber(dateToEdit, orderNum);
       Assert.assertEquals(LocalDate.of(2020, 01, 01), edited.getDate());
       Assert.assertEquals("BOB", edited.getCustomerName());
       Assert.assertEquals("IN", edited.getStateAbbrevation());
       Assert.assertEquals(new BigDecimal("6.00"), edited.getTaxRate());
       Assert.assertEquals("LAMINATE", edited.getProductName());
       Assert.assertEquals(new BigDecimal("250"), edited.getArea());
       Assert.assertEquals(new BigDecimal("1.75"), edited.getMatUnitCost());
       Assert.assertEquals(new BigDecimal("2.10"), edited.getLaborUnitCost());
 

   }
    

    /**
     * Test of removeOrder method, of class OrderFileDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
       OrderFileDao testDao = new OrderFileDao(testPath);
       LocalDate date = LocalDate.of(2020, 01, 01);
       int orderToRemove = 1;
       Order toRemove = testDao.getOrderByOrderNumber(date, orderToRemove);
       testDao.removeOrder(toRemove);
       List<Order> orderSize = testDao.getOrdersByDate(LocalDate.of(2020, 01, 01));
       Assert.assertEquals(0, orderSize.size());
       Assert.assertNull(testDao.getOrderByOrderNumber(date,orderToRemove));
    }
    @Test
    public void testRemoveOrderDATENOPE() throws Exception {
       OrderFileDao testDao = new OrderFileDao(testPath);
       LocalDate date = LocalDate.of(2200, 01, 01);
       int orderToRemove = 1;
       //Order toRemove = testDao.getOrderByOrderNumber(date, orderToRemove);
       Order toRemove = new Order();
       toRemove.setDate(date);
       toRemove.setOrderNumber(orderToRemove);
      try{ 
          testDao.removeOrder(toRemove);
          Assert.fail();
      } catch(OrderPersistenceException ex){
          
      }
       List<Order> orderSize = testDao.getOrdersByDate(LocalDate.of(2200, 01, 01));
       Assert.assertEquals(0, orderSize.size());
       
    }


    /**
     * Test of getOrderByOrderNumber method, of class OrderFileDao.
     */
    @Test
    public void testGetOrderByOrderNumber() throws Exception {
       OrderFileDao testDao = new OrderFileDao(testPath);
       LocalDate dateOfOrder = LocalDate.of(2020, 01, 01);
       int orderNumber = 1;
       Order retrieved = testDao.getOrderByOrderNumber(dateOfOrder, orderNumber);
       Assert.assertEquals(1, retrieved.getOrderNumber());
       Assert.assertEquals("OH", retrieved.getStateAbbrevation());
       Assert.assertEquals(new BigDecimal("100"),retrieved.getArea());
      
     
    }
    
    
}
