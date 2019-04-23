/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

/**
 *
 * @author alexbarrett
 */
class invalidProductException extends Exception {

    public invalidProductException(String message) {
        super(message);
    }
    public invalidProductException(String message, Throwable inner){
        super(message, inner);
    }
    
}
