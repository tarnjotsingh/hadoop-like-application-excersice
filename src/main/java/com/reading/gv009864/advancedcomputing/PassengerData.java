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
public class PassengerData {

    Logger log = LoggerFactory.getLogger(PassengerData.class);

    private List<String[]> lines;
    private final String DELIMITER = ",";


    private PassengerData() {}

    public PassengerData(String resourcePath) {
        this.lines = null;
        this.loadFile(resourcePath);
    }

    public List<String[]> getLines() {
        return lines;
    }

    /**
     * Method for loading passenger data provided the correct classpath is provided.
     * Private method as this does not need to be called again after loading the object.
     *
     * @param resourcePath CLASSPATH to the csv data file.
     */
    private void loadFile(String resourcePath) {
        // Locate the resource in the classpath
        //URL fileUrl = this.getClass().getResource(resourcePath);
        //File f = new File(fileUrl.getFile());
        LinkedList<String[]> data = new LinkedList<>();
        String readLine;
        String[] splitLine;

        InputStream in = this.getClass().getResourceAsStream(resourcePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        /*
            Attempt to read the data from the csv file, provided the input stream was
            actually opened and store the String[] by splitting.
         */
        try {
            while((readLine = reader.readLine()) != null) {
                splitLine = readLine.split(DELIMITER);
                if(this.validateData(splitLine))
                    data.add(splitLine);
                else
                    log.error("Skipping {}", readLine);
            }
        }
        catch(IOException e) {
            log.debug(e.getLocalizedMessage());
        }
        finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(in);
        }

        //Store the data after successful read
        this.lines = data;
        log.info("File {} with {} lines has been loaded", resourcePath, lines.size());
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
    private boolean validateData(String [] data) {
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
