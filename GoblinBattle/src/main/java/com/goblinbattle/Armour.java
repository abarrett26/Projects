/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblinbattle;

/**
 *
 * @author alexbarrett
 */
public class Armour {
    private String name;
    private int protection;
    
    
    public Armour( String name, int protection ){
        this.name = name;
        this.protection = protection;
  
  
}

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the protection
     */
    public int getProtection() {
        return protection;
    }
}