/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.dtos;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author alexbarrett
 */
public class Users {
    private Integer userId;
    private String username;
    private String password;
    private boolean enabled;
    private List<Characters> characters;
    private List<Games> games;
    private List<Groups> groups;
    private Set<Roles> roles;
    private String imagePath;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.userId;
        hash = 17 * hash + Objects.hashCode(this.username);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + (this.enabled ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.characters);
        hash = 17 * hash + Objects.hashCode(this.games);
        hash = 17 * hash + Objects.hashCode(this.groups);
        hash = 17 * hash + Objects.hashCode(this.roles);
        return hash;
    }
    
    public boolean hasRole( int roleId ){
        boolean toReturn = false;
        for( Roles toCheck : roles ){
            toReturn = toCheck.getRoleId() == roleId;
            if( toReturn ){
                break;
            }
        }
        
        return toReturn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.characters, other.characters)) {
            return false;
        }
        if (!Objects.equals(this.games, other.games)) {
            return false;
        }
        if (!Objects.equals(this.groups, other.groups)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        return true;
    }
    

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the characters
     */
    public List<Characters> getCharacters() {
        return characters;
    }

    /**
     * @param characters the characters to set
     */
    public void setCharacters(List<Characters> characters) {
        this.characters = characters;
    }

    /**
     * @return the games
     */
    public List<Games> getGames() {
        return games;
    }

    /**
     * @param games the games to set
     */
    public void setGames(List<Games> games) {
        this.games = games;
    }

    /**
     * @return the groups
     */
    public List<Groups> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    /**
     * @return the roles
     */
    public Set<Roles> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
}
