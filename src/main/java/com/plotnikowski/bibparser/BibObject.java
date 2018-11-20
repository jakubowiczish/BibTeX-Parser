package com.plotnikowski.bibparser;

public class BibObject {
    private String name;
    private String quoteKey;
    private BibPair[] bibPair;


    public BibObject(String name, String quoteKey, BibPair[] bibPair) {
        this.name = name;
        this.quoteKey = quoteKey;
        this.bibPair = bibPair;
    }
}
