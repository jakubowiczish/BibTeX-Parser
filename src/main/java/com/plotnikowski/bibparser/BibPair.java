package com.plotnikowski.bibparser;

import java.util.Map;
import java.util.Objects;

/**
 * Class used to create pair out of two Strings
 */
public class BibPair {
    private String field;
    private String value;

    public BibPair(String field, String value) {
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

    /**
     *
     * @param entry
     */
    public void handleString(Map.Entry<String, String> entry) {
        if (getValue().contains(entry.getKey())) {
            value = getValue().replaceAll(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibPair bibPair = (BibPair) o;
        return Objects.equals(field, bibPair.field) &&
                Objects.equals(value, bibPair.value);
    }
}
