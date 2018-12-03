package com.plotnikowski.bibparser;

import java.util.Objects;

/**
 * Uses Strategy Design Pattern
 */
public class BibPrinter {
    private final BibDocument document;
    private final IPrint printer;

    public BibPrinter(IPrint printer, BibDocument document) {
        this.printer = printer;
        this.document = document;
    }

    public String print() {
        return printer.print(document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibPrinter printer1 = (BibPrinter) o;
        return Objects.equals(document, printer1.document) &&
                Objects.equals(printer, printer1.printer);
    }
}
