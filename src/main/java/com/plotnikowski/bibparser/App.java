package com.plotnikowski.bibparser;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        String filePath = "D:\\Studia\\GITHUB\\ObjectOrientedProgramming---Project-1\\test.bib";

        BibDocument document = BibParser.parse(filePath);

        String name = "BOOK";

        String auth = "Knuth";
        String[] authors = BibUtils.splitAuthors(auth);

        // mozna wiele filtrow jednoczesnie
        String[] names = new String[] {"PHDTHESIS"};

        BibPrinter printer = new BibPrinter(new BibWholeDocumentPrinter(), BibFilter.filter(document, new BibNameFilter(names)));
        BibPrinter printer2 = new BibPrinter(new BibWholeDocumentPrinter(), BibFilter.filter(document, new BibAuthorFilter(authors)));
        BibPrinter printerW = new BibPrinter(new BibWholeDocumentPrinter(), document);

        if (document != null) {
//            printer.print();
//            printer2.print();
            System.out.println(printerW.print());
        }
    }

}
