/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblinbattle;

import java.util.Random;

/**
 *
 * @author alexbarrett
 */
public class RNG {
    private static Random generator = new Random();
    
    public static int nextInt(int min, int max){
        int range = max - min;
        int generatedNumber = generator.nextInt( range + 1 ) + min;
        
        return generatedNumber;
    }
    
    
}
