/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import com.mycompany.controller.MainMenu;
import com.mycompany.dao.OrderDao;
import com.mycompany.dao.OrderFileDao;
import com.mycompany.dao.ProductDao;
import com.mycompany.dao.ProductFileDao;
import com.mycompany.dao.TaxDao;
import com.mycompany.dao.TaxFileDao;
import com.mycompany.services.FlooringService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author alexbarrett
 */
public class Program {
    public static void main(String[] args) {
//        OrderDao toTest = new OrderFileDao();
//      
        //LocalDate date = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyy"));
        TaxDao tDao = new TaxFileDao ("/Users/alexbarrett/Desktop/Taxes.txt");
        ProductDao pDao = new ProductFileDao ("/Users/alexbarrett/Desktop/Products.txt");
        OrderDao dao = new OrderFileDao( "/Users/alexbarrett/Desktop/data 2");
        FlooringService service = new FlooringService(dao, pDao, tDao);
        MainMenu menu = new MainMenu();
        menu.run(service);
    }
}
