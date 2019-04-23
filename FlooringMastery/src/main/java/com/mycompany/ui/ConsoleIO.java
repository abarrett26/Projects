/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author alexbarrett
 */
public class ConsoleIO implements UserIO{
    Scanner scn = new Scanner(System.in);

    @Override
    public void print(String prompt) {
        System.out.print(prompt);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String toReturn = scn.nextLine();
        return toReturn;
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Double.parseDouble(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
            
            }

        }

        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN;
        boolean correctInput = false;
        while( !correctInput ){
            toReturn = readDouble( prompt );
            correctInput = toReturn >= min && toReturn <= max;
        }
        
        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.NaN;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Float.parseFloat(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
             
            }

        }

        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean correctInput = false;
        while( !correctInput ){
            toReturn = readFloat( prompt );
            correctInput = toReturn >= min && toReturn <= max;
        }
        
        return toReturn;
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Integer.parseInt(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
               
            }

        }

        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean correctInput = false;
        while( !correctInput ){
            toReturn = readInt( prompt );
            correctInput = toReturn >= min && toReturn <= max;
        }
        
        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Long.parseLong(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
             
            }

        }

        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Long.MIN_VALUE;
        boolean correctInput = false;
        while( !correctInput ){
            toReturn = readLong( prompt );
            correctInput = toReturn >= min && toReturn <= max;
        }
        
        return toReturn;
    }
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal order = null;

        boolean isValid = false;
        while (!isValid) {
            String userInput = readString(prompt);
            try {
                order = new BigDecimal(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
               
            }
        }

        return order;
    }
    @Override
    public BigDecimal editBigDecimal(String prompt, BigDecimal oldValue){
        BigDecimal toReturn = null;
        
        boolean isValid = false;
        while(!isValid){
            String userInput = readString(prompt);
            if(userInput.isEmpty()){
                toReturn = oldValue;
                isValid = true;
            } else {
                try{
                    toReturn = new BigDecimal(userInput);
                    isValid = true;
                } catch (NumberFormatException ex){
                    
                }
            }
        }
        return toReturn;
    }

// read localdate

    @Override
    public LocalDate readDate(String prompt){
        LocalDate date = null;
        
        boolean isValid = false;
        while(!isValid){
            String userInput = readString(prompt);
            try{
                date = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                isValid = true;
            } catch (DateTimeParseException e) {
                
            }
        } return date;
    } 
    
    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max){
         LocalDate toReturn = LocalDate.MIN;
        boolean correctInput = false;
        while( !correctInput ){
            toReturn = readDate( prompt );
            correctInput = min.compareTo(toReturn) <= 0 && toReturn.compareTo(max) <= 0;
        }
        
        return toReturn;
    }
    
}