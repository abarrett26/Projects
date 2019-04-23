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
public class InvalidGroupDescriptionException extends Exception {

    public InvalidGroupDescriptionException(String message) {
        super(message);
    }

    public InvalidGroupDescriptionException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
