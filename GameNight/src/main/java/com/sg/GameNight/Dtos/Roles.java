/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.dtos;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author alexbarrett
 */
public class Roles {

    private Integer roleId;
    private String role;
    private List<Users> users;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.roleId;
        hash = 23 * hash + Objects.hashCode(this.role);
        hash = 23 * hash + Objects.hashCode(this.users);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Roles other = (Roles) obj;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the users
     */
    public List<Users> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
