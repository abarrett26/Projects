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
public class InvalidCharacterLevelException extends Exception {

    public InvalidCharacterLevelException(String message) {
        super(message);
    }

    public InvalidCharacterLevelException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
