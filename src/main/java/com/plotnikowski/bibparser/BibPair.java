package com.plotnikowski.bibparser;

import java.util.Map;

/**
 * Class used to create pair out of two Strings
 */
public class BibPair {
    private String field;
    private String value;

    BibPair(String field, String value) {
        this.field = field;
        this.value = value;
    }


    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return field + " = " + value + '\n';
    }

    public void handleString(Map.Entry<String, String> entry) {
        if (getValue().contains(entry.getKey())) {
            value = getValue().replaceAll(entry.getKey(), entry.getValue());
        }
    }
}
