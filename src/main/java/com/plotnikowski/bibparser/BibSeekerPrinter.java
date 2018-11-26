package com.plotnikowski.bibparser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used to search for publications given name of publication or author (authors)
 */
public class BibSeekerPrinter extends BibWholeDocumentPrinter {

    private final String name;
    private final String[] authors;

    BibSeekerPrinter(String name){
        this.name = name;
        authors = null;
    }

    BibSeekerPrinter(String[] authors){
        this.authors = authors;
        name = null;
    }

    @Override
    public void print(BibDocument document){
        if(name != null){
            printSpecificPublication(document, name);
        }
        if(authors != null){
            printPublicationOfSpecificAuthors(document, authors);
        }
    }


    /**
     * Prints publications which name equals given name in second argument
     *
     * @param document a whole document
     * @param name publication name e.g. BOOK, ARTICLE etc.
     */
    public void printSpecificPublication(BibDocument document, String name) {
        ArrayList<BibObject> modifiedList = new ArrayList<>();
        BibDocument newDocument = new BibDocument(modifiedList);

        for (BibObject object : document.getObjects()) {
            if (object.getName().equals(name)) {
                modifiedList.add(object);
            }
        }
        super.print(newDocument);
    }

    /**
     * Prints publications that have given authors in themselves
     *
     * @param document a whole document
     * @param authors array of authors
     */
    public void printPublicationOfSpecificAuthors(BibDocument document, String[] authors) {
        ArrayList<BibObject> modifiedList = new ArrayList<>();
        BibDocument newDocument = new BibDocument(modifiedList);

        for (BibObject object : document.getObjects()) {
            BibPair[] pairs = object.getBibPairs();

            for (BibPair pair : pairs) {
                String field = pair.getField();
                String value = pair.getValue();

                if (field.equals("author") || field.equals("editor")) {
                    String[] authorsInValue = splitAuthors(value);
                    boolean contains = true;
                    for (String author : authorsInValue) {
                        if (!Arrays.asList(authors).contains(author)) {
                            contains = false;
                        }
                    }
                    if (contains) {
                        modifiedList.add(object);
                    }
                }
            }
        }
        super.print(newDocument);
    }

    /**
     * Splits authors separated with '|' and returns array of authors
     *
     * @param authorField singular String that contains authors separated with '|'
     * @return array of authors
     */
    public static String[] splitAuthors(String authorField) {
        String[] authors = authorField.split("\\|");
        for(int i = 0; i < authors.length; i++){
            authors[i] = authors[i].trim();
        }
        return authors;
    }
}
