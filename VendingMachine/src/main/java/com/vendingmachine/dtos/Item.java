/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.dtos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author dsmelser
 */
public class Item {
    private int id;
    private String name;
    private int quantity;
    private int priceInPennies;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public int getPriceInPennies() {
        return priceInPennies;
    }
    
    public BigDecimal getPrice() {
        return new BigDecimal(priceInPennies)
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

  
    public void setPriceInPennies(int priceInPennies) {
        this.priceInPennies = priceInPennies;
    }
    
}
