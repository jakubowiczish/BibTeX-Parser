package com.plotnikowski.bibparser;

public class BibPrinter {
    private final BibDocument document;
    private final IPrint printer;

    public BibPrinter(IPrint printer, BibDocument document) {
        this.printer = printer;
        this.document = document;
    }

    public String print() {
        String result = printer.print(document);
        return result;
    }
}
