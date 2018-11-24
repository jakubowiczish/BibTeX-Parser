package com.plotnikowski.bibparser;

import java.util.ArrayList;

public class BibSeeker {

    public static void printSpecifiedPublication(BibDocument document, String name){
        ArrayList<BibObject> modifiedList = new ArrayList<>();
        BibDocument newDocument = new BibDocument(modifiedList);

        for(BibObject object : document.getObjects()){
            if(object.getName().equals(name)){
                modifiedList.add(object);
            }
        }

        BibPrinter.printAll(newDocument);
    }

    public static void printPublicationOfAuthors(BibDocument document, ArrayList<String> authors){
        ArrayList<BibObject> modifiedList = new ArrayList<>();
        BibDocument newDocument = new BibDocument(modifiedList);

        for(BibObject object : document.getObjects()){
            BibPair[] pairs = object.getBibPairs();

            for(BibPair pair : pairs){
                String field = pair.getField();
                String value = pair.getValue();

                if(field.equals("author") || field.equals("editor")){
                    for(String author : authors){
                        if(value.equals(author)){
                            modifiedList.add(object);
                        }

                    }
                }
            }
        }
        BibPrinter.printAll(newDocument);
    }
}
