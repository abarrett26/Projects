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
public class InvalidCharacterTypeException extends Exception {

    public InvalidCharacterTypeException(String message) {
        super(message);
    }

    public InvalidCharacterTypeException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
