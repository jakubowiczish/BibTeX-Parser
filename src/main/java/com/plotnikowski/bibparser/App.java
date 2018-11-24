package com.plotnikowski.bibparser;

import java.io.IOException;
import java.util.*;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        String filePath = "test.bib";
        // Scanner scanner = new Scanner(System.in);

        BibDocument document = BibParser.parse(filePath);
        // System.out.println(Arrays.toString(listOfOBjects.toArray()));
        // System.out.println(document);
//        String string = String.format("%17s| ", "");
//        System.out.println(string);
        ArrayList<String> authors = new ArrayList<String>();
        authors.add("Donald E. Knuth");
        if (document != null) {
//            BibSeeker.printSpecifiedPublication(document, "BOOK");
            BibSeeker.printPublicationOfAuthors(document, authors);
        }

//        try {
//            String result = StringCleaner.removeLines(filePath);
//            String cleaned = WhiteSpaceDeleter.deleteWhiteSpaces(result);
//            //System.out.println(cleaned);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        // checking
    }

}
