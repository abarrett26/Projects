/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.ui.controllers;

import com.vendingmachine.dtos.Item;
import com.vendingmachine.services.AddItemResponse;
import com.vendingmachine.services.AddMoneyResponse;
import com.vendingmachine.services.ChangeReturnResponse;
import com.vendingmachine.services.EditItemResponse;
import com.vendingmachine.services.PrintItemsResponse;
import com.vendingmachine.services.RemoveItemResponse;
import com.vendingmachine.services.VendItemResponse;
import com.vendingmachine.services.VendingService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.QueryParam;

/**
 *
 * @author alexbarrett
 */
@RestController
@RequestMapping("/api")

public class VendingMachineApiController {

    @GetMapping("/test")
    public String controllerTest() {
        return "yodel boy";
    }

    @Autowired
    private VendingService service;

    @GetMapping("/displayAll")
    public List<Item> displayAll() {
        PrintItemsResponse response = service.getAllItems();

        List<Item> allItems = response.getAllItems();

        return allItems;

    }

    @GetMapping("/display/{id}")
    public Item displaySingle(@PathVariable int id) {
        Item toReturn = null;
        List<Item> allItems = displayAll();
        for (Item toCheck : allItems) {
            if (id == toCheck.getId()) {
                toReturn = toCheck;
            }
        }
        return toReturn;
    }

    @DeleteMapping("/remove/{id}")
    public RemoveItemResponse remove(@PathVariable int id) {
        RemoveItemResponse response = service.removeItem(id);
        return response;

    }

    @GetMapping("/add")
    public AddItemResponse addItem(@QueryParam("name") String name, @QueryParam("quantity") int quantity, @QueryParam("priceinpennies") int priceInPennies) {
        AddItemResponse response = service.addItem(name, quantity, priceInPennies);
        return response;
    }

    @GetMapping("/edit")
    public EditItemResponse editItem(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("quantity") int quantity, @QueryParam("priceinpennies") int priceInPennies) {
        EditItemResponse response = service.editItem(id, name, quantity, priceInPennies);
        return response;
    }

    @GetMapping("/addMoney/{moneyEntered}")
    public AddMoneyResponse addMoney(@PathVariable BigDecimal moneyEntered) {
        AddMoneyResponse response = service.enterMoney(moneyEntered);
        return response;
    }

    @GetMapping("/vendItem/{id}")
    public VendItemResponse vendItem(@PathVariable int id) {
        VendItemResponse response = service.vendItem(id);
        return response;
    }
    
    @GetMapping("/returnChange")
    public ChangeReturnResponse returnChange() {
    ChangeReturnResponse response = service.returnedChange();
    return response;
}

}
