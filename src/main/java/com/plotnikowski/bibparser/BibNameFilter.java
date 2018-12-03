package com.plotnikowski.bibparser;

/**
 * Uses Strategy Design Pattern
 *
 * Class that is used to search for records that contain given names
 */
public class BibNameFilter extends BibPredicateFilter {
    public BibNameFilter(String[] names) {
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
