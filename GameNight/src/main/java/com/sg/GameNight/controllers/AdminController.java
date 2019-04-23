/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.controllers;

import com.sg.GameNight.dtos.Roles;
import com.sg.GameNight.dtos.Users;
import com.sg.GameNight.daos.GameNightDao;
import com.sg.GameNight.dtos.AddUserViewModel;
import com.sg.GameNight.dtos.EditGameViewModel;
import com.sg.GameNight.dtos.EditGroupViewModel;
import com.sg.GameNight.dtos.EditUserViewModel;
import com.sg.GameNight.dtos.Games;
import com.sg.GameNight.dtos.Groups;
import com.sg.GameNight.services.GameNightService;
import com.sg.GameNight.services.Response;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 16128
 */
@Controller
public class AdminController {

//    @Autowired
//    GameNightDao dao;
    @Autowired
    GameNightService service;

    @Autowired
    PasswordEncoder encoder;

    public static final String pictureFolder = "images";

    @GetMapping("addPictureForm")
    public String displayAddPictureForm() {
        return "addPictureForm";
    }

    @PostMapping("/addPicture")
    public String addPicture(HttpServletRequest request, Model model,
            @RequestParam("displayTitle") String displayTitle,
            @RequestParam("picture") MultipartFile pictureFile) {

        if (!pictureFile.isEmpty()) {
            try {
                String savePath = request.getSession().getServletContext().getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String filename = pictureFile.getOriginalFilename();
                pictureFile.transferTo(new File(savePath + filename));

                return "redirect:admin";
            } catch (Exception e) {
                model.addAttribute("errorMsg", "File upload failed!: " + e.getMessage());
                return "redirect:admin";
            }
        } else {
            model.addAttribute("errorMsg", "Please specify a non-empty file.");
            return "redirect:admin";
        }
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("users", service.getAllUsers().getResponseData());
        AddUserViewModel vm = new AddUserViewModel();
        vm.setAllRoles(service.getAllRoles().getResponseData());
        model.addAttribute("vm", vm);
        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(HttpServletRequest request, AddUserViewModel vm, Model model,
            @RequestParam("picture") MultipartFile pictureFile) {

        if (!pictureFile.isEmpty()) {
            try {

                String filename = pictureFile.getOriginalFilename();

                Path dirPath = Paths.get(request.getSession().getServletContext().getRealPath("/"), pictureFolder);
                Path filePath = Paths.get(request.getSession().getServletContext().getRealPath("/"), pictureFolder, filename);

                File dir = dirPath.toFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                pictureFile.transferTo(filePath.toFile());
                vm.getAddedUser().setPassword(encoder.encode(vm.getAddedUser().getPassword()));
                vm.getAddedUser().setEnabled(true);
                vm.getAddedUser().setImagePath("/" + pictureFolder + "/" + filename);
                Set<Roles> userRoles = new HashSet<>();
                for (int selectedRoleId : vm.getSelectedRoleIds()) {
                    userRoles.add(service.getRoleById(selectedRoleId).getResponseData());
                }

                vm.getAddedUser().setRoles(userRoles);

                service.addUser(vm.getAddedUser());

                return "redirect:/admin";
            } catch (Exception e) {
                model.addAttribute("errorMsg", "File upload failed!: " + e.getMessage());
                return "redirect:/admin";
            }
        } else {
            model.addAttribute("errorMsg", "Please specify a non-empty file.");
            return "redirect:/admin";
        }

    }

    @PostMapping("/deleteUser")
    public String deleteUser(Integer userId) {
        service.deleteUserById(userId);
        return "redirect:/admin";
    }

    @GetMapping("/editUser")
    public String editUserDisplay(Model model, Integer userId, Integer error) {
        Response<Users> response = service.getUserById(userId);
        Users user = response.getResponseData();
        Response<List<Roles>> roleResponse = service.getAllRoles();

        model.addAttribute("user", user);
        model.addAttribute("roles", roleResponse.getResponseData());

        if (error != null) {
            if (error == 1) {
                model.addAttribute("error", "Passwords did not match, password was not updated.");
            }
        }
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUserAction(EditUserViewModel vm, Model model) {

        Users toEdit = service.getUserById(vm.getEditedUser().getUserId()).getResponseData();

        toEdit.setUsername(vm.getEditedUser().getUsername());
        toEdit.setEnabled(vm.getEditedUser().isEnabled());

        Set<Roles> userRoles = new HashSet<>();
        for (int selectedRoleId : vm.getSelectedRoleIds()) {
            userRoles.add(service.getRoleById(selectedRoleId).getResponseData());
        }

        toEdit.setRoles(userRoles);
        service.editUser(toEdit);

        return "redirect:/admin";
    }

    @PostMapping("editPassword")
    public String editPassword(Integer userId, String password, String confirmPassword) {
        Response<Users> response = service.getUserById(userId);
        Users user = response.getResponseData();

        if (password.equals(confirmPassword)) {
            user.setPassword(encoder.encode(password));
            service.editUser(user);
            return "redirect:/admin";
        } else {
            return "redirect:/editUser?id=" + userId + "&error=1";
        }
    }

    //group CRUD
    @PostMapping("addGroup")
    public String addGroup(@ModelAttribute Groups toAdd, Model model) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Groups>> validationFailures = validate.validate(toAdd);
        model.addAttribute("errors", validationFailures);
        if (validationFailures.isEmpty()) {
            Response<Groups> response = service.addGroup(toAdd);
        } else {
            //model.addAttribute("character", toAdd);
            model.addAttribute("name", toAdd.getName());
            model.addAttribute("description", toAdd.getDescription());
            model.addAttribute("acceptingPlayers", toAdd.isAcceptingPlayers());
            //add other thinga? games? users?
        }
        Response<List<Groups>> groups = service.getAllGroups();
        model.addAttribute("groups", groups.getResponseData());
        return "groups";
    }

    @GetMapping("deleteGroup")
    public String deleteGroup(Integer groupId) {
        Response<Groups> response = service.deleteGroupById(groupId);
        return "redirect:/groups";
    }

    @GetMapping("editGroup/{groupId}")
    public String editGroup(@PathVariable Integer groupId, Model model) {
        EditGroupViewModel vm = new EditGroupViewModel();
        Response<Groups> toEdit = service.getGroupById(groupId);
        Response<List<Users>> allUsers = service.getAllUsers();
        Response<List<Games>> allGames = service.getAllGames();
        vm.setGroup(toEdit.getResponseData());
        vm.setUsers(allUsers.getResponseData());
        vm.setGames(allGames.getResponseData());

        model.addAttribute("vm", vm);

        return "editGroup";
    }

    @PostMapping("editGroup")
    public String editGroup(EditGroupViewModel vm, Model model) {

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Groups>> validationFailures = validate.validate(vm.getGroup());
        model.addAttribute("errors", validationFailures);
        if (validationFailures.isEmpty()) {
            Response<Groups> editResponse = service.editGroup(vm.getGroup());
            model.addAttribute("vm", vm);
            Response<Groups> response = service.getGroupById(editResponse.getResponseData().getGroupId());
            model.addAttribute("group", response.getResponseData());
            return "redirect:/groups";
        } else {
            return editGroup(vm.getGroup().getGroupId(), model);
        }
    }

    //Game CRUD
    @PostMapping("addGame")
    public String addGame(@ModelAttribute Games toAdd, HttpServletRequest request, Model model) {

        toAdd.setActive(request.getParameter("active") != null);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Games>> validationFailures = validate.validate(toAdd);
        model.addAttribute("errors", validationFailures);
        if (validationFailures.isEmpty()) {
            Response<Games> response = service.addGame(toAdd);
        } else {

            //model.addAttribute("character", toAdd);
            model.addAttribute("name", toAdd.getName());
            model.addAttribute("description", toAdd.getDescription());
            model.addAttribute("active", toAdd.isActive());
            //add other things? group? users?
        }
        Response<List<Games>> games = service.getAllGames();
        model.addAttribute("games", games.getResponseData());
        return "games";
    }

    @GetMapping("deleteGame")
    public String deleteGame(Integer gameId) {
        Response<Games> response = service.deleteGameById(gameId);
        return "redirect:/games";
    }

    @GetMapping("editGame/{gameId}")
    public String editGame(@PathVariable Integer gameId, Model model) {
        EditGameViewModel vm = new EditGameViewModel();
        Response<Games> toEdit = service.getGameById(gameId);
        Response<List<Users>> allUsers = service.getAllUsers();
        vm.setGame(toEdit.getResponseData());
        vm.setUsers(allUsers.getResponseData());
        model.addAttribute("vm", vm);

        return "editGame";
    }

    @PostMapping("editGame")
    public String editGame(EditGameViewModel vm, Model model) {

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Games>> validationFailures = validate.validate(vm.getGame());
        model.addAttribute("errors", validationFailures);
        if (validationFailures.isEmpty()) {
            Response<Games> existingGame = service.getGameById(vm.getGame().getGameId());
            existingGame.getResponseData().setName(vm.getGame().getName());
            existingGame.getResponseData().setDescription(vm.getGame().getDescription());
            //existingGame.getResponseData().setActive(vm.getGame().isActive());
            existingGame.getResponseData().setUsers(vm.getGame().getUsers());
            Response<Games> editResponse = service.editGame(vm.getGame());
            model.addAttribute("vm", vm);
            Response<Games> response = service.getGameById(editResponse.getResponseData().getGameId());
            model.addAttribute("game", response.getResponseData());
            Response<List<Games>> games = service.getAllGames();
            model.addAttribute("games", games.getResponseData());
            return "games";
        } else {
            return editGame(vm.getGame().getGameId(), model);
        }
    }

}
