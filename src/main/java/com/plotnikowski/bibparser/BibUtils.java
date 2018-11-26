package com.plotnikowski.bibparser;

public class BibUtils {
    /**
     * Splits authors separated with '|' and returns array of authors
     *
     * @param authorField singular String that contains authors separated with '|'
     * @return array of authors
     */
    public static String[] splitAuthors(String authorField) {
        String[] authors = authorField.split("\\|");
        for (int i = 0; i < authors.length; i++) {
            authors[i] = authors[i].trim();
        }
        return authors;
    }
}
