package com.plotnikowski.bibparser;

/**
 * Class that is used to search for
 */
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
