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
public class InvalidRoleException extends Exception {

    public InvalidRoleException(String message) {
        super(message);
    }

    public InvalidRoleException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
