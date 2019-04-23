/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

/**
 *
 * @author alexbarrett
 */
public class OutofStockException extends Exception{
    public OutofStockException ( String message){
        super(message);
    }
    public OutofStockException ( String message, Throwable innerException){
        super(message, innerException);
    }
}
