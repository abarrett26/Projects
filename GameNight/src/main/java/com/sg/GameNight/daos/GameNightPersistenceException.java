/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.daos;

/**
 *
 * @author 16128
 */
public class GameNightPersistenceException extends Exception {

    public GameNightPersistenceException(String message) {
        super(message);
    }

    public GameNightPersistenceException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
