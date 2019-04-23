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
public class InvalidCharacterNameException extends Exception {

    public InvalidCharacterNameException(String message) {
        super(message);
    }

    public InvalidCharacterNameException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
