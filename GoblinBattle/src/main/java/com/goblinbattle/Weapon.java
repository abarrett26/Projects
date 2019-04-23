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
public class Weapon {
    private String name;
    private int damage;
    
    public Weapon( String name, int damage ){
        this.name = name;
        this.damage = damage;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }
}
