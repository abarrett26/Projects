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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexbarrett
 */
@Repository
public class GameNightDbDao implements GameNightDao {

    @Autowired
    JdbcTemplate jdbc;

    public GameNightDbDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //userCRUD
    @Override
    public Users getUserById(Integer userId) throws GameNightPersistenceException {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE userId = ?";
            Users user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), userId);
            user.setRoles(getRolesForUser(user.getUserId()));
            return user;
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve user from database.");
        }
    }

    @Override
    public Users getUserByUsername(String username) throws GameNightPersistenceException {
        try {
            final String SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
            Users user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), username);
            user.setRoles(getRolesForUser(user.getUserId()));
            return user;
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve user from database.");
        }
    }

    @Override
    public List<Users> getAllUsers() throws GameNightPersistenceException {
        List<Users> users = new ArrayList<>();
        try {
            final String SELECT_ALL_USERS = "SELECT * FROM users";
            users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
            for (Users user : users) {
                user.setRoles(getRolesForUser(user.getUserId()));
            }
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve users from database.");
        }
        return users;
    }

    @Override
    @Transactional
    public Users addUser(Users user) throws GameNightPersistenceException {

        try {
            final String INSERT_USER = "INSERT INTO users(username, password, enabled, imagePath) VALUES(?,?,?,?)";
            int rowsAffected = jdbc.update(INSERT_USER, user.getUsername(), user.getPassword(), user.isEnabled(), user.getImagePath());
            if (rowsAffected != 1) {
                throw new GameNightPersistenceException("ERROR: could not insert user.");
            }
            Integer newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
            user.setUserId(newId);

            for (Roles role : user.getRoles()) {
                final String INSERT_USER_ROLE = "INSERT INTO userRoles(userId, roleId) VALUES(?,?)";
                rowsAffected = jdbc.update(INSERT_USER_ROLE, user.getUserId(), role.getRoleId());
                if (rowsAffected != 1) {
                    throw new GameNightPersistenceException("ERROR: could not insert user.");
                }
            }

        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to add user to database.");
        }
        return user;
    }

    @Override
    public void editUser(Users toEdit) throws GameNightPersistenceException {
        try {
            final String UPDATE_USER = "UPDATE users SET username = ?, password = ?,enabled = ? WHERE userId = ?";
            jdbc.update(UPDATE_USER, toEdit.getUsername(), toEdit.getPassword(), toEdit.isEnabled(), toEdit.getUserId());

            final String DELETE_USER_ROLE = "DELETE FROM userRoles WHERE userId = ?";
            jdbc.update(DELETE_USER_ROLE, toEdit.getUserId());
            for (Roles role : toEdit.getRoles()) {
                final String INSERT_USER_ROLE = "INSERT INTO userRoles(userId, roleId) VALUES(?,?)";
                jdbc.update(INSERT_USER_ROLE, toEdit.getUserId(), role.getRoleId());
            }
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to edit user in database.");
        }
    }

    @Override
    public void deleteUserById(Integer userId) throws GameNightPersistenceException {
        try {
            final String DELETE_USER_ROLE = "DELETE FROM userRoles WHERE userId = ?";
            final String DELETE_USER = "DELETE FROM users WHERE userId = ?";
            jdbc.update(DELETE_USER_ROLE, userId);
            jdbc.update(DELETE_USER, userId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to delete from database.");
        }
    }

    //characterCRUD
    @Override
    public Characters getCharacterById(Integer characterId) throws GameNightPersistenceException {
        try {
            final String SELECT_CHARACTER_BY_ID = "SELECT * FROM characters WHERE characterId = ?";
            Characters character = jdbc.queryForObject(SELECT_CHARACTER_BY_ID, new CharacterMapper(), characterId);
            return character;
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve character from database.", ex);
        }
    }

    @Override
    public List<Characters> getAllCharacters() throws GameNightPersistenceException {
        List<Characters> allCharacters = new ArrayList<>();
        try {
            final String SELECT_ALL_CHARACTERS = "SELECT * FROM characters";
            allCharacters = jdbc.query(SELECT_ALL_CHARACTERS, new CharacterMapper());
            associateUserToCharacter(allCharacters);
            return allCharacters;
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve characters from database.");
        }
    }

    @Override
    public List<Characters> getAllCharactersByUserId(Integer userId) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Characters addCharacter(Characters toAdd) throws GameNightPersistenceException {
        try {
            final String INSERT_CHARACTER = "INSERT INTO characters(userId, name, description, type, level, specialAbility, equipment,imagePath) "
                    + " VALUES(?,?,?,?,?,?,?,?)";
            jdbc.update(INSERT_CHARACTER,
                    toAdd.getUserId(),  //somehow pass in
                    toAdd.getName(),
                    toAdd.getDescription(),
                    toAdd.getType(),
                    toAdd.getLevel(),
                    toAdd.getSpecialAbility(),
                    toAdd.getEquipment(),
                    toAdd.getImagePath());

            Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            toAdd.setCharacterId(newId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to add character to database.");
        }
        return toAdd;
    }

    @Override
    public void editCharacter(Characters toEdit) throws GameNightPersistenceException {
        try {
            final String UPDATE_CHARACTER = "UPDATE characters SET userId = ?, name = ?, description = ?, type = ?, level = ?, specialAbility = ?, equipment = ?,imagePath = ? "
                    + " WHERE characterId = ?";
            jdbc.update(UPDATE_CHARACTER,
                    toEdit.getUserId(),
                    toEdit.getName(),
                    toEdit.getDescription(),
                    toEdit.getType(),
                    toEdit.getLevel(),
                    toEdit.getSpecialAbility(),
                    toEdit.getEquipment(),
                    toEdit.getImagePath());

            final String DELETE_CHARACTER_GROUPS = "DELETE FROM characterGroups WHERE characterId = ?";
            jdbc.update(DELETE_CHARACTER_GROUPS, toEdit.getCharacterId());
            insertCharacterGroup(toEdit);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to update character to database.");
        }
    }

    @Override
    public void deleteCharacterById(Integer characterId) throws GameNightPersistenceException {
        try {
            //delete from any character bridge tables first
            final String DELETE_CHARACTER_GROUP = "DELETE FROM gamenight.characterGroups "
                    + " WHERE characterId = ?";
            jdbc.update(DELETE_CHARACTER_GROUP, characterId);

            final String DELETE_CHARACTER = "DELETE FROM gamenight.characters WHERE characterId = ?";
            jdbc.update(DELETE_CHARACTER, characterId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to delete character from database.");
        }
    }


    private void associateUserToCharacter(List<Characters> allCharacters) throws GameNightPersistenceException {
        try {
            for (Characters character : allCharacters) {
                List<Users> allUsers = getUserForCharacter(character.getCharacterId());

                for (Users user : allUsers) {
                    character.setUserId(user.getUserId());
                }
            }
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to find user for character.");
        }
    }

    private List<Users> getUserForCharacter(Integer characterId) throws GameNightPersistenceException {
        try {
            final String SELECT_USER_FOR_CHARACTER = "SELECT * FROM users u "
                    + " JOIN characters c ON u.userId = c.userId WHERE characterId = ?";
            return jdbc.query(SELECT_USER_FOR_CHARACTER, new UserMapper(), characterId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve users from database.");
        }
    }

    private void insertCharacterGroup(Characters toEdit) throws GameNightPersistenceException {
        try {
            final String INSERT_CHARACTER_GROUP = "INSERT INTO "
                    + " characterGroups(characterId, groupId) VALUES(?,?)";
            if (toEdit.getGroups() != null) {
                for (Groups group : toEdit.getGroups()) {
                    jdbc.update(INSERT_CHARACTER_GROUP,
                            toEdit.getCharacterId(),
                            group.getGroupId());
                }
            }
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to make updates to database.");
        }
    }

    //role CRUD
    private Set<Roles> getRolesForUser(Integer userId) throws DataAccessException {
        final String SELECT_ROLES_FOR_USER = "SELECT r.* FROM userRoles ur "
                + "JOIN roles r ON ur.roleId = r.roleId "
                + "WHERE ur.userId = ?";
        Set<Roles> roles = new HashSet(jdbc.query(SELECT_ROLES_FOR_USER, new RoleMapper(), userId));
        return roles;
    }

    @Override
    public Roles getRoleById(Integer roleId) {
        try {
            final String SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE roleId = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), roleId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Roles getRoleByRole(String role) {
        try {
            final String SELECT_ROLE_BY_ROLE = "SELECT * FROM roles WHERE roleId = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ROLE, new RoleMapper(), role);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Roles> getAllRoles() {
        final String SELECT_ALL_ROLES = "SELECT * FROM roles";
        return jdbc.query(SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    public void deleteRole(Integer roleId) throws GameNightPersistenceException {
        final String DELETE_USER_ROLE = "DELETE FROM userRoles where roleId = ?";
        final String DELETE_ROLE = "DELETE FROM roles where roleId = ?";
        jdbc.update(DELETE_USER_ROLE, roleId);
        jdbc.update(DELETE_ROLE, roleId);
    }

    @Override
    public void editRole(Roles role) throws GameNightPersistenceException {
        final String EDIT_ROLE = "UPDATE roles SET role = ? WHERE roleId = ?";
        jdbc.update(EDIT_ROLE, role.getRole(), role.getRoleId());
    }

    @Override
    @Transactional
    public Roles addRole(Roles role) throws GameNightPersistenceException {

        final String ADD_ROLE = "INSERT INTO roles(role) VALUES(?)";
        jdbc.update(ADD_ROLE, role.getRole());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        role.setRoleId(newId);
        return role;
    }

    //game CRUD
    @Override
    public List<Games> getAllGames() throws GameNightPersistenceException {
        try {
            final String SELECT_ALL_GAMES = "SELECT * FROM gamenight.games;";
            List<Games> allGames = jdbc.query(SELECT_ALL_GAMES, new GameMapper());
            associateUsersToGame(allGames);
            return allGames;
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve games from database.", ex);
        }
    }

    private void associateUsersToGame(List<Games> allGames) throws GameNightPersistenceException {
        try {
            for (Games game : allGames) {
                List<Users> allUsers = getUsersForGame(game.getGameId());

                game.setUsers(allUsers);
            }
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to find user for game.");
        }
    }

    private List<Users> getUsersForGame(int gameId) throws GameNightPersistenceException {
        try {
            final String SELECT_USERS_FOR_GAME = "SELECT u.* FROM gameUsers gu "
                    + " JOIN users u ON gu.userId = u.userId "
                    + "WHERE gu.gameId = ?";
            return jdbc.query(SELECT_USERS_FOR_GAME, new UserMapper(), gameId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve users from database.");
        }
    }

    @Override
    public Games getGameById(Integer gameId) throws GameNightPersistenceException {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM games WHERE gameId = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to get game from database.", ex);
        }
    }

    @Override
    public Games addGame(Games toAdd) throws GameNightPersistenceException {
        try {
            final String INSERT_GAME = "INSERT INTO games(name, description, active) "
                    + " VALUES(?,?,?)";
            jdbc.update(INSERT_GAME,
                    toAdd.getName(),
                    toAdd.getDescription(),
                    toAdd.isActive());
         
                    

            Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            toAdd.setGameId(newId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to add game to database.", ex);
        }
        return toAdd;
    }

    @Override
    public void editGame(Games toEdit) throws GameNightPersistenceException {
        try {
            final String UPDATE_GAME = "UPDATE games SET name = ?, description = ? "
                    + " WHERE gameId = ?";
            jdbc.update(UPDATE_GAME,
                    toEdit.getName(),
                    toEdit.getDescription());

            final String DELETE_GAME_USERS = "DELETE FROM gameUsers WHERE gameId = ?";
            jdbc.update(DELETE_GAME_USERS, toEdit.getGameId());
            insertGameUsers(toEdit);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to update game to database.");
        }
    }

    @Override
    public void deleteGameById(Integer gameId) throws GameNightPersistenceException {
        try {
            //delete from any bridge tables first
            final String DELETE_GAME_USERS = "DELETE FROM gameUsers "
                    + " WHERE gameId = ?";
            jdbc.update(DELETE_GAME_USERS, gameId);

            final String DELETE_GAME = "DELETE FROM games WHERE gameId = ?";
            jdbc.update(DELETE_GAME, gameId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to delete game from database.", ex);
        }
    }

    private void insertGameUsers(Games toEdit) throws GameNightPersistenceException {
        try {
            final String INSERT_GAME_USERS = "INSERT INTO "
                    + " gameUsers(gameId, userId) VALUES(?,?)";
            if (toEdit.getUsers() != null) {
                for (Users user : toEdit.getUsers()) {
                    jdbc.update(INSERT_GAME_USERS,
                            toEdit.getGameId(),
                            user.getUserId());
                }
            }
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to make updates to database.");
        }
    }

    //group CRUD
    @Override
    public Groups getGroupById(Integer groupId) throws GameNightPersistenceException {
        try {
            final String SELECT_GROUP_BY_ID = "SELECT * FROM groups WHERE groupId = ?";
            return jdbc.queryForObject(SELECT_GROUP_BY_ID, new GroupMapper(), groupId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to get group from database.", ex);
        }
    }

    @Override
    public List<Groups> getAllGroups() throws GameNightPersistenceException {
        try {
            final String SELECT_ALL_GROUPS = "SELECT * FROM gamenight.groups;";
            List<Groups> allGroups = jdbc.query(SELECT_ALL_GROUPS, new GroupMapper());
            //associateUsersToGroup(allGroups);
            return allGroups;
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to retrieve groups from database.", ex);
        }
    }

    @Override
    public Groups addGroup(Groups toAdd) throws GameNightPersistenceException {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editGroup(Groups toEdit) throws GameNightPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteGroupById(Integer groupId) throws GameNightPersistenceException {
        try {
            //delete from any bridge tables first
            final String DELETE_GROUP_USERS = "DELETE FROM groupUsers "
                    + " WHERE groupId = ?";
            jdbc.update(DELETE_GROUP_USERS, groupId);
            final String DELETE_CHARACTER_GROUPS = "DELETE FROM characterGroups "
                    + " WHERE groupId = ?";
            jdbc.update(DELETE_CHARACTER_GROUPS, groupId);

            final String DELETE_GROUP = "DELETE FROM groups WHERE groupId = ?";
            jdbc.update(DELETE_GROUP, groupId);
        } catch (DataAccessException ex) {
            throw new GameNightPersistenceException("Unable to delete group from database.", ex);
        }
    }

    private void associateUsersToGroup(List<Groups> allGroups) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class UserMapper implements RowMapper<Users> {

        @Override
        public Users mapRow(ResultSet rs, int i) throws SQLException {
            Users user = new Users();
            user.setUserId(rs.getInt("userId"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            user.setImagePath(rs.getString("imagePath"));
            return user;
        }
    }

    public static final class RoleMapper implements RowMapper<Roles> {

        @Override
        public Roles mapRow(ResultSet rs, int i) throws SQLException {
            Roles role = new Roles();
            role.setRoleId(rs.getInt("roleId"));
            role.setRole(rs.getString("role"));
            return role;
        }
    }

    public static final class CharacterMapper implements RowMapper<Characters> {

        @Override
        public Characters mapRow(ResultSet rs, int i) throws SQLException {
            Characters character = new Characters();
            character.setCharacterId(rs.getInt("characterId"));
            character.setUserId(rs.getInt("userId"));
            character.setName(rs.getString("name"));
            character.setDescription(rs.getString("description"));
            character.setType(rs.getString("type"));
            character.setLevel(rs.getInt("level"));
            character.setSpecialAbility(rs.getString("specialAbility"));
            character.setEquipment(rs.getString("equipment"));
            character.setImagePath(rs.getString("imagePath"));

            return character;
        }
    }

    public static final class GameMapper implements RowMapper<Games> {

        @Override
        public Games mapRow(ResultSet rs, int i) throws SQLException {
            Games game = new Games();
            game.setGameId(rs.getInt("gameId"));
            game.setName(rs.getString("name"));
            game.setDescription(rs.getString("description"));
            game.setActive(rs.getBoolean("active"));
//            game.setImagePath(rs.getString("imagePath"));

            return game;
        }
    }

    public static final class GroupMapper implements RowMapper<Groups> {

        @Override
        public Groups mapRow(ResultSet rs, int i) throws SQLException {
            Groups group = new Groups();
            group.setGroupId(rs.getInt("groupId"));
            group.setName(rs.getString("name"));
            group.setDescription(rs.getString("description"));
            group.setAcceptingPlayers(rs.getBoolean("acceptingPlayers"));
            return group;
        }
    }

}
