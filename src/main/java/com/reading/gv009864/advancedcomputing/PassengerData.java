package com.reading.gv009864.advancedcomputing;

import com.reading.gv009864.advancedcomputing.strings.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Class for loading an managing loading and validating passenger data for a given
 * passenger csv file.
 */
public class PassengerData extends CsvData{

    private Logger log = LoggerFactory.getLogger(PassengerData.class);
    private final Integer DATA_LENGTH = 6;

    private PassengerData() {}

    public PassengerData(String resourcePath) {
        this.lines = null;
        this.loadFile(resourcePath);
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
        if(data.length != DATA_LENGTH) {
            log.error("Data only has {} element(s), expected {}", data.length, DATA_LENGTH);
            return false;
        }
        /*
            Do some sort of regex check against each element in the array to see
            if the data is valid or not.
            https://regexr.com/ using this site to figure out what the
            correct regex is.
         */
        Pattern[] patterns = {
                Validator.PASSENGER_ID.getPattern(),
                Validator.FLIGHT_ID.getPattern(),
                Validator.AIRPORT_CODE.getPattern(),
                Validator.AIRPORT_CODE.getPattern(),
                Validator.EPOCH_TIME.getPattern(),
                Validator.FLIGHT_TIME.getPattern()
        };

        return this.matcherIterator(DATA_LENGTH, data, patterns);
    }
}
