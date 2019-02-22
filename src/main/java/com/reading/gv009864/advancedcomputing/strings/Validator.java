package com.reading.gv009864.advancedcomputing.strings;

import java.util.regex.Pattern;

public enum Validator {

    AIRPORT_NAME("[^\\s\\\\!@#$%^&*(),.?\":{}|<>]+[ /A-Z]{3,20}"),
    AIRPORT_CODE("[A-Z]{3}"),
    EPOCH_TIME("\\d{10}"),
    FLIGHT_TIME("\\d{1,4}"),
    FLIGHT_ID("[A-Z]{3}\\d{4}[A-Z]"),
    PASSENGER_ID("[A-Z]{3}\\d{4}[A-Z]{2}\\d"),
    LAT_LONG("^[-0-9]+(\\.[0-9]{3,13})?$");

    private String patternString;
    private Pattern pattern;

    private Validator(String patternString) {
        this.patternString = patternString;
        this.pattern = Pattern.compile(this.patternString);
    }

    public String getPatternString() {
        return patternString;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
