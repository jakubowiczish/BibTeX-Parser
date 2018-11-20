package com.plotnikowski.bibparser;

import java.util.Arrays;

public class BibObject {
    private String name;
    private String quoteKey;
    private BibPair[] bibPair;


    public BibObject(String name, String quoteKey, BibPair[] bibPair) {
        this.name = name;
        this.quoteKey = quoteKey;
        this.bibPair = bibPair;
    }

    @Override
    public String toString() {
        return "BibObject{" +
                "name='" + name + '\'' + '\n' +
                ", quoteKey='" + quoteKey + '\'' + '\n' +
                ", bibPair=" + Arrays.toString(bibPair) + '\n' +
                '}';
    }
}
