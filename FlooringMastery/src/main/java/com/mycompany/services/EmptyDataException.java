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
class EmptyDataException extends Exception {

    public EmptyDataException(String message) {
        super(message);
    }
    public EmptyDataException(String message, Throwable inner) {
        super(message, inner);
    }
    
}
