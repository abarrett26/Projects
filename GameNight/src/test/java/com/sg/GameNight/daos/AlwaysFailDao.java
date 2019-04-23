/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.daos;

import com.sg.GameNight.dtos.Characters;
import com.sg.GameNight.dtos.Games;
import com.sg.GameNight.dtos.Groups;
import com.sg.GameNight.dtos.Roles;
import com.sg.GameNight.dtos.Users;
import java.util.List;

/**
 *
 * @author 16128
 */
public class AlwaysFailDao implements GameNightDao {

    @Override
    public Users getUserById(Integer userId) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public Users getUserByUsername(String username) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public List<Users> getAllUsers() throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public Users addUser(Users toAdd) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public void editUser(Users toEdit) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public void deleteUserById(Integer userId) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public Characters getCharacterById(Integer characterId) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public List<Characters> getAllCharacters() throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public Characters addCharacter(Characters toAdd) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public void editCharacter(Characters toEdit) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public void deleteCharacterById(Integer characterId) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public Roles getRoleById(Integer roleId) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public Roles getRoleByRole(String role) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public List<Roles> getAllRoles() throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public void deleteRole(Integer roleId) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public void editRole(Roles role) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public Roles addRole(Roles role) throws GameNightPersistenceException {
         throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public List<Characters> getAllCharactersByUserId(Integer userId) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Games> getAllGames() throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Games getGameById(Integer gameId) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Games addGame(Games toAdd) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editGame(Games toEdit) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteGroupById(Integer groupId) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteGameById(Integer gameId) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Groups getGroupById(Integer groupId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Groups> getAllGroups() throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Groups addGroup(Groups toAdd) throws GameNightPersistenceException {
        throw new GameNightPersistenceException("Failed to retrieve data");
    }

    @Override
    public void editGroup(Groups toEdit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}