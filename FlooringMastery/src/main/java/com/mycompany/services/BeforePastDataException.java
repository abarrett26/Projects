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
class BeforePastDataException extends Exception {

    public BeforePastDataException(String message) {
        super(message);
    }
    public BeforePastDataException(String message, Throwable inner){
        super(message, inner);
    }
}
