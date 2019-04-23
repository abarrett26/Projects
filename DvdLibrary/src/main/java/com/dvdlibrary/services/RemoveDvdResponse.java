/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template filiuuhkhke, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.services;

/**
 *
 * @author dsmelser
 */
public class RemoveDvdResponse extends Response {

    String title;
    
    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

}
