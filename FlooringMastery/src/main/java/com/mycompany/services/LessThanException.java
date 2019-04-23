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
class LessThanException extends Exception {

    public LessThanException(String message) {
        super(message);
    }
    public LessThanException(String message, Throwable inner) {
        super(message, inner);
    }
}
