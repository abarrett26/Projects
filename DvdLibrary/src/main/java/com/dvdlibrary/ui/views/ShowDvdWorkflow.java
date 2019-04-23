/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.ui.views;

import com.dvdlibrary.dtos.Dvd;
import com.dvdlibrary.services.DvdService;
import com.dvdlibrary.services.ListDvdResponse;
import com.dvdlibrary.ui.ConsoleIO;
import java.util.List;

/**
 *
 * @author dsmelser
 */
public class ShowDvdWorkflow {

     DvdService service;

    public ShowDvdWorkflow(DvdService service) {
        this.service = service;
    }
    public void run(ConsoleIO ui) {
        ListDvdResponse response = service.listDvds();
        
        if (response.getSuccess()) {
            List<Dvd> allMovies = response.getAllMovies();

            Dvd id = getDvdbyId(ui, response.getAllMovies());

        }
    }

    private Dvd getDvdbyId(ConsoleIO ui, List<Dvd> allMovies, Dvd id) {
        Dvd toReturn = null;
        boolean isMatch = false;

        while (!isMatch) {
            String disc = ui.readString("Please display ID for details: ");
            for (Dvd movie : allMovies) {
                if (disc.equals(movie.getId())) {
                    toReturn = movie;
                    isMatch = true;
                } break;
                

            }

        }  return toReturn;
      
    }

    
}
