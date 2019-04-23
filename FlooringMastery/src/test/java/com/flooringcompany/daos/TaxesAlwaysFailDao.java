/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringcompany.daos;

import com.mycompany.dao.TaxDao;
import com.mycompany.dao.TaxPersistenceException;
import com.mycompany.dto.Taxes;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class TaxesAlwaysFailDao implements TaxDao{

    @Override
    public List<Taxes> getAllStateTax() throws TaxPersistenceException {
        throw new TaxPersistenceException("Failed to retrieve data");
    }

    @Override
    public Taxes getTaxbyState(String state) throws TaxPersistenceException {
                throw new TaxPersistenceException("Failed to retrieve data");
    }
    
}
