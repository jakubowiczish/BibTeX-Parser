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
        System.out.println(document);
    }

}
