package com.plotnikowski.bibparser;

public class BibPrinter {
    private final BibDocument document;
    private final IPrint printer;

    BibPrinter(IPrint printer, BibDocument document){
        this.printer = printer;
        this.document = document;
    }

    void print(){
        printer.print(document);
    }
}
