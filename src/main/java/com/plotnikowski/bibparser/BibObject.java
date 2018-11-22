package com.plotnikowski.bibparser;

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

    @Override
    public String toString() {
        return "BibObject{" +
                "name='" + name + '\'' + '\n' +
                ", quoteKey='" + quoteKey + '\'' + '\n' +
                ", bibPair=" + Arrays.toString(bibPairs) + '\n' +
                '}';
    }

    public void handleStringMap(Map<String, String> stringMap) {
        for(Map.Entry<String, String> entry : stringMap.entrySet()) {
            for (BibPair bibPair : bibPairs) {
                bibPair.handleString(entry);
            }
        }
    }
}
