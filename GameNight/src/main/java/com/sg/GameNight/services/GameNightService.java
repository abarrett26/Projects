/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.services;

import com.sg.GameNight.dtos.Characters;
import com.sg.GameNight.dtos.Roles;
import com.sg.GameNight.dtos.Users;
import com.sg.GameNight.daos.GameNightDao;
import com.sg.GameNight.daos.GameNightPersistenceException;
import com.sg.GameNight.dtos.Games;
import com.sg.GameNight.dtos.Groups;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 16128
 */
@Service
public class GameNightService {

    @Autowired
    GameNightDao dao;

    public GameNightService(GameNightDao dao) {
        this.dao = dao;
    }

    //User CRUD
    public Response<List<Users>> getAllUsers() {
        Response<List<Users>> response = new Response<List<Users>>();

        try {
            List<Users> allUsers = dao.getAllUsers();
            response.setSuccess(true);
            response.setResponseData(allUsers);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public Response<Users> getUserById(Integer userId) {
        Response<Users> response = new Response<Users>();
        try {
            response.setResponseData(dao.getUserById(userId));
            response.setSuccess(true);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Users> getUserByUsername(String username) {
        Response<Users> response = new Response<Users>();
        try {
            response.setResponseData(dao.getUserByUsername(username));
            response.setSuccess(true);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Users> addUser(Users user) {
        Response<Users> response = new Response<Users>();
        try {
            validateUser(user);
            user = dao.addUser(user);
            response.setSuccess(true);
            response.setResponseData(user);
        } catch (GameNightPersistenceException | InvalidUsernameException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Users> editUser(Users toEdit) {
        Response<Users> response = new Response<Users>();
        try {
            List<Users> allUsers = dao.getAllUsers();
            validateUserId(toEdit.getUserId(), allUsers);
            validateUser(toEdit);
            dao.editUser(toEdit);
            response.setSuccess(true);
            response.setResponseData(toEdit);
        } catch (GameNightPersistenceException | InvalidUsernameException | InvalidIdException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Users> deleteUserById(Integer userId) {
        Response<Users> response = new Response<Users>();

        try {
            List<Users> allUsers = dao.getAllUsers();
            validateUserId(userId, allUsers);
            dao.deleteUserById(userId);
            response.setSuccess(true);
        } catch (GameNightPersistenceException | InvalidIdException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    private void validateUser(Users toAdd) throws InvalidUsernameException {
        if (toAdd.getUsername().equals("")) {
            throw new InvalidUsernameException("Username cannot be blank.");
        }
    }

    private void validateUserId(int userId, List<Users> allUsers) throws InvalidIdException {
        boolean found = false;
        for (Users toCheck : allUsers) {
            if (userId == toCheck.getUserId()) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidIdException("This userId does not exist");
        }
    }

    //Character CRUD
    public Response<List<Characters>> getAllCharacters() {
        Response<List<Characters>> response = new Response<List<Characters>>();

        try {
            List<Characters> allCharacters = dao.getAllCharacters();
            response.setSuccess(true);
            response.setResponseData(allCharacters);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
//alex
    public Response<Characters> getCharacterById(Integer characterId) {
        Response<Characters> response = new Response<Characters>();
        try {
            response.setResponseData(dao.getCharacterById(characterId));
            response.setSuccess(true);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Characters> addCharacter(Characters toAdd) {
        Response<Characters> response = new Response<Characters>();
        try {
            List<Characters> allCharacters = dao.getAllCharacters();
            validateCharacter(toAdd);
//            validateCharacterId(toAdd.getCharacterId(), allCharacters);
            dao.addCharacter(toAdd);
            response.setSuccess(true);
            response.setResponseData(toAdd);
        } catch (GameNightPersistenceException | InvalidAbilityException | InvalidCharacterLevelException | InvalidEquipmentException | InvalidCharacterNameException | InvalidCharacterTypeException | InvalidCharacterDescriptionException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Characters> editCharacter(Characters toEdit) {
        Response<Characters> response = new Response<Characters>();
        try {
            List<Characters> allCharacters = dao.getAllCharacters();
            validateCharacter(toEdit);
            validateCharacterId(toEdit.getCharacterId(), allCharacters);
            dao.editCharacter(toEdit);
            response.setSuccess(true);
            response.setResponseData(toEdit);
        } catch (GameNightPersistenceException | InvalidAbilityException | InvalidCharacterLevelException | InvalidEquipmentException | InvalidCharacterNameException | InvalidIdException | InvalidCharacterTypeException | InvalidCharacterDescriptionException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Characters> deleteCharacterById(Integer characterId) {
        Response<Characters> response = new Response<Characters>();
        try {
            List<Characters> allCharacters = dao.getAllCharacters();
            validateCharacterId(characterId, allCharacters);
            dao.deleteCharacterById(characterId);
            response.setSuccess(true);
        } catch (GameNightPersistenceException | InvalidIdException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;

    }

    private void validateCharacter(Characters toAdd) throws InvalidCharacterNameException, InvalidCharacterDescriptionException, InvalidCharacterTypeException, InvalidAbilityException, InvalidEquipmentException, InvalidCharacterLevelException {
        if (toAdd.getName().equals("")) {
            throw new InvalidCharacterNameException("Username cannot be blank.");
        }
        if (toAdd.getDescription().equals("")) {
            throw new InvalidCharacterDescriptionException("Description cannot be blank.");
        }
        if (toAdd.getType().equals("")) {
            throw new InvalidCharacterTypeException("Type cannot be blank.");
        }
        if (toAdd.getSpecialAbility().equals("")) {
            throw new InvalidAbilityException("Ability cannot be blank.");
        }
        if (toAdd.getEquipment().equals("")) {
            throw new InvalidEquipmentException("Equipment cannot be blank.");
        }
        if (toAdd.getLevel() == null) {
            throw new InvalidCharacterLevelException("Level cannot be blank.");
        }
        //other stuff to validate?

    }

    private void validateCharacterId(Integer characterId, List<Characters> allCharacters) throws InvalidIdException {
        boolean found = false;
        for (Characters toCheck : allCharacters) {
            if (characterId == toCheck.getCharacterId()) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidIdException("This characterId does not exist");
        }
    }

    //Role CRUD
    public Response<Roles> getRoleByRole(String roleUser) {
        Response<Roles> response = new Response<Roles>();
        try {
            response.setResponseData(dao.getRoleByRole(roleUser));
            response.setSuccess(true);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<List<Roles>> getAllRoles() {
        Response<List<Roles>> response = new Response<List<Roles>>();

        try {
            List<Roles> allRoles = dao.getAllRoles();
            response.setSuccess(true);
            response.setResponseData(allRoles);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Roles> getRoleById(int roleId) {
        Response<Roles> response = new Response<Roles>();
        try {
            response.setResponseData(dao.getRoleById(roleId));
            response.setSuccess(true);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    //groupCRUD
    public Response<List<Groups>> getAllGroups() {
        Response<List<Groups>> response = new Response<List<Groups>>();
        try {
            List<Groups> allGroups = dao.getAllGroups();
            response.setSuccess(true);
            response.setResponseData(allGroups);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Groups> getGroupById(Integer groupId) {
        Response<Groups> response = new Response<Groups>();
        try {
            response.setResponseData(dao.getGroupById(groupId));
            response.setSuccess(true);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Groups> addGroup(Groups toAdd) {
        Response<Groups> response = new Response<Groups>();
        try {
            List<Groups> allGroups = dao.getAllGroups();
            validateGroup(toAdd);
            validateGroupId(toAdd.getGroupId(), allGroups);
            dao.addGroup(toAdd);
            response.setSuccess(true);
            response.setResponseData(toAdd);
        } catch (GameNightPersistenceException | InvalidIdException | InvalidGroupNameException | InvalidGroupDescriptionException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Groups> deleteGroupById(Integer groupId) {
        Response<Groups> response = new Response<Groups>();
        try {
            List<Groups> allGroups = dao.getAllGroups();
            validateGroupId(groupId, allGroups);
            dao.deleteGroupById(groupId);
            response.setSuccess(true);
        } catch (GameNightPersistenceException | InvalidIdException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Groups> editGroup(Groups toEdit) {
        Response<Groups> response = new Response<Groups>();
        try {
            List<Groups> allGroups = dao.getAllGroups();
            validateGroup(toEdit);
            validateGroupId(toEdit.getGroupId(), allGroups);
            dao.editGroup(toEdit);
            response.setSuccess(true);
            response.setResponseData(toEdit);
        } catch (GameNightPersistenceException | InvalidGroupNameException | InvalidGroupDescriptionException | InvalidIdException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    private void validateGroup(Groups toAdd) throws InvalidGroupNameException, InvalidGroupDescriptionException {
        if (toAdd.getName().equals("")) {
            throw new InvalidGroupNameException("Group name cannot be blank.");
        }
        if (toAdd.getDescription().equals("")) {
            throw new InvalidGroupDescriptionException("Group description cannot be blank.");
        }
    }

    private void validateGroupId(Integer groupId, List<Groups> allGroups) throws InvalidIdException {
        boolean found = false;
        for (Groups toCheck : allGroups) {
            if (groupId == toCheck.getGroupId()) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidIdException("This groupId does not exist");
        }
    }

    //game CRUD
    public Response<List<Games>> getAllGames() {
        Response<List<Games>> response = new Response<List<Games>>();

        try {
            List<Games> allGames = dao.getAllGames();
            response.setSuccess(true);
            response.setResponseData(allGames);
        } catch (GameNightPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Games> getGameById(Integer gameId) {
        Response<Games> response = new Response<Games>();
        try {
            List<Games> allGames = dao.getAllGames();
            validateGameId(gameId, allGames);
            Games toGet = dao.getGameById(gameId);
            response.setResponseData(toGet);
            response.setSuccess(true);
        } catch (GameNightPersistenceException | InvalidIdException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Games> addGame(Games toAdd) {
        Response<Games> response = new Response<Games>();
        try {
            List<Games> allGames = dao.getAllGames();
            validateGame(toAdd);
            dao.addGame(toAdd);
//            validateGameId(toAdd.getGameId(), allGames);
            response.setSuccess(true);
            response.setResponseData(toAdd);
//            InvalidIdException
        } catch (GameNightPersistenceException | InvalidGameNameException | InvalidGameDescriptionException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Games> deleteGameById(Integer gameId) {
        Response<Games> response = new Response<Games>();
        try {
            List<Games> allGames = dao.getAllGames();
            validateGameId(gameId, allGames);
            dao.deleteGameById(gameId);
            response.setSuccess(true);
        } catch (GameNightPersistenceException | InvalidIdException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Games> editGame(Games toEdit) {
        Response<Games> response = new Response<Games>();
        try {
            List<Games> allGames = dao.getAllGames();
            validateGame(toEdit);
            validateGameId(toEdit.getGameId(), allGames);
            dao.editGame(toEdit);
            response.setSuccess(true);
            response.setResponseData(toEdit);
        } catch (GameNightPersistenceException | InvalidIdException | InvalidGameNameException | InvalidGameDescriptionException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    private void validateGame(Games toAdd) throws InvalidGameNameException, InvalidGameDescriptionException {
        if (toAdd.getName().equals("")) {
            throw new InvalidGameNameException("Game name cannot be blank.");
        }
        if (toAdd.getDescription().equals("")) {
            throw new InvalidGameDescriptionException("Game description cannot be blank.");
        }
    }

    private void validateGameId(int gameId, List<Games> allGames) throws InvalidIdException {
        boolean found = false;
        for (Games toCheck : allGames) {
            if (gameId == toCheck.getGameId()) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidIdException("This gameId does not exist");
        }
    }
}
