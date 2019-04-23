///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vendingmachine.daos;
//
//import com.vendingmachine.dtos.Item;
//import com.vendingmachine.services.AddMoneyResponse;
//import com.vendingmachine.services.VendItemResponse;
//import com.vendingmachine.services.VendingService;
//import java.io.File;
//import java.math.BigDecimal;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//import junit.framework.TestCase;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author alexbarrett
// */
//public class FileDaoTest extends TestCase {
//    
//    String seedPath = "/Users/alexbarrett/Desktop/seed.txt";
//    String testPath = "/Users/alexbarrett/Desktop/test.txt";
//    
//    public FileDaoTest(String testName) {
//        super(testName);
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Override
//    public void setUp() throws Exception {
//        File seedFile = new File("/Users/alexbarrett/Desktop/seed.txt");
//        File testFile = new File("/Users/alexbarrett/Desktop/test.txt");
//        
//        testFile.delete();
//        Files.copy(seedFile.toPath(), testFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getAllItems method, of class FileDao.
//     */
//    @Test
//    public void testGetAllItems() throws PersistenceException {
//        FileDao testDao = new FileDao(testPath);
//        List<Item> allItems = testDao.getAllItems();
//        Assert.assertNotNull(allItems);
//        Assert.assertEquals(5, allItems.size());
//        
//    }
//    
//    @Test
//    public void testVendItem() throws PersistenceException {
//        FileDao testDao = new FileDao(testPath); 
//      List<Item> allItems = testDao.getAllItems();
//      Item toVend = allItems.get(0);
//      toVend.setQuantity(99);
//      testDao.vendItem(toVend);
//      allItems = testDao.getAllItems();
//      toVend = allItems.get(0);
//      Assert.assertEquals(99, toVend.getQuantity());
//
//       
//       
//        
//    }
//    
//}
