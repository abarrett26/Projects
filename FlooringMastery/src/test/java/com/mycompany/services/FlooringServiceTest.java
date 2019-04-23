/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.flooringcompany.daos.OrderAlwaysFailDao;
import com.flooringcompany.daos.InMemoryOrderDao;
import com.flooringcompany.daos.InMemoryProductDao;
import com.flooringcompany.daos.InMemoryTaxesDao;
import com.mycompany.dao.OrderDao;
import com.mycompany.dao.OrderPersistenceException;
import com.mycompany.dao.ProductDao;
import com.mycompany.dao.TaxDao;
import com.mycompany.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexbarrett
 */
public class FlooringServiceTest {

    

    public void FlooringService() {

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
     * Test of addOrder method, of class FlooringService.
     */
    @Test
    public void testAddOrderSuccess() throws OrderPersistenceException {
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       Order orderAdd = new Order();
       LocalDate date = LocalDate.of(2021, 01, 01);
       orderAdd.setDate(date);
       orderAdd.setCustomerName("Alex");
       orderAdd.setStateAbbrevation("OH");
       orderAdd.setProductName("CARPET");
       orderAdd.setArea(new BigDecimal("100"));
       AddOrderResponse responseAdd = service.addOrder(orderAdd);
       
       Order addedOrder = testDao.getOrderByOrderNumber(date, 2);
       
       Assert.assertEquals(LocalDate.of(2021, 01, 01), addedOrder.getDate());
       Assert.assertEquals(2, addedOrder.getOrderNumber());
       Assert.assertEquals("Alex", addedOrder.getCustomerName());
       Assert.assertEquals("OH", addedOrder.getStateAbbrevation());
       Assert.assertEquals("CARPET", addedOrder.getProductName());
       Assert.assertEquals(new BigDecimal("100"), addedOrder.getArea());
       Assert.assertEquals(new BigDecimal("6.25"), addedOrder.getTaxRate());
       Assert.assertEquals(new BigDecimal("2.25"), addedOrder.getMatUnitCost());
       Assert.assertEquals(new BigDecimal("2.10"), addedOrder.getLaborUnitCost());
       //TODO: write asserts for dependant data (tax rate and labor/material unit costs)
      
       
               
    }
    @Test
    public void testAddOrderFail() {
       //InMemoryOrderDao testDao = new InMemoryOrderDao();
       OrderAlwaysFailDao failDao = new OrderAlwaysFailDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(failDao,testPDao,testTDao);
       Order orderAdd = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderAdd.setDate(date);
       orderAdd.setCustomerName("Alex");
       orderAdd.setStateAbbrevation("OH");
       orderAdd.setProductName("CARPET");
       orderAdd.setArea(new BigDecimal("100"));
       AddOrderResponse responseAdd = service.addOrder(orderAdd);
       Assert.assertFalse(responseAdd.isSuccess());
       
    }
    
    @Test
    public void testAddOrderWrongState(){
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       Order orderAdd = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderAdd.setDate(date);
       orderAdd.setCustomerName("Alex");
       orderAdd.setStateAbbrevation("MN");
       orderAdd.setProductName("CARPET");
       orderAdd.setArea(new BigDecimal("100"));
       AddOrderResponse responseAdd = service.addOrder(orderAdd);
       Assert.assertFalse(responseAdd.isSuccess());
        
    }
    @Test
    public void testAddOrderWrongArea(){
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       Order orderAdd = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderAdd.setDate(date);
       orderAdd.setCustomerName("Alex");
       orderAdd.setStateAbbrevation("OH");
       orderAdd.setProductName("CARPET");
       orderAdd.setArea(new BigDecimal("0"));
       AddOrderResponse responseAdd = service.addOrder(orderAdd);
       Assert.assertFalse(responseAdd.isSuccess());
        
    }
    @Test
    public void testAddOrderWrongProduct(){
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       Order orderAdd = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderAdd.setDate(date);
       orderAdd.setCustomerName("Alex");
       orderAdd.setStateAbbrevation("OH");
       orderAdd.setProductName("METAL");
       orderAdd.setArea(new BigDecimal("100"));
       AddOrderResponse responseAdd = service.addOrder(orderAdd);
       Assert.assertFalse(responseAdd.isSuccess());
        
    }
    @Test
    public void testAddOrderWrongDate(){
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       Order orderAdd = new Order();
       LocalDate date = LocalDate.of(2000, 01, 01);
       orderAdd.setDate(date);
       orderAdd.setCustomerName("Alex");
       orderAdd.setStateAbbrevation("OH");
       orderAdd.setProductName("CARPET");
       orderAdd.setArea(new BigDecimal("100"));
       AddOrderResponse responseAdd = service.addOrder(orderAdd);
       Assert.assertFalse(responseAdd.isSuccess());
        
    }
    @Test
    public void testAddOrderWrongName(){
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       Order orderAdd = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderAdd.setDate(date);
       orderAdd.setCustomerName("");
       orderAdd.setStateAbbrevation("OH");
       orderAdd.setProductName("CARPET");
       orderAdd.setArea(new BigDecimal("100"));
       AddOrderResponse responseAdd = service.addOrder(orderAdd);
       Assert.assertFalse(responseAdd.isSuccess());
        
    }

    /**
     * Test of listOrders method, of class FlooringService.
     */
    @Test
    public void testListOrdersSuccess() throws Exception {
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       LocalDate date = LocalDate.of(2020, 1, 1);
       DisplayOrderResponse response = service.listOrders(date);
       Assert.assertTrue(response.isSuccess());
       Assert.assertNotNull(response.getAllOrders());
       Assert.assertEquals(1, response.getAllOrders().size());
   
    }
    @Test
    public void testListOrdersFail() throws Exception{
       OrderAlwaysFailDao failDao = new OrderAlwaysFailDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(failDao,testPDao,testTDao);
       LocalDate date = LocalDate.of(2020, 1, 1);
       DisplayOrderResponse response = service.listOrders(date);
       Assert.assertFalse(response.isSuccess());
        
    }

    /**
     * Test of removeOrder method, of class FlooringService.
     */
    @Test
    public void testRemoveOrderSuccess() throws Exception{
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       LocalDate date = LocalDate.of(2020, 01, 01);
       int orderNumber = 1;
       DisplayOrderResponse response = service.listOrders(date);
       RemoveOrderResponse removed = service.removeOrder(date, orderNumber);
       Assert.assertTrue(removed.isSuccess());
       Assert.assertEquals(0, response.getAllOrders().size());
  
    }

    @Test
    public void testRemoveOrderNoDate() throws Exception{
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       LocalDate date = LocalDate.of(2099, 01, 01);
       int orderNumber = 1;
       DisplayOrderResponse response = service.listOrders(date);
       RemoveOrderResponse removed = service.removeOrder(date, orderNumber);
       Assert.assertFalse(removed.isSuccess());
       
  
    }
    /**
     * Test of editOrder method, of class FlooringService.
     * 
     * 
     *  
 
     */
    @Test
    public void testEditOrderSuccess()throws Exception {
       InMemoryOrderDao testDao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(testDao,testPDao,testTDao);
       LocalDate dateToEdit = LocalDate.of(2020, 01, 01);
       int orderNum = 1;
       Order orderEdit = testDao.getOrderByOrderNumber(dateToEdit, orderNum);
       
       orderEdit.setCustomerName("BOB");
       orderEdit.setStateAbbrevation("IN");
       
       orderEdit.setProductName("WOOD");
       orderEdit.setArea(new BigDecimal("500"));
      
       EditOrderResponse responseEdit = service.editOrder(orderEdit);

       Assert.assertTrue(responseEdit.isSuccess());
       
       orderEdit = testDao.getOrderByOrderNumber(dateToEdit, orderNum);
       
       Assert.assertEquals("BOB", orderEdit.getCustomerName());
       Assert.assertEquals("IN", orderEdit.getStateAbbrevation());
       Assert.assertEquals(new BigDecimal("6.00"), orderEdit.getTaxRate());
       Assert.assertEquals("WOOD", orderEdit.getProductName());
       Assert.assertEquals(new BigDecimal("500"), orderEdit.getArea());
       Assert.assertEquals(new BigDecimal("4.75"), orderEdit.getLaborUnitCost());
       Assert.assertEquals(new BigDecimal("5.15"), orderEdit.getMatUnitCost());
       
       //TODO: assert that the dependant data has changed (tax rate & labor/material unit costs)
     
               
    }
    
    @Test
    public void testEditOrderFail() throws Exception{
       OrderAlwaysFailDao failDao = new OrderAlwaysFailDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(failDao,testPDao,testTDao);
       Order orderEdit = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderEdit.setDate(date);
       orderEdit.setCustomerName("Alex");
       orderEdit.setStateAbbrevation("OH");
       orderEdit.setProductName("CARPET");
       orderEdit.setArea(new BigDecimal("100"));
       EditOrderResponse responseEdit = service.editOrder(orderEdit);
       Assert.assertFalse(responseEdit.isSuccess());
        
    }
    @Test
    public void testEditOrderWrongState() throws Exception{
       InMemoryOrderDao dao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(dao,testPDao,testTDao);
       Order orderEdit = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderEdit.setDate(date);
       orderEdit.setCustomerName("Alex");
       orderEdit.setStateAbbrevation("MN");
       orderEdit.setProductName("CARPET");
       orderEdit.setArea(new BigDecimal("100"));
       EditOrderResponse responseEdit = service.editOrder(orderEdit);
       Assert.assertFalse(responseEdit.isSuccess());
        
    }
    @Test
    public void testEditOrderWrongProduct() throws Exception{
       InMemoryOrderDao dao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(dao,testPDao,testTDao);
       Order orderEdit = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderEdit.setDate(date);
       orderEdit.setCustomerName("Alex");
       orderEdit.setStateAbbrevation("OH");
       orderEdit.setProductName("METAL");
       orderEdit.setArea(new BigDecimal("100"));
       EditOrderResponse responseEdit = service.editOrder(orderEdit);
       Assert.assertFalse(responseEdit.isSuccess());
        
    }
    @Test
    public void testEditOrderArea() throws Exception{
       InMemoryOrderDao dao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(dao,testPDao,testTDao);
       Order orderEdit = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderEdit.setDate(date);
       orderEdit.setCustomerName("Alex");
       orderEdit.setStateAbbrevation("OH");
       orderEdit.setProductName("CARPET");
       orderEdit.setArea(new BigDecimal("0"));
       EditOrderResponse responseEdit = service.editOrder(orderEdit);
       Assert.assertFalse(responseEdit.isSuccess());
        
    }

    
    @Test
    public void testEditOrderWrongName() throws Exception{
       InMemoryOrderDao dao = new InMemoryOrderDao();
       InMemoryProductDao testPDao = new InMemoryProductDao();
       InMemoryTaxesDao testTDao = new InMemoryTaxesDao();
       FlooringService service = new FlooringService(dao,testPDao,testTDao);
       Order orderEdit = new Order();
       LocalDate date = LocalDate.of(2020, 01, 01);
       orderEdit.setDate(date);
       orderEdit.setCustomerName("");
       orderEdit.setStateAbbrevation("OH");
       orderEdit.setProductName("CARPET");
       orderEdit.setArea(new BigDecimal("100"));
       EditOrderResponse responseEdit = service.editOrder(orderEdit);
       Assert.assertFalse(responseEdit.isSuccess());
        
    }
    
    
}
