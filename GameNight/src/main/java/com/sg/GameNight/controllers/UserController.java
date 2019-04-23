/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.controllers;

import com.sg.GameNight.dtos.Characters;
import com.sg.GameNight.dtos.Users;
import com.sg.GameNight.dtos.EditCharacterViewModel;
import com.sg.GameNight.dtos.EditGameViewModel;
import com.sg.GameNight.dtos.EditGroupViewModel;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.User;
/**
 *
 * @author 16128
 */
@Controller
public class UserController {

    @GetMapping("/user")
    public String showUserPage() {
        return "user";
    }

    @Autowired
    GameNightService service;

    public static final String pictureFolder = "images";

    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Users>> violations = new HashSet();

    //Character CRUD
    @GetMapping("characters")
    public String displayCharacters(Model model) {
        Response<List<Characters>> characters = service.getAllCharacters();
        model.addAttribute("characters", characters.getResponseData());
        return "characters";
    }

    @GetMapping("displayCharacter")
    public String displayCharacterById(Integer characterId, Model model) {
        Response<Characters> response = service.getCharacterById(characterId);
        model.addAttribute("character", response.getResponseData());
        return "displayCharacter";
    }

    @PostMapping("addCharacter")
    public String addCharacter(HttpServletRequest request, @ModelAttribute Characters toAdd, Model model, @RequestParam("picture") MultipartFile pictureFile) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Characters>> validationFailures = validate.validate(toAdd);
        model.addAttribute("errors", validationFailures);
        if (validationFailures.isEmpty()) {
            try {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User currentUser = (User)auth.getPrincipal();
                Response<Users> userResponse = service.getUserByUsername(currentUser.getUsername());
                toAdd.setUserId(userResponse.getResponseData().getUserId());
                String filename = pictureFile.getOriginalFilename();

                Path dirPath = Paths.get(request.getSession().getServletContext().getRealPath("/"), pictureFolder);
                Path filePath = Paths.get(request.getSession().getServletContext().getRealPath("/"), pictureFolder, filename);
                File dir = dirPath.toFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                pictureFile.transferTo(filePath.toFile());
                String imageUrl = "/images/" + filename;
                toAdd.setImagePath(imageUrl);
                Response<Characters> response = service.addCharacter(toAdd);
                model.addAttribute("addCharacter", response.getResponseData());
            } catch (Exception e) {
                model.addAttribute("errorMsg", "File upload failed!: " + e.getMessage());
                return "redirect:/user";
            }
        } else {
            model.addAttribute("character", toAdd);
            model.addAttribute("userId", toAdd.getUserId());
            model.addAttribute("name", toAdd.getName());
            model.addAttribute("description", toAdd.getDescription());
            model.addAttribute("type", toAdd.getType());
            model.addAttribute("level", toAdd.getLevel());
            model.addAttribute("specialAbility", toAdd.getSpecialAbility());
            model.addAttribute("equipment", toAdd.getEquipment());
            model.addAttribute("imagePath", toAdd.getImagePath());
        }
        return displayCharacters(model);
    }

    @GetMapping("deleteCharacter")
    public String deleteCharacter(Integer characterId) {
        Response<Characters> response = service.deleteCharacterById(characterId);
        return "redirect:/characters";
    }

    @GetMapping("editCharacter/{characterId}")
    public String editCharacter(@PathVariable Integer characterId, Model model) {
        EditCharacterViewModel vm = new EditCharacterViewModel();
        Response<Characters> toEdit = service.getCharacterById(characterId);
        Response<List<Users>> allUsers = service.getAllUsers();
        vm.setCharacter(toEdit.getResponseData());
        vm.setAllUsers(allUsers.getResponseData());

        model.addAttribute("vm", vm);

        return "editCharacter";
    }

    @PostMapping("editCharacter")
    public String editCharacter(EditCharacterViewModel vm, Model model) {

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Characters>> validationFailures = validate.validate(vm.getCharacter());
        model.addAttribute("errors", validationFailures);
        if (validationFailures.isEmpty()) {
            Response<Characters> editResponse = service.editCharacter(vm.getCharacter());
            model.addAttribute("vm", vm);
            Response<Characters> response = service.getCharacterById(editResponse.getResponseData().getCharacterId());
            model.addAttribute("organization", response.getResponseData());
            return "redirect:/organizations";
        } else {
            return editCharacter(vm.getCharacter().getCharacterId(), model);
        }
    }

    //group CRUD
    @GetMapping("groups")
    public String displayGroups(Model model
    ) {
        Response<List<Groups>> groups = service.getAllGroups();
        model.addAttribute("groups", groups.getResponseData());
        return "groups";
    }

    @GetMapping("displayGroup")
    public String displayGroupById(Integer groupId, Model model
    ) {
        Response<Groups> response = service.getGroupById(groupId);
        model.addAttribute("group", response.getResponseData());
        return "displayGroup";
    }

    //Game CRUD
    @GetMapping("games")
    public String displayGames(Model model) {
        Response<List<Games>> games = service.getAllGames();
        model.addAttribute("games", games.getResponseData());
        return "games";
    }

    @GetMapping("displayGame")
    public String displayGameById(Integer gameId, Model model) {
        Response<Games> response = service.getGameById(gameId);
        model.addAttribute("game", response.getResponseData());
        return "displayGame";
    }

}
