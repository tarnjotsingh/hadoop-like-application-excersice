package com.reading.gv009864.advancedcomputing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirportData extends CsvData {
    private Logger log = LoggerFactory.getLogger(AirportData.class);

    private AirportData() {}

    public AirportData(String resourcePath) {
        this.lines = null;
        this.loadFile(resourcePath);
    }

    @Override
    protected void loadFile(String resourcePath) {

    }

    @Override
    protected boolean validateData(String[] data) {

        return true;
    }
}
