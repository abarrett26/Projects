/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author alexbarrett
 */
public class Order {
    //Order Date
    private LocalDate date;
    
    //Order Number
    private int orderNumber;
    //Customer Name
    private String customerName;
    //State abbriviation
    private String stateAbbrevation;
    //tax rate
    private BigDecimal taxRate;
            
    //product type(Name)
    private String productName;
    //Area
    private BigDecimal area;
    //Material Unit Cost
    private BigDecimal matUnitCost;
    //Labor unit cost
    private BigDecimal laborUnitCost;
    
    //Total Material Cost
    
    //Total labor cost
    //total tax
    //order total

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the stateAbbrevation
     */
    public String getStateAbbrevation() {
        return stateAbbrevation;
    }

    /**
     * @param stateAbbrevation the stateAbbrevation to set
     */
    public void setStateAbbrevation(String stateAbbrevation) {
        this.stateAbbrevation = stateAbbrevation;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the matUnitCost
     */
    public BigDecimal getMatUnitCost() {
        return matUnitCost;
    }

    /**
     * @param matUnitCost the matUnitCost to set
     */
    public void setMatUnitCost(BigDecimal matUnitCost) {
        this.matUnitCost = matUnitCost;
    }

    /**
     * @return the laborUnitCost
     */
    public BigDecimal getLaborUnitCost() {
        return laborUnitCost;
    }

    /**
     * @param laborUnitCost the laborUnitCost to set
     */
    public void setLaborUnitCost(BigDecimal laborUnitCost) {
        this.laborUnitCost = laborUnitCost;
    }
    public BigDecimal getTotalMatCost(){
        BigDecimal toReturn = area.multiply(matUnitCost);
        return toReturn;
    }
    public BigDecimal getTotalLaborCost(){
        BigDecimal toReturn = area.multiply(laborUnitCost);
        return toReturn;
    }
    public BigDecimal getTotalTax(){
        BigDecimal totalMatCost = getTotalMatCost();
        BigDecimal totalLaborCost = getTotalLaborCost();
        BigDecimal preTax = totalMatCost.add(totalLaborCost);
        
        BigDecimal toReturn = preTax.multiply(taxRate).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        return toReturn;
    }
    public BigDecimal getOrderTotal(){
        BigDecimal totalMatCost = getTotalMatCost();
        BigDecimal totalLaborCost = getTotalLaborCost();
        BigDecimal totalTaxes = getTotalTax();
        
        BigDecimal toReturn = totalMatCost.add(totalLaborCost).add(totalTaxes);
        return toReturn;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
