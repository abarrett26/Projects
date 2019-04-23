/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblinbattle;

/**
 *
 * @author dsmelser
 */
public class Goblin {
    //field variables
    private int health = -1;
    private int attack = -1;
    
    private String name = "Bob";
    
    private Weapon goblinWeapon = new Weapon( "ERROR", 0 ); 
    private Armour goblinArmour = new Armour("ERROR", 0);
    

    public Goblin(String name, int health, int attack){
        this.name = name;
        this.health = health;
        this.attack = attack;
    }
    
    public void attack( Goblin that ){
        //1. compute damage
        //      min = 6
        //      max = 20
        //      add the attack value
        
        
        
        int damage = RNG.nextInt(6, 20) + attack + goblinWeapon.getDamage() - goblinArmour.getProtection();
        
        
      
        
        //2. adjust that's health
        int currentHealth = that.getHealth();
        int newHealth = currentHealth - damage;
        that.setHealth( newHealth );
        health+=2;
        
        
        
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the goblinWeapon
     */
    public Weapon getGoblinWeapon() {
        return goblinWeapon;
    }

    /**
     * @param goblinWeapon the goblinWeapon to set
     */
    public void setGoblinWeapon(Weapon goblinWeapon) {
        this.goblinWeapon = goblinWeapon;
    }
    
    public void setGoblinArmour(Armour goblinArmour){
        this.goblinArmour = goblinArmour;
    }
    
    
}
