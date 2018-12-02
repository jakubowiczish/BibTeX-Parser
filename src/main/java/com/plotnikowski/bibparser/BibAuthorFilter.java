package com.plotnikowski.bibparser;

import java.util.Arrays;

/**
 * Class used to search for publications given name of publication or author (authors)
 */
public class BibAuthorFilter extends BibPredicateFilter {

    BibAuthorFilter(String[] authors) {
        super(object -> {
            BibPair[] pairs = object.getBibPairs();

            for (BibPair pair : pairs) {
                String field = pair.getField();
                String value = pair.getValue();

                if (field.equals("author") || field.equals("editor")) {
                    String[] authorsInValue = BibUtils.splitAuthors(value);
                    boolean contains = false;
                    for (String author : authorsInValue) {
                        if (author.contains(" ")) {
                            String[] names = author.split(" ");

                            for (String name : names) {
                                if (Arrays.asList(authors).contains(name)) {
                                    contains = true;
                                }
                            }

                        }
                    }
                    if (contains) {
                        return true;
                    }
                }
            }
            return false;
        });
    }

}
