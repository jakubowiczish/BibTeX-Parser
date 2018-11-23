package com.plotnikowski.bibparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Class representing BibObject
 * used to store the name of object, quoteKey and
 * name of a field with its value
 */
public class BibObject {
    private String name;
    private String quoteKey;
    private BibPair[] bibPairs;


    public BibObject(String name, String quoteKey, BibPair[] bibPair) {
        this.name = name;
        this.quoteKey = quoteKey;
        this.bibPairs = bibPair;
    }


    public int getMaxFieldLength() {
        int maxFieldLength = 0;
        for (BibPair pair : bibPairs) {
            int fieldLength = pair.getField().length();
            if (fieldLength > maxFieldLength) {
                maxFieldLength = fieldLength;
            }
        }

        return maxFieldLength;
    }

    public int getMaxValueLength() {
        int maxValueLength = 0;
        for (BibPair pair : bibPairs) {
            int valueLength = pair.getValue().length();
            if (valueLength > maxValueLength) {
                maxValueLength = valueLength;
            }

        }
        return maxValueLength;
    }

    @Override
    public String toString() {
        return "BibObject{" +
                "name='" + name + '\'' +
                ", quoteKey='" + quoteKey + '\'' +
                ", bibPairs=" + Arrays.toString(bibPairs) +
                '}';
    }

    public void handleStringMap(Map<String, String> stringMap) {
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            for (BibPair bibPair : bibPairs) {
                bibPair.handleString(entry);
            }
        }
    }


    public String getName() {
        return name;
    }

    public String getQuoteKey() {
        return quoteKey;
    }

    public BibPair[] getBibPairs() {
        return bibPairs;
    }
}
