package com.plotnikowski.bibparser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used to search for publications given name of publication or author (authors)
 */
public class BibAuthorFilter extends BibPredicateFilter {

    BibAuthorFilter(String[] authors) {
        super(new IPredicate() {
            @Override
            public boolean test(BibObject object) {
                BibPair[] pairs = object.getBibPairs();

                for (BibPair pair : pairs) {
                    String field = pair.getField();
                    String value = pair.getValue();

                    if (field.equals("author") || field.equals("editor")) {
                        String[] authorsInValue = BibUtils.splitAuthors(value);
                        boolean contains = true;
                        for (String author : authorsInValue) {
                            if (!Arrays.asList(authors).contains(author)) {
                                contains = false;
                            }
                        }
                        if (contains) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }

}
