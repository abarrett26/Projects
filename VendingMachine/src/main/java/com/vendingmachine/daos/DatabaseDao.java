package com.sg.vendingmachine.daos;
​
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.vendingmachine.dtos.Item;
import com.vendingmachine.daos.PersistenceException;
import com.vendingmachine.daos.VendingDao;
import com.vendingmachine.dtos.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;.mysql.cj.jdbc.MysqlDataSource;
import com.sg.vendingmachine.dtos.Item;
import com.vendingmachine.daos.VendingDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
​
/**
 *
 * @author alexbarrett
 */
public class DatabaseDao implements VendingDao {
  DataSource source;
  
  public DatabaseDao() throws SQLException{
    try {
    MysqlDataSource sqlSource = new MysqlDataSource();
    sqlSource.setServerName("localhost");
    sqlSource.setDatabaseName("vendingmachine");
    sqlSource.setUser("root");
    sqlSource.setPassword("mpls-6157");
    sqlSource.setServerTimezone("America/Chicago");
    sqlSource.setUseSSL(false);
    sqlSource.setAllowPublicKeyRetrieval(true);
    source = sqlSource;
    } catch (SQLException ex) {
      //something here?
    }
  }
  
  @Override
  public List<Item> getAllItems() throws PersistenceException {
    List<Item> allItems = new ArrayList<>();
    try(Connection conn = source.getConnection()){ //this is a try with resources 
      Statement stmt = conn.createStatement();
      ResultSet results = stmt.executeQuery("SELECT * FROM Items");
      while (results.next()){
        //must do next at least once to advance it to the first row of actual data
        Item toAdd = new Item();
        toAdd.setId(results.getInt("ItemId"));
        toAdd.setName(results.getString("ItemName"));
        toAdd.setQuantity(results.getInt("Quantity"));
        toAdd.setPriceInPennies(results.getInt("PriceInPennies"));
        allItems.add(toAdd);
      }
    } catch (SQLException ex) {
      //something here? 
    }
    return allItems;
  }
​
  @Override
  public Item vendItem(Item toVend) throws PersistenceException {
   
  }

    @Override
    public List<Item> getAllItems() throws PersistenceException {
         Item toReturn = new Item();
    
    try (Connection conn = source.getConnection()){
//      Statement stmt = conn.createStatement();
      PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM Items WHERE ItemId = ?");
      selectStmt.setInt(1, toVend.getId());
      ResultSet results = selectStmt.executeQuery();
      if(results.next()){
        toReturn.setId(results.getInt("ItemId"));
        toReturn.setName(results.getString("ItemName"));
        toReturn.setQuantity(results.getInt("Quantity"));
        toReturn.setPriceInPennies(results.getInt("PriceInPennies"));
        toReturn.setQuantity(toReturn.getQuantity()-1);
        
        PreparedStatement updateStatement = conn.prepareCall("UPDATE Items SET Quantity = ? WHERE ItemId = ?"); //question marks as placeholders
        updateStatement.setInt(1, toReturn.getQuantity());
        updateStatement.setInt(2, toVend.getId());
        int rowsAffected = updateStatement.executeUpdate();
        
        if (rowsAffected == 0){
          throw new VendingMachinePersistenceException("Failed to update any rows");
        } else if (rowsAffected > 1){
          throw new VendingMachinePersistenceException("SEVER ERROR: Update affected too many rows");
        }
      }
    } catch (SQLException ex) {
      //something here?
    }
    return toReturn;
    }

    @Override
    public void vendItem(Item toVend) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getItemById(int itemId) {
      throw   new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}