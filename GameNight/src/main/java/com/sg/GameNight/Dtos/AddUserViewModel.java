/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.dtos;

import java.util.List;

/**
 *
 * @author 16128
 */
public class AddUserViewModel {
    private List<Roles> allRoles;
    
    private Users addedUser;
    private int[] selectedRoleIds;

    /**
     * @return the allRoles
     */
    public List<Roles> getAllRoles() {
        return allRoles;
    }

    /**
     * @param allRoles the allRoles to set
     */
    public void setAllRoles(List<Roles> allRoles) {
        this.allRoles = allRoles;
    }

    /**
     * @return the addedUser
     */
    public Users getAddedUser() {
        return addedUser;
    }

    /**
     * @param addedUser the addedUser to set
     */
    public void setAddedUser(Users addedUser) {
        this.addedUser = addedUser;
    }

    /**
     * @return the selectedRoleIds
     */
    public int[] getSelectedRoleIds() {
        return selectedRoleIds;
    }

    /**
     * @param selectedRoleIds the selectedRoleIds to set
     */
    public void setSelectedRoleIds(int[] selectedRoleIds) {
        this.selectedRoleIds = selectedRoleIds;
    }
}
