package com.plotnikowski.bibparser;

/**
 * Class used to build a BibObject after verification of needed and optional fields
 */
public class BibBuilder {
    private final String name;
    private final BibFieldNames[] needed;
    private final BibFieldNames[] optional;

    BibBuilder(String name, BibFieldNames[] needed, BibFieldNames[] optional) {
        this.name = name;
        this.needed = needed;
        this.optional = optional;
    }

    /**
     * Function that builds an object after checking whether arguments are valid
     *
     * @param quoteName quoteName needed to create an Object
     * @param pairs Array made of BibPair pairs
     * @return new Object if it is possible
     */
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
}
