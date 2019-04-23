/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.TaxFileDao;
import com.mycompany.dao.TaxPersistenceException;
import com.mycompany.dto.Taxes;
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
public class TaxFileDaoTest {
    String seedPath = "/Users/alexbarrett/Desktop/TaxesSeed.txt";
    String testPath = "/Users/alexbarrett/Desktop/TaxesTest.txt";
    
    public TaxFileDaoTest() {
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
     * Test of getAllStateTax method, of class TaxFileDao.
     */
    @Test
    public void testGetAllStateTax() throws TaxPersistenceException {
       TaxFileDao testDao = new TaxFileDao(testPath);
       List<Taxes> taxListSize = testDao.getAllStateTax();
       Assert.assertNotNull(taxListSize);
       Assert.assertEquals(4, taxListSize.size());
       Taxes stateTax = taxListSize.get(0);
       Assert.assertEquals("OH", stateTax.getStateAbbreviation());
       Assert.assertEquals(new BigDecimal("6.25"), stateTax.getTaxRate());
    }

    /**
     * Test of getTaxbyState method, of class TaxFileDao.
     */
    @Test
    public void testGetTaxbyState() throws TaxPersistenceException {
       TaxFileDao testDao = new TaxFileDao(testPath);
       List<Taxes> getTaxSize = testDao.getAllStateTax();
       Assert.assertNotNull(getTaxSize);
       Assert.assertEquals(4, getTaxSize.size());
       Taxes correctTax = getTaxSize.get(0);
       Assert.assertEquals("OH", correctTax.getStateAbbreviation());
       Assert.assertEquals(new BigDecimal("6.25"), correctTax.getTaxRate());
       
       
       
    }
    
}
