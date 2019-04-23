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
public class EditUserViewModel {

    private List<Roles> allRoles;

    private Users editedUser;
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
     * @return the editedUser
     */
    public Users getEditedUser() {
        return editedUser;
    }

    /**
     * @param editedUser the editedUser to set
     */
    public void setEditedUser(Users editedUser) {
        this.editedUser = editedUser;
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
