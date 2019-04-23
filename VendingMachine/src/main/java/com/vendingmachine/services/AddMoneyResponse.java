/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

import java.math.BigDecimal;

/**
 *
 * @author alexbarrett
 */
public class AddMoneyResponse extends Response{
    private BigDecimal totalMoneyEntered;
 
    public BigDecimal getTotalMoneyEntered() {
        return totalMoneyEntered;
    }

    public void setTotalMoneyEntered(BigDecimal totalMoneyEntered) {
        this.totalMoneyEntered = totalMoneyEntered;
    }
}
