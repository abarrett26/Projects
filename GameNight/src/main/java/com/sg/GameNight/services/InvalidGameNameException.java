/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.services;

/**
 *
 * @author alexbarrett
 */
public class InvalidGameNameException extends Exception {

    public InvalidGameNameException(String message) {
        super(message);
    }

    public InvalidGameNameException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
