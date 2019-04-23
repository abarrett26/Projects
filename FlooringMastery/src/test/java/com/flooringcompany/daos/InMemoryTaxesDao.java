/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.TaxDao;
import com.mycompany.dao.TaxPersistenceException;
import com.mycompany.dto.Taxes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class InMemoryTaxesDao implements TaxDao{
    List<Taxes> allStateTax = new ArrayList();
    
    public InMemoryTaxesDao(){
        Taxes ohio = new Taxes();
        ohio.setStateAbbreviation("OH");
        ohio.setTaxRate(new BigDecimal("6.25"));
        
        Taxes pens = new Taxes();
        pens.setStateAbbreviation("PA");
        pens.setTaxRate(new BigDecimal("6.75"));
        
        Taxes mich = new Taxes();
        mich.setStateAbbreviation("MI");
        mich.setTaxRate(new BigDecimal("5.75"));
        
        Taxes indi = new Taxes();
        indi.setStateAbbreviation("IN");
        indi.setTaxRate(new BigDecimal("6.00"));
        
        allStateTax.add(ohio);
        allStateTax.add(pens);
        allStateTax.add(mich);
        allStateTax.add(indi);
        
    }

    @Override
    public List<Taxes> getAllStateTax() throws TaxPersistenceException {
        return allStateTax;
    }

    @Override
    public Taxes getTaxbyState(String state) throws TaxPersistenceException {
          Taxes toReturn = null;
 
       List<Taxes> getAllTaxes = getAllStateTax();
 
       for (int i = 0; i < getAllTaxes.size(); i++) {
           Taxes toCheck = getAllTaxes.get(i);
           if (toCheck.getStateAbbreviation().equalsIgnoreCase(state)) {
               toReturn = toCheck;
               break;
           }
       }
       return toReturn;
    }
    
}
