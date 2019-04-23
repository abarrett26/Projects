///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vendingmachine.services;
//
//import com.vendingmachine.daos.AlwaysFailDao;
//import com.vendingmachine.daos.InMemoryDao;
//import com.vendingmachine.daos.TestAuditDao;
//import java.math.BigDecimal;
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
//public class VendingServiceTest {
////    public ClassRosterServiceLayerTest() {
////        // wire the Service Layer with stub implementations of the Dao and
////        // Audit Dao
////        ClassRosterDao dao = new ClassRosterDaoStubImpl();
////        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
//// 
////        service = new ClassRosterServiceLayerImpl(dao, auditDao);
//// 
////    }
//
//    TestAuditDao fakeAudit = new TestAuditDao();
//
//    public VendingServiceTest() {
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
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void getAllItemsFail() {
//        AlwaysFailDao failDao = new AlwaysFailDao();
//
//        VendingService service = new VendingService(failDao, fakeAudit);
//        PrintItemsResponse response = service.getAllItems();
//        Assert.assertFalse(response.isSuccess());
////        Assert.assertEquals("Error retrieving items.", response.getMessage());
//    }
//
//    @Test
//    public void testGetAllItemsSuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        PrintItemsResponse response = service.getAllItems();
//
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertNotNull(response.getAllItems());
//        Assert.assertEquals(2, response.getAllItems().size());
//
//    }
//
//    @Test
//    public void testEnterMoneySuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        //NOTE: adding zero money is probably not what we want to do
//        AddMoneyResponse response = service.enterMoney(new BigDecimal("0.25"));
//
//        Assert.assertNotNull(response);
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertEquals(new BigDecimal("0.25"), response.getTotalMoneyEntered());
//
//        response = service.enterMoney(new BigDecimal("0.25"));
//        Assert.assertEquals(new BigDecimal("0.50"), response.getTotalMoneyEntered());
//
//    }
////    @Test
////    public void testEnterMoneyFail(){
////        InMemoryDao failDao = new InMemoryDao();
////        VendingService service = new VendingService(failDao);
////        
////        AddMoneyResponse response = service.enterMoney(new BigDecimal("0.25")); //help?
////        Assert.assertFalse(response.isSuccess());
////        Assert.assertEquals(new BigDecimal("0.25"), response.getTotalMoneyEntered());
////        
////        response = service.enterMoney(new BigDecimal("0.50"));
////        Assert.assertEquals(new BigDecimal("1.00"), response.getTotalMoneyEntered());
////    }
//
//    //public PrintItemsResponse getAllItems()
//    @Test
//    public void EnterPennyTestsuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        AddMoneyResponse response = service.enterPenny();
//
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertEquals(new BigDecimal("0.01"), response.getTotalMoneyEntered());
//
//        response = service.enterPenny();
//        Assert.assertEquals(new BigDecimal("0.02"), response.getTotalMoneyEntered());
//    }
//
//    @Test
//    public void EnterNickelSuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        AddMoneyResponse response = service.enterNickel();
//
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertEquals(new BigDecimal("0.05"), response.getTotalMoneyEntered());
//
//        response = service.enterNickel();
//        Assert.assertEquals(new BigDecimal("0.10"), response.getTotalMoneyEntered());
//    }
//
//    @Test
//    public void EnterDimeSuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        AddMoneyResponse response = service.enterDime();
//
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertEquals(new BigDecimal("0.10"), response.getTotalMoneyEntered());
//
//        response = service.enterDime();
//        Assert.assertEquals(new BigDecimal("0.20"), response.getTotalMoneyEntered());
//    }
//
//    @Test
//    public void EnterQuarterSuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        AddMoneyResponse response = service.enterQuarter();
//
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertEquals(new BigDecimal("0.25"), response.getTotalMoneyEntered());
//
//        response = service.enterQuarter();
//        Assert.assertEquals(new BigDecimal("0.50"), response.getTotalMoneyEntered());
//
//    }
//
//    @Test
//    public void EnterDollarSuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        AddMoneyResponse response = service.enterDollar();
//
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertEquals(new BigDecimal("1.00"), response.getTotalMoneyEntered());
//
//        response = service.enterDollar();
//        Assert.assertEquals(new BigDecimal("2.00"), response.getTotalMoneyEntered());
//
//    }
//
//    @Test
//    public void VendItemSuccess() {
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        AddMoneyResponse addMoney = service.enterMoney(new BigDecimal("0.75"));
//
//        VendItemResponse response = service.vendItem(1);
//
//        Assert.assertTrue(response.isSuccess());
//        Assert.assertEquals(99, response.getVendedItem().getQuantity());
//        Assert.assertEquals(75, response.getVendedItem().getPriceInPennies());
//        Assert.assertEquals(0, response.getReturnedChange().getDollars());
//        Assert.assertEquals(0, response.getReturnedChange().getQuarters());
//        Assert.assertEquals(0, response.getReturnedChange().getDimes());
//        Assert.assertEquals(0, response.getReturnedChange().getNickels());
//        Assert.assertEquals(0, response.getReturnedChange().getPennies());
//
//    }
//
//    @Test
//    public void returnedChangeSuccess() {
//
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//
//        AddMoneyResponse addMoney = service.enterMoney(new BigDecimal("1.41"));
//
//        ChangeReturnResponse response = service.returnedChange();
//
//        Assert.assertEquals(1, response.getReturnedChange().getDollars());
//        Assert.assertEquals(1, response.getReturnedChange().getQuarters());
//        Assert.assertEquals(1, response.getReturnedChange().getDimes());
//        Assert.assertEquals(1, response.getReturnedChange().getNickels());
//        Assert.assertEquals(1, response.getReturnedChange().getPennies());
//
//    }
//
//    @Test
//    public void badDaoTest() {
//        AlwaysFailDao failDao = new AlwaysFailDao();
//        VendingService service = new VendingService(failDao, fakeAudit);
//        
//        VendItemResponse response = service.vendItem(1);
//        Assert.assertFalse(response.isSuccess());
//    }
//
//    @Test
//    public void insufficientFundTest(){
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//        
//        VendItemResponse response = service.vendItem(1);
//        Assert.assertFalse(response.isSuccess());
////        AddMoneyResponse addMoney = service.enterMoney(new BigDecimal("0"));
////        VendItemResponse response = service.vendItem(1);
////        Assert.assertFalse(addMoney.isSuccess());
////        Assert.assertFalse(response.isSuccess());
//    }
//    @Test
//    public void outOfStockTest(){
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//        service.enterDollar();
//        VendItemResponse response = service.vendItem(2);
//        Assert.assertFalse(response.isSuccess());
//    }
//    @Test
//    public void invalidIdTest(){
//        InMemoryDao successDao = new InMemoryDao();
//        VendingService service = new VendingService(successDao, fakeAudit);
//        
//        service.enterDollar();
//        service.enterDollar();
//        service.enterDollar();
//        service.enterDollar();
//        service.enterDollar();
//        service.enterDollar();
//        service.enterDollar();
//        service.enterDollar();
//        service.enterDollar();
//        //hypthoetically does not matter how much money is entered. 
//        
//        VendItemResponse response = service.vendItem(0);
//        Assert.assertFalse(response.isSuccess());
//    }
//}
