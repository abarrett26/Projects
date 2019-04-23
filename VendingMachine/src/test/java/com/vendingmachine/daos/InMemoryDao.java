///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vendingmachine.daos;
//
//import com.vendingmachine.dtos.Item;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author alexbarrett
// */
//public class InMemoryDao implements VendingDao {
//
//    List<Item> allItems = new ArrayList();
//
//    public InMemoryDao() {
//        Item coke = new Item();
//        coke.setId(1);
//        coke.setName("Coke");
//        coke.setPriceInPennies(75);
//        coke.setQuantity(100);
//        allItems.add(coke);
//
//        Item sprite = new Item();
//        sprite.setId(2);
//        sprite.setName("sprite");
//        sprite.setPriceInPennies(75);
//        sprite.setQuantity(0);
//        allItems.add(sprite);
//    }
//
//    @Override
//    public List<Item> getAllItems() {
//        return allItems;
//    }
//
//    //public void vendItem(int id) throws PersistenceException {
//    //  Item toReturn = getItemById(id);
//    //  toReturn.setQuantity(toReturn.getQuantity() - 1 );
//    //  return toReturn;
//    // }
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
//        }
//    }
//}
