package com.reading.gv009864.advancedcomputing;

import com.reading.gv009864.advancedcomputing.strings.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class AirportData extends CsvData {
    private Logger log = LoggerFactory.getLogger(AirportData.class);
    private final Integer DATA_LENGTH = 4;

    private AirportData() {}

    public AirportData(String resourcePath) {
        this.lines = null;
        this.loadFile(resourcePath);
    }

    @Override
    protected boolean validateData(String[] data) {
        // Immediately fail validation if the array does not have 6 elements.
        if(data.length != DATA_LENGTH) {
            log.error("Data only has {} element(s), expected {}", data.length, DATA_LENGTH);
            return false;
        }
        // Simple array to iterate through, should use a list I guess but this will do.
        Pattern[] patterns = {
                Validator.AIRPORT_NAME.getPattern(),
                Validator.AIRPORT_CODE.getPattern(),
                Validator.LAT_LONG.getPattern(),
                Validator.LAT_LONG.getPattern()
        };

        return this.matcherIterator(DATA_LENGTH, data, patterns);
    }
}
