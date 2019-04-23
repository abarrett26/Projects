/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

/**
 *
 * @author alexbarrett
 */
public class ProductPersistenceException extends Exception{
    
    public ProductPersistenceException(String message){
        super (message);
    }
     public ProductPersistenceException(String message, Throwable inner ){
         super(message, inner);
     }
}
