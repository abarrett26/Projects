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
public class ListDvdWorkflow {

    DvdService service;

    public ListDvdWorkflow(DvdService service) {
        this.service = service;

    }

    public void run(ConsoleIO ui) {

        ListDvdResponse response = service.listDvds();
        

        if (response.getSuccess()) {
            List<Dvd> allMovies = response.getAllMovies();
            for (Dvd movie : allMovies) {

                ui.print(movie.getTitle() + "\n");
                ui.print("ID: " + movie.getId() + "\n");
                ui.print("Year: " + movie.getReleaseYear() + "\n");
                ui.print("MPAA: " + movie.getRating() + "\n");
                ui.print("Director: " + movie.getDirector() + "\n");
                ui.print("Studio: " + movie.getStudio() + "\n");
                ui.print("\n" + movie.getNote() + "\n");
            }
        } else {
                String failureMessage = response.getMessage();
                ui.print(failureMessage);
        }

    }

}
