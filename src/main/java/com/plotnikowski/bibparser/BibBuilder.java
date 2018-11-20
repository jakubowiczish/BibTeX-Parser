package com.plotnikowski.bibparser;

public class BibBuilder {
    private final String name;
    private final TwoNames[] needed;
    private final TwoNames[] optional;

    BibBuilder(String name, TwoNames[] needed, TwoNames[] optional) {
        this.name = name;
        this.needed = needed;
        this.optional = optional;
    }

    BibObject build(String quoteName, BibPair[] pairs) {
        for (int i = 0; i < needed.length; i++) {
            boolean fieldFound = false;
            for (int j = 0; j < pairs.length; j++) {
                if (needed[i].contains(pairs[j])) {
                    fieldFound = true;
                }
            }
            if (!fieldFound) throw new RuntimeException("Needed field: " + needed[i] + " for type " + name + " not found");
        }

        for (int i = 0; i < pairs.length; i++) {
            boolean fieldFound = false;
            for (int j = 0; j < needed.length; j++) {
                if (needed[j].contains(pairs[i])) {
                    fieldFound = true;
                }
            }
            for (int j = 0; j < optional.length; j++) {
                if (optional[j].contains(pairs[i])) {
                    fieldFound = true;
                }
            }
            if (!fieldFound) throw new RuntimeException("Wrong optional field: " + pairs[i] + " for type: " + name);
        }

        return new BibObject(name, quoteName, pairs);
    }

    public String getName() {
        return this.name;
    }


//    com.plotnikowski.bibparser.BibObject build(String quoteKey, com.plotnikowski.bibparser.BibPair[] pairs){
//
//    }


}
