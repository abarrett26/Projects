/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.services;

/**
 *
 * @author 16128
 */
public class InvalidEquipmentException extends Exception {

    public InvalidEquipmentException(String message) {
        super(message);
    }

    public InvalidEquipmentException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
