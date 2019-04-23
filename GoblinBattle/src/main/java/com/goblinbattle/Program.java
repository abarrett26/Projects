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
public class Program {

    public static void main(String[] arg) {

        Weapon fists = new Weapon("Fists", 2);
        Weapon sword = new Weapon("Short Sword", 6);
        Armour helmet = new Armour("helmet", 3);
        Armour sheild = new Armour("Shield", 3);
        
        

        Goblin a = new Goblin("Dave", 200, 5);
        a.setGoblinWeapon(sword);
        a.setGoblinArmour(helmet);

        Goblin b = new Goblin("Turbo", 200, 5);
        b.setGoblinWeapon(fists);
        b.setGoblinArmour(sheild);
        Goblin attacker = b;
        Goblin defender = a;

        while (a.getHealth() > 0 && b.getHealth() > 0) {
            System.out.println(attacker.getName() + "[" + attacker.getHealth() + "] is attacking " + defender.getName() + "[" + defender.getHealth() + "]");

            attacker.attack(defender);

            //flip attacker and defender
            Goblin temp = attacker;
            attacker = defender;
            defender = temp;
        }

        if (a.getHealth() > b.getHealth()) {
            //here we know a won
            //print something...
            System.out.println(a.getName() + " wins!");
        } else {
            //here we know b won
            //print something else
            System.out.println(b.getName() + " wins!");
        }

    }

}
