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
import java.io.Console;
import java.util.List;

/**
 *
 * @author dsmelser
 */
public class SearchTitleWorkflow {

    DvdService service;

    public SearchTitleWorkflow(DvdService service) {
        this.service = service;
    }

    public void run(ConsoleIO ui) {
        ListDvdResponse response = service.listDvds();

        if (response.getSuccess()) {
            List<Dvd> allMovies = response.getAllMovies();

            Dvd toSearch = getDvdbyTitle(ui, response.getAllMovies());

        }
    }

    private Dvd getDvdbyTitle(ConsoleIO ui, List<Dvd> allMovies) {
        Dvd toReturn = null;
        boolean isMatch = false;

        while (!isMatch) {
            String title = ui.readString("Please display title for details: ");
            for (Dvd movie : allMovies) {
                if (title.equals(movie.getTitle())) {
                    toReturn = movie;
                    isMatch = true;
                } break;
                

            }

        }  return toReturn;
      
    }

}
