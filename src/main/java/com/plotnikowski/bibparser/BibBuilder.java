package com.plotnikowski.bibparser;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class used to build a BibObject after verification of needed and optional fields
 */
public class BibBuilder {
    private final String name;
    private final BibFieldNames[] needed;
    private final BibFieldNames[] optional;

    public BibBuilder(String name, BibFieldNames[] needed, BibFieldNames[] optional) {
        this.name = name;
        this.needed = needed;
        this.optional = optional;
    }

    /**
     * Function that builds an object after checking whether arguments are valid
     *
     * @param quoteName quoteName needed to create an Object
     * @param pairs     Array made of BibPair pairs
     * @param line      line in which is the name of currently processed record
     * @return new Object if it is possible (object contains all needed fields and not too many optional fields)
     */
    public BibObject build(String quoteName, BibPair[] pairs, int line) {
        for (int i = 0; i < needed.length; i++) {
            boolean fieldFound = false;
            for (int j = 0; j < pairs.length; j++) {
                if (needed[i].contains(pairs[j])) {
                    fieldFound = true;
                }
            }
            if (!fieldFound) {
                throw new RuntimeException("Needed field " + needed[i] + " for type " + name + " at line " + line + " not found");
            }
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
            if (!fieldFound) {
                throw new RuntimeException("Wrong optional field " + pairs[i].getField() + " for type " + name + " at line " + line);
            }
        }

        return new BibObject(name, quoteName, pairs);
    }

    /**
     * @return name of the record
     */
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibBuilder builder = (BibBuilder) o;
        return Objects.equals(name, builder.name) &&
                Arrays.equals(needed, builder.needed) &&
                Arrays.equals(optional, builder.optional);
    }


}
