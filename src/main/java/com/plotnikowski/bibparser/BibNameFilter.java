package com.plotnikowski.bibparser;

public class BibNameFilter extends BibPredicateFilter {
    BibNameFilter(String[] names) {
        super(object -> {

            for (String name : names) {
                if (object.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        });
    }
}
