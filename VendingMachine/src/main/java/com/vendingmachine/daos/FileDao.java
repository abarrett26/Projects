///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vendingmachine.daos;
//
//import com.vendingmachine.dtos.Item;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author alexbarrett
// */
//public class FileDao implements VendingDao {
//
//    String path;
//
//    public FileDao(String path) {
//        this.path = path;
//    }
//
//    @Override
//    public List<Item> getAllItems() throws PersistenceException {
//
//        List<Item> toReturn = new ArrayList();
//
//        try {
//
//            File itemFile = new File(path);
//
//            if (!itemFile.exists()) {
//                itemFile.createNewFile();
//            }
//
//            Scanner reader = new Scanner(new BufferedReader(new FileReader(itemFile)));
//            while (reader.hasNextLine()) {
//                String line = reader.nextLine();
//                String[] cells = line.split("::");
//                Item toAdd = new Item();
//                toAdd.setId(Integer.parseInt(cells[0]));
//                toAdd.setName(cells[1]);
//                toAdd.setQuantity(Integer.parseInt(cells[2]));
//                toAdd.setPriceInPennies(Integer.parseInt(cells[3]));
//                toReturn.add(toAdd);
//            }
//        } catch (FileNotFoundException ex) {
//            throw new PersistenceException("Could not find item file", ex);
//
//        } catch (IOException ex) {
//            throw new PersistenceException("Could not open item file", ex);
//        }
//        return toReturn;
//
//    }
//
//    @Override
//    public void vendItem(Item toVend) throws PersistenceException {
//        Item toReturn = null;
//        List<Item> getAllItems = getAllItems();
//        int index = Integer.MIN_VALUE;
//        int id = toVend.getId();
//        for (int i = 0; i < getAllItems.size(); i++) {
//            Item toCheck = getAllItems.get(i);
//            if (toCheck.getId() == id) {
//                index = i;
//                break;
//            }
//        }
//        if (index >= 0 && index < getAllItems.size()) {
//            getAllItems.set(index, toVend);
//            boolean success = writeFile(getAllItems);
//            if (success) {
//                toReturn = toVend;
//            }
//        }
//    }
//
//    private boolean writeFile(List<Item> getAllItems) {
//
//        boolean success = false;
//
//        try {
//            PrintWriter pw = new PrintWriter(new FileWriter(path));
//
//            for (Item toWrite : getAllItems) {
//                String line = itemToLine(toWrite);
//                pw.println(line);
//            }
//
//            pw.flush();
//            pw.close();
//
//            success = true;
//        } catch (IOException ex) {
//            //throw a persistence exception here
//        }
//
//        return success;
//    }
//
//    private String itemToLine(Item toWrite) {
//        String toReturn
//                = toWrite.getId() + "::"
//                + toWrite.getName() + "::"
//                + toWrite.getQuantity() + "::"
//                + toWrite.getPriceInPennies();
//        return toReturn;
//    }
//
//    @Override
//    public Item getItemById(int itemId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean removeItem(int itemId) {
//        try {
//            int rowAffected = jdbc.update;
//            
//        } catch (PersistenceException ex) {
//          
//        }
//        
//    
//    }
//}
//
//
