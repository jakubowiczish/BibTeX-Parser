package com.plotnikowski.bibparser;

public class BibBuilder {
    private final String name;
    private final String[] needed;
    private final String[] optional;

    BibBuilder(String name, String[] needed, String[] optional) {
        this.name = name;
        this.needed = needed;
        this.optional = optional;
    }

    BibObject build(String quoteName, BibPair[] pairs) {
        for (int i = 0; i < needed.length; i++) {
            boolean fieldFound = false;
            for (int j = 0; j < pairs.length; j++) {
                if (pairs[j].getField().equals(needed[i])) {
                    fieldFound = true;
                }
            }
            if (!fieldFound) throw new RuntimeException("Needed field not found");
        }

        for (int i = 0; i < pairs.length; i++) {
            boolean fieldFound = false;
            for (int j = 0; j < needed.length; j++) {
                if (pairs[i].getField().equals(needed[j])) {
                    fieldFound = true;
                }
            }
            for (int j = 0; j < optional.length; j++) {
                if (pairs[i].getField().equals(optional[j])) {
                    fieldFound = true;
                }
            }
            if (!fieldFound) throw new RuntimeException("Too many optional fields");
        }

        return new BibObject(name, quoteName, pairs);
    }


//    com.plotnikowski.bibparser.BibObject build(String quoteKey, com.plotnikowski.bibparser.BibPair[] pairs){
//
//    }


}
