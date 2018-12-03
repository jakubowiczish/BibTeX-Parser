package com.plotnikowski.bibparser;

/**
 * Uses Strategy Design Pattern
 *
 * Class that is used to filter document
 */
public class BibFilter {
    public static BibDocument filter(BibDocument document, IFilter... filters) {
        for (IFilter filter : filters) {
            document = filter.filter(document);
        }
        return document;
    }
}

