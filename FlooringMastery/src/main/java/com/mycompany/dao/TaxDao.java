/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Taxes;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public interface TaxDao {
    public List<Taxes> getAllStateTax() throws TaxPersistenceException;
    public Taxes getTaxbyState(String state)throws TaxPersistenceException;
}
