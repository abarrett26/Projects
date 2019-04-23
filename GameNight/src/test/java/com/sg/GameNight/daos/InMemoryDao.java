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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 16128
 */
public class InMemoryDao implements GameNightDao {

    List<Characters> allCharacters = new ArrayList<>();
    List<Games> allGames = new ArrayList<>();
    List<Groups> allGroups = new ArrayList<>();
    List<Roles> allRoles = new ArrayList<>();
    List<Users> allUsers = new ArrayList<>();
    

    public InMemoryDao() {
        Characters character1 = new Characters();
        character1.setCharacterId(1);
        character1.setName("Alexander");
        character1.setDescription("Can slide");
        character1.setType("Mele");
        character1.setLevel(1000);
        character1.setSpecialAbility("Teleports");
        character1.setEquipment("Fist");
        allCharacters.add(character1);

        Characters character2 = new Characters();
        character2.setCharacterId(2);
        character2.setName("Erin");
        character2.setDescription("Flys 1000mph");
        character2.setType("Archer");
        character2.setLevel(1000);
        character2.setSpecialAbility("Speed");
        character2.setEquipment("Dark Bow");
        allCharacters.add(character2);

        Games game1 = new Games();
        game1.setGameId(1);
        game1.setName("Cool Game Night");
        game1.setDescription("It's just cool");
        game1.setActive(true);
        //acceptingPlayers variable in database is not in dto. 
        allGames.add(game1);

        Games game2 = new Games();
        game2.setGameId(2);
        game2.setName("Alex's Game");
        game2.setDescription("Wow it's so snazzy");
        game2.setActive(false);
        allGames.add(game2);

        Groups group1 = new Groups();
        group1.setGroupId(1);
        group1.setName("California");
        group1.setDescription("California group");
        group1.setAcceptingPlayers(true);
        allGroups.add(group1);

        Groups group2 = new Groups();
        group2.setGroupId(2);
        group2.setName("Minnesota");
        group2.setDescription("MN group");
        group2.setAcceptingPlayers(false);
        allGroups.add(group2);

        Roles role1 = new Roles();
        role1.setRoleId(1);
        role1.setRole("ROLE_ADMIN");
        allRoles.add(role1);
        //double check these roles.
        Roles role2 = new Roles();
        role2.setRoleId(2);
        role2.setRole("ROLE_USER");
        allRoles.add(role2);

        Users user1 = new Users();
        user1.setUserId(1);
        user1.setUsername("alexander");
        user1.setPassword("macsrule1!");
      //  user1.setRoles();
        allUsers.add(user1);

        Users user2 = new Users();
        user2.setUserId(2);
        user2.setUsername("thomas");
        user2.setPassword("macsrule1!");
        allUsers.add(user2);

        Users user3 = new Users();
        user3.setUserId(3);
        user3.setUsername("erin");
        user3.setPassword("macsrule1!");
        allUsers.add(user3);

    }

    @Override
    public Users getUserById(Integer userId) throws GameNightPersistenceException {
        Users toReturn = null;
        for (Users toCheck : allUsers) {
            if (toCheck.getUserId().equals(userId)) {
                toReturn = toCheck;
            }
        }
        return toReturn;
    }

    @Override
    public Users getUserByUsername(String username) throws GameNightPersistenceException {
        Users toReturn = null;
        for (Users toCheck : allUsers) {
            if (toCheck.getUsername().equals(username)) {
                toReturn = toCheck;
            }
        }
        return toReturn;
    }

    @Override
    public List<Users> getAllUsers() throws GameNightPersistenceException {
        return allUsers;
    }

    @Override
    public Users addUser(Users toAdd) throws GameNightPersistenceException {
        toAdd.setUserId(generateUserId(allUsers));
        allUsers.add(toAdd);
        return toAdd;
    }

    @Override
    public void editUser(Users toEdit) throws GameNightPersistenceException {

        int index = Integer.MIN_VALUE;
        int id = toEdit.getUserId();
        for (int i = 0; i < allUsers.size(); i++) {
            Users toCheck = allUsers.get(i);
            if (toCheck.getUserId() == id) {
                index = i;
                break;
            }
        }
        if (index >= 0 && index < allUsers.size()) {

            allUsers.set(index, toEdit);

        }

    }

    @Override
    public void deleteUserById(Integer userId) throws GameNightPersistenceException {
         List<Users> usersToRemove = getAllUsers();
        
        int index = Integer.MIN_VALUE;
        
        for(int i = 0; i < usersToRemove.size(); i++){
            Users toCheck = usersToRemove.get(i);
            if(toCheck.getUserId()== userId){
                index = i;
                break;
            }
        }
        usersToRemove.remove(index);
    }

    @Override
    public Characters getCharacterById(Integer characterId) throws GameNightPersistenceException {
        Characters toReturn = null;
        for (Characters toCheck : allCharacters) {
            if (toCheck.getCharacterId().equals(characterId)) {
                toReturn = toCheck;
            }
        }
        return toReturn;
    }

    @Override
    public List<Characters> getAllCharacters() throws GameNightPersistenceException {
        return allCharacters;
    }

    @Override
    public Characters addCharacter(Characters toAdd) throws GameNightPersistenceException {
        toAdd.setCharacterId(generateCharacterId(allCharacters));
        allCharacters.add(toAdd);
        return toAdd;
    }

    @Override
    public void editCharacter(Characters toEdit) throws GameNightPersistenceException {
        int index = Integer.MIN_VALUE;
        int id = toEdit.getCharacterId();
        for (int i = 0; i < allCharacters.size(); i++) {
            Characters toCheck = allCharacters.get(i);
            if (toCheck.getCharacterId() == id) {
                index = i;
                break;
            }
        }
        if (index >= 0 && index < allCharacters.size()) {

            allCharacters.set(index, toEdit);

        }
    }

    @Override
    public void deleteCharacterById(Integer characterId) throws GameNightPersistenceException {
       List<Characters> charactersToRemove = getAllCharacters();
        
        int index = Integer.MIN_VALUE;
        
        for(int i = 0; i < charactersToRemove.size(); i++){
            Characters toCheck = charactersToRemove.get(i);
            if(toCheck.getUserId()== characterId){
                index = i;
                break;
            }
        }
        charactersToRemove.remove(index);
    }
       
       
    

    @Override
    public Roles getRoleById(Integer roleId) throws GameNightPersistenceException {
        Roles toReturn = null;
        for (Roles toCheck : allRoles) {
            if (toCheck.getRoleId().equals(roleId)) {
                toReturn = toCheck;
            }
        }
        return toReturn;
    }

    @Override
    public Roles getRoleByRole(String role) throws GameNightPersistenceException {
        Roles toReturn = null;
        for (Roles toCheck : allRoles) {
            if (toCheck.getRole().equals(role)) {
                toReturn = toCheck;
            }
        }
        return toReturn;
    }

    @Override
    public List<Roles> getAllRoles() throws GameNightPersistenceException {
        return allRoles;
    }

    @Override
    public void deleteRole(Integer roleId) throws GameNightPersistenceException {
        List<Roles> rolesToRemove = getAllRoles();
        
        int index = Integer.MIN_VALUE;
        
        for(int i = 0; i < rolesToRemove.size(); i++){
            Roles toCheck = rolesToRemove.get(i);
            if(toCheck.getRoleId()== roleId){
                index = i;
                break;
            }
        }
        rolesToRemove.remove(index);
    }

    @Override
    public void editRole(Roles role) throws GameNightPersistenceException {
        int index = Integer.MIN_VALUE;
        int id = role.getRoleId();
        for (int i = 0; i < allRoles.size(); i++) {
            Roles toCheck = allRoles.get(i);
            if (toCheck.getRoleId() == id) {
                index = i;
                break;
            }
        }
        if (index >= 0 && index < allRoles.size()) {

            allRoles.set(index, role);

        }
    }

    @Override
    public Roles addRole(Roles role) throws GameNightPersistenceException {
        role.setRoleId(generateRolesId(allRoles));
        allRoles.add(role);
        return role;

    }

    private Integer generateUserId(List<Users> allUsers) {
        Integer toReturn = Integer.MIN_VALUE;
        if (allUsers.isEmpty()) {
            toReturn = 1;
        } else {
            for (Users toInspect : allUsers) {
                if (toInspect.getUserId() > toReturn) {
                    toReturn = toInspect.getUserId();
                }
            }
            toReturn++;
        }
        return toReturn;
    }

    private Integer generateGamesId(List<Games> allGames) {
        Integer toReturn = Integer.MIN_VALUE;
        if (allGames.isEmpty()) {
            toReturn = 1;
        } else {
            for (Games toInspect : allGames) {
                if (toInspect.getGameId() > toReturn) {
                    toReturn = toInspect.getGameId();
                }
            }
            toReturn++;
        }
        return toReturn;
    }

    private Integer generateGroupId(List<Groups> allGroups) {
        Integer toReturn = Integer.MIN_VALUE;
        if (allGroups.isEmpty()) {
            toReturn = 1;
        } else {
            for (Groups toInspect : allGroups) {
                if (toInspect.getGroupId() > toReturn) {
                    toReturn = toInspect.getGroupId();
                }
            }
            toReturn++;
        }
        return toReturn;
    }

    private Integer generateRolesId(List<Roles> allRoles) {
        Integer toReturn = Integer.MIN_VALUE;
        if (allRoles.isEmpty()) {
            toReturn = 1;
        } else {
            for (Roles toInspect : allRoles) {
                if (toInspect.getRoleId() > toReturn) {
                    toReturn = toInspect.getRoleId();
                }
            }
            toReturn++;
        }
        return toReturn;
    }

    private Integer generateCharacterId(List<Characters> allCharacters) {
        Integer toReturn = Integer.MIN_VALUE;
        if (allCharacters.isEmpty()) {
            toReturn = 1;
        } else {
            for (Characters toInspect : allCharacters) {
                if (toInspect.getCharacterId() > toReturn) {
                    toReturn = toInspect.getCharacterId();
                }
            }
            toReturn++;
        }
        return toReturn;
    }

    @Override
    public List<Characters> getAllCharactersByUserId(Integer userId) throws GameNightPersistenceException {
       
       throw new UnsupportedOperationException("Not supported yet."); 
    } 


    @Override
    public List<Games> getAllGames() throws GameNightPersistenceException {
        return allGames;
    }

    @Override
    public Games getGameById(Integer gameId) throws GameNightPersistenceException {
        Games toReturn = null;
        for (Games toCheck : allGames) {
            if (toCheck.getGameId()==(gameId)) {
                toReturn = toCheck;
            }
        }
        return toReturn;

    }

    @Override
    public Games addGame(Games toAdd) throws GameNightPersistenceException {
        toAdd.setGameId(generateGamesId(allGames));
        allGames.add(toAdd);
        return toAdd;
    }

    @Override
    public void editGame(Games toEdit) throws GameNightPersistenceException {
        int index = Integer.MIN_VALUE;
        int id = toEdit.getGameId();
        for (int i = 0; i < allGames.size(); i++) {
            Games toCheck = allGames.get(i);
            if (toCheck.getGameId()== id) {
                index = i;
                break;
            }
        }
        if (index >= 0 && index < allGames.size()) {

            allGames.set(index, toEdit);

        }
        
    }

    @Override
    public void deleteGroupById(Integer groupId) throws GameNightPersistenceException {
        List<Groups> groupsToRemove = getAllGroups();
        
        int index = Integer.MIN_VALUE;
        
        for(int i = 0; i < groupsToRemove.size(); i++){
            Groups toCheck = groupsToRemove.get(i);
            if(toCheck.getGroupId()== groupId){
                index = i;
                break;
            }
        }
        groupsToRemove.remove(index);
    }
    

    @Override
    public void deleteGameById(Integer gameId) throws GameNightPersistenceException {
        List<Games> gamesToRemove = getAllGames();
        
        int index = Integer.MIN_VALUE;
        
        for(int i = 0; i < gamesToRemove.size(); i++){
            Games toCheck = gamesToRemove.get(i);
            if(toCheck.getGameId()== gameId){
                index = i;
                break;
            }
        }
        gamesToRemove.remove(index);
    }
      

    @Override
    public Groups getGroupById(Integer groupId) throws GameNightPersistenceException {
        Groups group = null;
        for(Groups toCheck : allGroups){
            if(toCheck.getGroupId() == (groupId)){
                group = toCheck;
            }
                   
        }
        return group;
    }

    @Override
    public List<Groups> getAllGroups() throws GameNightPersistenceException { 
        return allGroups;
    }

    @Override
    public Groups addGroup(Groups toAdd) throws GameNightPersistenceException {
        toAdd.setGroupId(generateGroupId(allGroups));
        allGroups.add(toAdd);
        return toAdd;
    }

    @Override
    public void editGroup(Groups toEdit) throws GameNightPersistenceException {
        int index = Integer.MIN_VALUE;
        int id = toEdit.getGroupId();
        for (int i = 0; i < allGroups.size(); i++) {
            Groups toCheck = allGroups.get(i);
            if (toCheck.getGroupId()== id) {
                index = i;
                break;
            }
        }
        if (index >= 0 && index < allGroups.size()) {

            allGroups.set(index, toEdit);

        }
    }

}
