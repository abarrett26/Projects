/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.services;

import com.sg.GameNight.daos.GameNightPersistenceException;
import com.sg.GameNight.daos.InMemoryDao;
import com.sg.GameNight.dtos.Characters;
import com.sg.GameNight.dtos.Roles;
import com.sg.GameNight.dtos.Users;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 16128
 */
public class GameNightServiceTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getAllUsers method, of class GameNightService.
     */
    @Test
    public void testGetAllUsers() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<List<Users>> response = service.getAllUsers();
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals(3, response.getResponseData().size());

    }

    //\\**
     // Test of getUserById method, of class GameNightService.
    // */
    @Test
    public void testGetUserById() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<Users> response = service.getUserById(1);
        Assert.assertTrue(true);
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals("alexander", response.getResponseData().getUsername());

    }

    @Test
    public void testGetUserByUsername() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<Users> response = service.getUserByUsername("thomas");
        Assert.assertTrue(true);
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals("thomas", response.getResponseData().getUsername());
    }

    @Test
    public void testAddUser() throws GameNightPersistenceException {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);

        Users user1 = new Users();
        user1.setUsername("Bob");
        user1.setPassword("Bob1!");
        Response<Users> responseAdd = service.addUser(user1);
        Integer four = 4;
        Users addedUser = testDao.getUserByUsername("Bob");

        Assert.assertEquals("Bob", addedUser.getUsername());
        Assert.assertEquals("Bob1!", addedUser.getPassword());
        Assert.assertEquals(four, addedUser.getUserId());

    }

    @Test
    public void testEditUser() throws GameNightPersistenceException {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        
        List<Users> allUsers = testDao.getAllUsers();
        Users toEdit = testDao.getUserById(3);
        toEdit.setUsername("Bob");
        toEdit.setPassword("Changed");
        testDao.editUser(toEdit);
        Integer three = 3;
        Assert.assertEquals("Bob", toEdit.getUsername());
        Assert.assertEquals("Changed", toEdit.getPassword());
        Assert.assertEquals(three, toEdit.getUserId());
        
    }

    /**
     * Test of deleteUserById method, of class GameNightService.
     */
    @Test
    public void testDeleteUserById() {
        
    }

    /**
     * Test of getAllCharacters method, of class GameNightService.
     */
    @Test
    public void testGetAllCharacters() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<List<Characters>> response = service.getAllCharacters();
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals(2, response.getResponseData().size());
    }

    /**
     * Test of getCharacterById method, of class GameNightService.
     */
    @Test
    public void testGetCharacterById() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<Characters> response = service.getCharacterById(2);
        Assert.assertTrue(true);
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals("Erin", response.getResponseData().getName());
     
    }

    /**
     * Test of addCharacter method, of class GameNightService.
     */
    @Test
    public void testAddCharacter() throws GameNightPersistenceException {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        List<Characters> listAllCharacters = testDao.getAllCharacters();
        Characters character1 = new Characters();
        character1.setCharacterId(5);
        character1.setName("Master");
        character1.setDescription("Shoots guns");
        character1.setType("Shooter");
        character1.setLevel(50);
        character1.setSpecialAbility("Jumps");
        character1.setEquipment("Fly");
        listAllCharacters.add(character1);
       
        Characters addedCharacter = testDao.getCharacterById(5);

        Assert.assertEquals("Master", addedCharacter.getName());
        Assert.assertEquals("Shooter", addedCharacter.getType());
        Assert.assertEquals("Jumps", addedCharacter.getSpecialAbility());
      
    }

    /**
     * Test of editCharacter method, of class GameNightService.
     */
    @Test
    public void testEditCharacter() throws GameNightPersistenceException {
//        InMemoryDao testDao = new InMemoryDao();
//        GameNightService service = new GameNightService(testDao);
//        
//        List<Characters> allCharacters = testDao.getAllCharacters();
//        Characters toEdit = testDao.getCharacterById(1);
//        toEdit.getName("Troop");
//        toEdit.getDescription();
//        toEdit.getLevel();
//        toEdit.getSpecialAbility();
//        toEdit.getEquipment();
//        testDao.editCharacter(toEdit);
//        Integer three = 3;
//        Assert.assertEquals("Bob", toEdit.getUsername());
//        Assert.assertEquals("Changed", toEdit.getPassword());
//        Assert.assertEquals(three, toEdit.getUserId());
        
        
    }

    /**
     * Test of deleteCharacterById method, of class GameNightService.
     */
    @Test
    public void testDeleteCharacterById() {
        
    }

    /**
     * Test of getRoleByRole method, of class GameNightService.
     */
    @Test
    public void testGetRoleByRole() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<Roles> response = service.getRoleByRole("ROLE_USER");
        Assert.assertTrue(true);
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals("ROLE_USER", response.getResponseData().getRole());
    }

    /**
     * Test of getAllRoles method, of class GameNightService.
     */
    @Test
    public void testGetAllRoles() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<List<Roles>> response = service.getAllRoles();
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals(2, response.getResponseData().size());
    }

    /**
     * Test of getRoleById method, of class GameNightService.
     */
    @Test
    public void testGetRoleById() {
        InMemoryDao testDao = new InMemoryDao();
        GameNightService service = new GameNightService(testDao);
        Response<Roles> response = service.getRoleById(1);
        Integer one = 1;
        Assert.assertTrue(true);
        Assert.assertNotNull(response.getResponseData());
        Assert.assertEquals(one, response.getResponseData().getRoleId());
    }

}
