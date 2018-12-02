package com.plotnikowski.bibparser;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;

@Command(name = "test", mixinStandardHelpOptions = true, version = "first tak o")
public class Main implements Runnable {

    @Option(names = {"--file"}, description = "a path to BibTeX file that is to be parsed", paramLabel = "FilePath")
    private File file;

    @Option(names = {"--author"}, description = "authors", paramLabel = "Authors", split = ",")
    private String[] authors;

    @Option(names = {"--name"}, description = "entry type", paramLabel = "Entry", split = ",")
    private String[] names;


    public static void main(String[] args) {
//        args = new String[]{"--file=D:\\Studia\\GITHUB\\ObjectOrientedProgramming---Project-1\\test.bib", "--author=Lipcoll,Knuth"};
        CommandLine.run(new Main(), args);
    }

    public void run() {
        BibDocument document = BibParser.parse(file);

        if (authors == null && names == null) {
            BibPrinter printerWithoutArguments = new BibPrinter(new BibWholeDocumentPrinter(), document);
            System.out.println(printerWithoutArguments.print());

        } else if (authors == null) {
            BibPrinter printerWithoutAuthors = new BibPrinter(
                    new BibWholeDocumentPrinter(),
                    BibFilter.filter(document, new BibNameFilter(names))
            );
            System.out.println(printerWithoutAuthors.print());

        } else if (names == null) {
            BibPrinter printerWithoutNames = new BibPrinter(
                    new BibWholeDocumentPrinter(),
                    BibFilter.filter(document, new BibAuthorFilter(authors))
            );
            System.out.println(printerWithoutNames.print());

        } else {
            BibPrinter printerWithBothFilters = new BibPrinter(
                    new BibWholeDocumentPrinter(),
                    BibFilter.filter(document, new BibNameFilter(names), new BibAuthorFilter(authors))
            );
            System.out.println(printerWithBothFilters.print());
        }
    }
}
