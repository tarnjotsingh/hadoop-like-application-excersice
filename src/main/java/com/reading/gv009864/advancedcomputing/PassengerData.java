package com.reading.gv009864.advancedcomputing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for loading an managing loading and validating passenger data for a given
 * passenger csv file.
 */
public class PassengerData extends CsvData{

    private Logger log = LoggerFactory.getLogger(PassengerData.class);


    private PassengerData() {}

    public PassengerData(String resourcePath) {
        this.lines = null;
        this.loadFile(resourcePath);
    }

    public List<String[]> getLines() {
        return lines;
    }


    /**
     * Based on the passenger data, the arrays should have a size of 6.
     * This should be the first check to determine if the data is valid.
     * Could make this run as the lines are being read just to streamline
     * the process. That way lines can be skipped if the data read is valid or not.
     *
     * @param data String array to validate.
     * @return boolean value indicating if validation was successful or not.
    */
    @Override
    protected boolean validateData(String [] data) {
        // Immediately fail validation if the array does not have 6 elements.
        if(data.length != 6) {
            return false;
        }
        /*
            Do some sort of regex check against each element in the array to see
            if the data is valid or not.
            https://regexr.com/ using this site to figure out what the
            correct regex is.
         */
        Pattern passengerId = Pattern.compile("[A-Z]{3}\\d{4}[A-Z]{2}\\d"); // XXXnnnnXXn
        Pattern flightId =    Pattern.compile("[A-Z]{3}\\d{4}[A-Z]");       // XXXnnnnX
        Pattern airportCode = Pattern.compile("[A-Z]{3}");                  // XXX third and fourth elements
        Pattern epochTime =   Pattern.compile("\\d{10}");                   // n[10]
        Pattern flightTime =  Pattern.compile("\\d{1,4}");                  // n[1..4]

        // Simple array to iterate through, should use a list I guess but this will do.
        Pattern[] patterns = {passengerId, flightId, airportCode, airportCode, epochTime, flightTime};
        Matcher matcher;

        //Iterate through each of the elements in the array.
        //Elements should be in the correct order, otherwise this will also fail the validation.
        for(int i = 0; i < 6; i++) {
            matcher = patterns[i].matcher(data[i]);
            if(!matcher.matches()) {
                log.error("Validation error. Value: {} at column {} failed validation.", data[i], i + 1);
                return false;
            }
        }

        return true;
    }
}
