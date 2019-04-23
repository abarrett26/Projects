/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the taemplate in the editor.p
 */
package com.vendingmachine.daos;

import com.vendingmachine.dtos.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author alexbarrett
 */
@Component
@Primary
public class TemplateDao implements VendingDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        List<Item> allItems = jdbc.query("Select * from Items ", new ItemMapper());
        return allItems;
    }

    @Override
    public void vendItem(Item toVend) throws PersistenceException {

        jdbc.update("UPDATE Items SET ItemName = ?, Quantity = ?, PriceInPennies = ? where ItemID = ?",
                toVend.getName(),
                toVend.getQuantity(),
                toVend.getPriceInPennies(),
                toVend.getId());
//        Item toReturn = jdbc.query("Select * from Items where ItemID = ?", );

    }

    @Override
    public Item getItemById(int itemId) {

        final String itemById = "select * from Items where ItemId = ?";
        return jdbc.queryForObject(itemById, new ItemMapper(), itemId);

    }

    @Override
    public boolean removeItem(int itemId) {
        int rowAffected = jdbc.update("delete from Items where ItemId = ? ", itemId);

        return rowAffected == 1;

    }

    @Override
    public int generateNewId(List<Item> allItems) {
        int toReturn = Integer.MIN_VALUE;

        if (allItems.isEmpty()) {
            toReturn = 0;
        } else {
            for (Item toInspect : allItems) {
                if (toInspect.getId() > toReturn) {
                    toReturn = toInspect.getId();
                }
            }

            toReturn++;
        }

        return toReturn;
    }



@Override
        public void addItem(Item toAdd) {
       
       jdbc.update("insert Items set ItemID =?, ItemName = ?, Quantity = ?, PriceInPennies = ?", toAdd.getId(), toAdd.getName(), toAdd.getQuantity(), toAdd.getPriceInPennies());
             
    

}

    @Override
    public void editItem(Item toEdit) {
       jdbc.update("update Items set ItemName = ?, Quantity = ?, PriceInPennies = ? WHERE ItemID =?", toEdit.getName(), toEdit.getQuantity(), toEdit.getPriceInPennies(), toEdit.getId());

    }

    
    
    private static final class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int index) throws SQLException {
        Item toGet = new Item();
        toGet.setId(rs.getInt("ItemID"));
        toGet.setName(rs.getString("ItemName"));
        toGet.setQuantity(rs.getInt("Quantity"));
        toGet.setPriceInPennies(rs.getInt("PriceInPennies"));
        return toGet;

    }

}
    
}
