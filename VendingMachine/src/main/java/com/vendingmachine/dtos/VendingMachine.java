/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.dtos;

import com.vendingmachine.services.AddMoneyResponse;
import com.vendingmachine.services.VendingService;
import com.vendingmachine.ui.ConsoleIO;
import com.vendingmachine.ui.EnterMoneyWorkflow;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class VendingMachine {
    
    private List<Item> toSell;
    
    private int totalPenniesEntered = 0;
    
    public void enterDollar() { totalPenniesEntered += 100; }
    public void enterQuarter() { totalPenniesEntered += 25; }
    public void enterDime() { totalPenniesEntered += 10; }
    public void enterNickel() { totalPenniesEntered += 5; }
    public void enterPenny() { totalPenniesEntered += 1; }
    
    public void enterMoney( BigDecimal toEnter ){
        
        int justEntered = toEnter.multiply( new BigDecimal(100) ).intValue();
        totalPenniesEntered += justEntered;
    }
    
    public Change returnChange() {
        
        Change toReturn = new Change(totalPenniesEntered);
        
        totalPenniesEntered = 0;
        
        return toReturn;
        
    }
            

    /**
     * @return the toSell
     */
    public List<Item> getToSell() {
        return toSell;
    }

    /**
     * @return the penniesEntered
     */
    public int getTotalPenniesEntered() {
        return totalPenniesEntered;
    }
    
    
    public BigDecimal getTotalMoney() {
        BigDecimal toReturn = new BigDecimal(totalPenniesEntered);
        toReturn = toReturn.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        return toReturn;
    }

    /**
     * @param toSell the toSell to set
     */
    public void setToSell(List<Item> toSell) {
        this.toSell = toSell;
    }

    
   
    
    
    
}
