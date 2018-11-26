package com.plotnikowski.bibparser;

import java.util.ArrayList;
import java.util.Arrays;

public class BibSeeker {

    public static void printSpecifiedPublication(BibDocument document, String name) {
        ArrayList<BibObject> modifiedList = new ArrayList<>();
        BibDocument newDocument = new BibDocument(modifiedList);

        for (BibObject object : document.getObjects()) {
            if (object.getName().equals(name)) {
                modifiedList.add(object);
            }
        }

        BibPrinter.printAll(newDocument);
    }

    public static void printPublicationOfAuthors(BibDocument document, String[] authors) {
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
        BibPrinter.printAll(newDocument);
    }


    public static String[] splitAuthors(String authorField) {
        String[] authors = authorField.split("\\|");
        for(int i = 0; i < authors.length; i++){
            authors[i] = authors[i].trim();
        }
        return authors;
    }
}
