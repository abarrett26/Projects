/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.ui;

import com.vendingmachine.services.VendingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author alexbarrett
 */
@SpringBootApplication
@ComponentScan({"com.vendingmachine.*"})
public class Program  {
    
    @Autowired
   private VendingService service;
    
    

    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
    }

//        VendingDao dao = new FileDao( "/Users/alexbarrett/Desktop/VendingMachine.txt" );
//        VendingAuditDao vendingDao = new VendingAuditFileDao();
//        
   

}
