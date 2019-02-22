package com.reading.gv009864.advancedcomputing;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Base class for data readers.
 * This base class will contain a loadFile implementation that can be used
 * by any extended class. Only difference will be the validator which can be
 * overridden if desired.
 */
public class CsvData {
    private Logger log = LoggerFactory.getLogger(CsvData.class);


    protected List<String[]> lines;
    protected final String DELIMITER = ",";

    public List<String[]> getLines() {
        return lines;
    }

    /**
     * Method for loading passenger data provided the correct classpath is provided.
     * Private method as this does not need to be called again after loading the object.
     *
     * This method can be used as is, though an implementation of validateData will be
     * required in order to validate different data.
     *
     * @param resourcePath CLASSPATH to the csv data file.
     */
    protected void loadFile(String resourcePath) {
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
            log.info("Reading data from {}", resourcePath);
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
     * Default method for validation.
     *
     * With no class-specific validator in place, it only makes sense to
     * just return true in all cases. i.e. everything is valid unless specified
     *
     * @param data String array to validate.
     * @return boolean value indicating if validation was successful or not.
     */
    protected boolean validateData(String [] data) {
        return true;
    }

    protected boolean matcherIterator(Integer dataLengh, String[] data, Pattern[] patterns) {
        Matcher matcher;

        //Iterate through each of the elements in the array.
        //Elements should be in the correct order, otherwise this will also fail the validation.
        for(int i = 0; i < dataLengh; i++) {
            matcher = patterns[i].matcher(data[i]);
            if(!matcher.matches()) {
                log.error("Validation error. Value: {} at column {} failed validation.", data[i], i + 1);
                return false;
            }
        }

        return true;
    }
}
