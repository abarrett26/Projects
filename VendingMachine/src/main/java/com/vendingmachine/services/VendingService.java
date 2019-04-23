/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

import com.vendingmachine.daos.PersistenceException;
import com.vendingmachine.daos.VendingAuditDao;
import com.vendingmachine.daos.VendingDao;
import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.dtos.VendingMachine;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexbarrett
 */
@Service
public class VendingService {

    VendingMachine machine = new VendingMachine();

    @Autowired
    VendingDao dao;

    @Autowired
    VendingAuditDao auditDao;

    public VendingService(VendingDao dao, VendingAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public PrintItemsResponse getAllItems() {

        PrintItemsResponse response = new PrintItemsResponse();
        try {

            List<Item> allItems = dao.getAllItems();

            machine.setToSell(allItems);

            response.setSuccess(true);
            response.setAllItems(allItems);

        } catch (PersistenceException ex) {
            response.setSuccess(false);
            response.setMessage("Error retrieving items.");
        }
        return response;
    }

    public AddMoneyResponse enterMoney(BigDecimal moneyEntered) {
        AddMoneyResponse response = new AddMoneyResponse();

        machine.enterMoney(moneyEntered);
        response.setTotalMoneyEntered(machine.getTotalMoney());
        response.setSuccess(true);

        return response;

    }

    public AddMoneyResponse enterPenny() {
        machine.enterPenny();
        return buildAddMoneyResponse(machine.getTotalMoney());
    }

    public AddMoneyResponse enterNickel() {
        machine.enterNickel();
        return buildAddMoneyResponse(machine.getTotalMoney());
    }

    public AddMoneyResponse enterDime() {
        machine.enterDime();
        return buildAddMoneyResponse(machine.getTotalMoney());
    }

    public AddMoneyResponse enterQuarter() {
        machine.enterQuarter();
        return buildAddMoneyResponse(machine.getTotalMoney());
    }

    public AddMoneyResponse enterDollar() {
        machine.enterDollar();
        return buildAddMoneyResponse(machine.getTotalMoney());
    }

    private AddMoneyResponse buildAddMoneyResponse(BigDecimal totalMoneyEntered) {
        AddMoneyResponse response = new AddMoneyResponse();
        response.setTotalMoneyEntered(totalMoneyEntered);
        response.setSuccess(true);
        return response;
    }

    public VendItemResponse vendItem(int id) {

        //check if item is real
        //check if item is in stock
        //check if enough money has been entered
        //if so, update the quantity (-1)
        //  tell the dao to record the new quantity
        //  return a successful response (if the dao worked)
        VendItemResponse response = new VendItemResponse();

        try {
            List<Item> allItems = dao.getAllItems();
            Item toVend = validate(id, allItems);

            toVend.setQuantity(toVend.getQuantity() - 1);
            dao.vendItem(toVend);
            BigDecimal price = toVend.getPrice();
            machine.enterMoney(price.negate());
            Change returnedChange = machine.returnChange();
            response.setReturnedChange(returnedChange);
            response.setVendedItem(toVend);
            response.setSuccess(true);

        } catch (InvalidIdException | OutofStockException | NotEnoughMoneyException | PersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());

        }

        return response;
    }

    private Item validate(int id, List<Item> allItems) throws InvalidIdException, OutofStockException, NotEnoughMoneyException, PersistenceException {
//        List<Item> getAllItems = dao.getAllItems();
        Item toReturn = null;

        for (Item toCheck : allItems) {
            if (id == toCheck.getId()) {
                toReturn = toCheck;
                break;
            }
        }

        if (toReturn == null) {

            throw new InvalidIdException("Error, could not find ID" + id);

        }
        if (toReturn.getQuantity() <= 0) {
            throw new OutofStockException("Error, out of stock");
        }
        int moneyEntered = machine.getTotalPenniesEntered();
        int price = toReturn.getPriceInPennies();

        if (price > moneyEntered) {
            throw new NotEnoughMoneyException("Error, not enough money.");
        }

        return toReturn;
    }

    public ChangeReturnResponse returnedChange() {
        ChangeReturnResponse response = new ChangeReturnResponse();
        Change returnedChange = machine.returnChange();
        response.setReturnedChange(returnedChange);
        response.setSuccess(true);
        return response;
    }

    public RemoveItemResponse removeItem(int itemId) {
          RemoveItemResponse toReturn = new RemoveItemResponse();
        try {
          
            List<Item> allItems = dao.getAllItems();
            
            boolean isValid = validateId(itemId, allItems);
            if (isValid) {
                Item matching = dao.getItemById(itemId);
                boolean success = dao.removeItem(itemId);
                if (success) {
                    toReturn.setRemovedItem(matching);
                    toReturn.setSuccess(true);
                    
                } else {
                    toReturn.setSuccess(false);
                    toReturn.setMessage("Failed to remove Item");
                }
            } else {
                toReturn.setSuccess(false);
                toReturn.setMessage("Invalid ItemId ");
            }
           
        } catch (PersistenceException ex) {
                toReturn.setSuccess(false);
                toReturn.setMessage("Error: unable to get items");
        }
        return toReturn;
    }

    private boolean validateId(int itemId, List<Item> allItems) {

        boolean toReturn = false;

        for (Item toCheck : allItems) {
            if (itemId == toCheck.getId()) {
                toReturn = true;
                break;
            }

        }
        return toReturn;
    }

    public AddItemResponse addItem(String itemName, int quantity, int priceInPennies) {
        AddItemResponse addResponse = new AddItemResponse();
        try {

            List<Item> allItems = dao.getAllItems();
            Item toAdd = new Item();

            toAdd.setName(itemName);
            toAdd.setQuantity(quantity);
            toAdd.setPriceInPennies(priceInPennies);
            toAdd.setId(dao.generateNewId(allItems));

            if (addResponse == null) {
                addResponse.setSuccess(false);
                addResponse.setMessage("Error: unable to add Item: ");
            } else {
                dao.addItem(toAdd);
                addResponse.setSuccess(true);
                addResponse.setAddedItem(toAdd);
            }

        } catch (PersistenceException ex) {
             addResponse.setSuccess(false);
             addResponse.setMessage("Error: unable to get items");
           
        }
        return addResponse;

    }

    public EditItemResponse editItem(int id, String name, int quantity, int priceInPennies) {
        EditItemResponse response = new EditItemResponse();
                try {

            List<Item> allItems = dao.getAllItems();
            Item toEdit = new Item();

            toEdit.setName(name);
            toEdit.setQuantity(quantity);
            toEdit.setPriceInPennies(priceInPennies);
            toEdit.setId(id);

            if (response == null) {
                response.setSuccess(false);
                response.setMessage("Error: unable to Edit Item: ");
            } else {

                dao.editItem(toEdit);
                response.setSuccess(true);
                response.setToEdit(toEdit);
            }

        } catch (PersistenceException ex) {
             response.setSuccess(false);
             response.setMessage("Error: unable to get items");
           
        }
        return response;
        
    }

}
