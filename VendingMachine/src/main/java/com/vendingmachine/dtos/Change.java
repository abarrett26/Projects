/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.dtos;

/**
 *
 * @author alexbarrett
 */
public class Change {
    private int dollars;
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    public Change( int totalPennies ){
        
        dollars = totalPennies / 100;
        totalPennies = totalPennies - dollars * 100;

        quarters = totalPennies / 25;
        totalPennies = totalPennies - quarters * 25;
        
        dimes = totalPennies / 10;
        totalPennies = totalPennies - dimes * 10;
        
        nickels = totalPennies / 5;
        totalPennies = totalPennies - nickels * 5;
        
        pennies = totalPennies;
    }

  

    /**
     * @return the dollars
     */
    public int getDollars() {
        return dollars;
    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @return the nickels
     */
    public int getNickels() {
        return nickels;
    }

    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }
    
    
}
