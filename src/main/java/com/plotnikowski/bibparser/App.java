package com.plotnikowski.bibparser;

import java.io.File;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        File filePath = new File("D:\\Studia\\GITHUB\\ObjectOrientedProgramming---Project-1\\test.bib");

        BibDocument document = BibParser.parse(filePath);

        String name = "BOOK";

        String auth = "Knuth";
        String[] authors = BibUtils.splitAuthors(auth);

        // mozna wiele filtrow jednoczesnie
        String[] names = new String[]{"PHDTHESIS"};

        BibPrinter printer = new BibPrinter(new BibWholeDocumentPrinter(), BibFilter.filter(document, new BibNameFilter(names)));
        BibPrinter printer2 = new BibPrinter(new BibWholeDocumentPrinter(), BibFilter.filter(document, new BibAuthorFilter(authors)));
        BibPrinter printerW = new BibPrinter(new BibWholeDocumentPrinter(), document);

        String[] testAuthors = new String[]{"Knuth | Donald E.", "Lipcoll | David J.", "Lawrie | D. H.", "Sameh | A. H."};

        System.out.println(Arrays.toString(BibWholeDocumentPrinter.handleNames(testAuthors)));
        if (document != null) {
//            printer.print();
//            printer2.print();
//            System.out.println(printerW.print());
        }
    }

}
