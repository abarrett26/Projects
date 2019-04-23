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
public interface GameNightDao {

    //user CRUD
    Users getUserById(Integer userId) throws GameNightPersistenceException;

    Users getUserByUsername(String username) throws GameNightPersistenceException;

    List<Users> getAllUsers() throws GameNightPersistenceException;

    Users addUser(Users toAdd) throws GameNightPersistenceException;

    void editUser(Users toEdit) throws GameNightPersistenceException;

    void deleteUserById(Integer userId) throws GameNightPersistenceException;

    //character CRUD
    Characters getCharacterById(Integer characterId) throws GameNightPersistenceException;

    List<Characters> getAllCharacters() throws GameNightPersistenceException;

    List<Characters> getAllCharactersByUserId(Integer userId) throws GameNightPersistenceException;

    Characters addCharacter(Characters toAdd) throws GameNightPersistenceException;

    void editCharacter(Characters toEdit) throws GameNightPersistenceException;

    void deleteCharacterById(Integer characterId) throws GameNightPersistenceException;

    //role CRUD
    Roles getRoleById(Integer roleId) throws GameNightPersistenceException;

    Roles getRoleByRole(String role) throws GameNightPersistenceException;

    List<Roles> getAllRoles() throws GameNightPersistenceException;

    void deleteRole(Integer roleId) throws GameNightPersistenceException;

    void editRole(Roles role) throws GameNightPersistenceException;

    Roles addRole(Roles role) throws GameNightPersistenceException;

    //game CRUD
    public List<Games> getAllGames() throws GameNightPersistenceException;

    public Games getGameById(Integer gameId) throws GameNightPersistenceException;

    public Games addGame(Games toAdd) throws GameNightPersistenceException;

    public void editGame(Games toEdit) throws GameNightPersistenceException;
    
    //group CRUD
    public void deleteGroupById(Integer groupId) throws GameNightPersistenceException;

    public void deleteGameById(Integer gameId) throws GameNightPersistenceException;

    public Groups getGroupById(Integer groupId) throws GameNightPersistenceException;

    public List<Groups> getAllGroups() throws GameNightPersistenceException;

    public Groups addGroup(Groups toAdd) throws GameNightPersistenceException;

    public void editGroup(Groups toEdit) throws GameNightPersistenceException;

}
