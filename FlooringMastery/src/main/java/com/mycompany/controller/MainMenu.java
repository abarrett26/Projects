/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.ui.ConsoleIO;

import com.mycompany.ui.UserIO;
import com.mycompany.services.FlooringService;
import com.mycompany.ui.views.AddOrderWorkflow;
import com.mycompany.ui.views.DisplayOrderWorkflow;
import com.mycompany.ui.views.EditOrderWorkflow;
import com.mycompany.ui.views.RemoveOrderWorkflow;

/**
 *
 * @author alexbarrett
 */
public class MainMenu {

     static ConsoleIO ui = new ConsoleIO();

    public void run(FlooringService service) {

        boolean isDone = false;
        int userChoice = 0;

        while (!isDone) {

            userChoice = getMenuSelection();
            switch (userChoice) {

                case 1:
                    DisplayOrderWorkflow displayFlow = new DisplayOrderWorkflow(service);
                    displayFlow.run(ui);
                    break;
                case 2:
                    AddOrderWorkflow addFlow = new AddOrderWorkflow(service);
                    addFlow.run(ui);
                    break;
                case 3:
                    EditOrderWorkflow editFlow = new EditOrderWorkflow(service);
                    editFlow.run(ui);
                    break;
                case 4:
                    RemoveOrderWorkflow removeFlow = new RemoveOrderWorkflow(service);
                    removeFlow.run(ui);
                    break;
                case 5:
                    isDone = true;
                    break;
                default:
                    ui.print("UNKOWN COMMAND");
                    break;
            }
        }
        ui.print("GoodBye!");
    }

    private int getMenuSelection() {
        ui.print("* * * * * * * * * * * * * *\n");
        ui.print("==FLOORING PROGRAM==\n");

        ui.print("1. Display Orders\n");
        ui.print("2. Add Order\n");
        ui.print("3. Edit Order\n");
        ui.print("4. Remove Order\n");
        ui.print("5. Quit\n");
        ui.print("* * * * * * * * * * * * * *\n");

        int toReturn = ui.readInt("Please select a choice between 1 and 5: ", 1, 5);

        return toReturn;
    }
}
