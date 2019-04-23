/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.daos;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.GameNight.dtos.Users;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author alexbarrett
 */
public class GameNightDbDaoTest {

    MysqlDataSource ds = new MysqlDataSource();
    JdbcTemplate jdbc;
    GameNightDbDao dao;

    public GameNightDbDaoTest() throws SQLException {
        ds.setServerName("localhost");
        ds.setDatabaseName("GameNightTest");
        ds.setUser("EATSquad");
        ds.setPassword("Sendit1!");
        ds.setServerTimezone("America/Chicago");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);

        this.jdbc = new JdbcTemplate(ds);
        this.dao = new GameNightDbDao(jdbc);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //delete from each table.
        //delete frmo bottom up.
        jdbc.update("DELETE FROM users");
        jdbc.update("DELETE FROM characters");
        jdbc.update("DELETE FROM games");
        jdbc.update("DELETE FROM `groups`");
        jdbc.update("DELETE FROM `roles`");
        
        jdbc.update("INSERT into Characters(characterId, userId, name, description, type, level, specialability, equipment, imagepath) "
                + "VALUES ('1', '1', 'Bob', 'The Builder','Archer','10','jumps','Blade','/images/tiamat.jpg\');");
        jdbc.update("INSERT INTO Games(gameId, name, description, active) VALUES ('1', 'Best Game', 'The best Game', 'true');");
        jdbc.update("INSERT INTO Roles(roleId, role) VALUES ('1', 'ROLE_ADMIN');");
        jdbc.update("INSERT INTO Users(userId, username, `password`, enabled, imagePath) VALUES ('1','FistBud','suter12!', 'true', '/images/tiamat.jpg\');");
                
    }
}
//    @After
//    public void tearDown() {
//    }

//    /**
//     * Test of getUserById method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetUserById() throws Exception {
//        Users user = dao.getUserById(1);
//        Assert.assertEquals("FistBud", user.getUsername());
//        
//        
//    }
//
//    /**
//     * Test of getUserByUsername method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetUserByUsername() throws Exception {
//    }
//
//    /**
//     * Test of getAllUsers method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetAllUsers() throws Exception {
//    }
//
//    /**
//     * Test of addUser method, of class GameNightDbDao.
//     */
//    @Test
//    public void testAddUser() throws Exception {
//    }
//
//    /**
//     * Test of editUser method, of class GameNightDbDao.
//     */
//    @Test
//    public void testEditUser() throws Exception {
//    }
//
//    /**
//     * Test of deleteUserById method, of class GameNightDbDao.
//     */
//    @Test
//    public void testDeleteUserById() throws Exception {
//    }
//
//    /**
//     * Test of getCharacterById method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetCharacterById() throws Exception {
//    }
//
//    /**
//     * Test of getAllCharacters method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetAllCharacters() throws Exception {
//    }
//
//    /**
//     * Test of getAllCharactersByUserId method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetAllCharactersByUserId() throws Exception {
//    }
//
//    /**
//     * Test of addCharacter method, of class GameNightDbDao.
//     */
//    @Test
//    public void testAddCharacter() throws Exception {
//    }
//
//    /**
//     * Test of editCharacter method, of class GameNightDbDao.
//     */
//    @Test
//    public void testEditCharacter() throws Exception {
//    }
//
//    /**
//     * Test of deleteCharacterById method, of class GameNightDbDao.
//     */
//    @Test
//    public void testDeleteCharacterById() throws Exception {
//    }
//
//    /**
//     * Test of getRoleById method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetRoleById() {
//    }
//
//    /**
//     * Test of getRoleByRole method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetRoleByRole() {
//    }
//
//    /**
//     * Test of getAllRoles method, of class GameNightDbDao.
//     */
//    @Test
//    public void testGetAllRoles() {
//    }
//
//    /**
//     * Test of deleteRole method, of class GameNightDbDao.
//     */
//    @Test
//    public void testDeleteRole() throws Exception {
//    }
//
//    /**
//     * Test of editRole method, of class GameNightDbDao.
//     */
//    @Test
//    public void testEditRole() throws Exception {
//    }
//
//    /**
//     * Test of addRole method, of class GameNightDbDao.
//     */
//    @Test
//    public void testAddRole() throws Exception {
//    }
//
//}
