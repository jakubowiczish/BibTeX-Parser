package com.plotnikowski.bibparser;

public class BibNameFilter extends BibPredicateFilter {
    BibNameFilter(String name) {
        super(object -> object.getName().equals(name));
    }
}
