/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Product;
import com.mycompany.dto.Taxes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexbarrett
 */
public class TaxFileDao implements TaxDao {

    String path;

    public TaxFileDao(String path) {
        this.path = path;
    }

    @Override
    public List<Taxes> getAllStateTax() throws TaxPersistenceException {

        List<Taxes> toReturn = new ArrayList();
        try {
            File taxFile = new File(path);

            Scanner reader = new Scanner(new BufferedReader(new FileReader(taxFile)));
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] cells = line.split(",");
                Taxes toAdd = new Taxes();
                toAdd.setStateAbbreviation(cells[0]);
                toAdd.setTaxRate(new BigDecimal(cells[1]));
                toReturn.add(toAdd);
            }

            return toReturn;

        } catch (FileNotFoundException ex) {
            throw new TaxPersistenceException("Error could not find tax file", ex);
        }
    }

    @Override
    public Taxes getTaxbyState(String state) throws TaxPersistenceException {
        Taxes toReturn = null;

        List<Taxes> allTaxes = getAllStateTax();

        for (Taxes toCheck : allTaxes) {
            if (toCheck.getStateAbbreviation().equalsIgnoreCase(state)) {
                toReturn = toCheck;
                break;
            }
        }

        return toReturn;
    }

}
