/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author alexbarrett
 */
public class TaxPersistenceException extends Exception{

   public TaxPersistenceException(String message) {
        super(message);
    }

   public TaxPersistenceException(String message, Throwable inner) {
        super(message, inner);
    }
    
}
