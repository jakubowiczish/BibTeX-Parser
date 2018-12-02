package com.plotnikowski.bibparser;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.File;

@CommandLine.Command(name = "test", mixinStandardHelpOptions = true, version = "first tak o")
public class Main implements Runnable {

    @CommandLine.Parameters(arity = "1",
            description = "a path to BibTeX file that is to be parsed", paramLabel = "FilePath")
    private File file;

    @Option(names = {"-a", "--author"},  description = "authors", paramLabel = "Authors", split = ",")
    private String[] authors;

    @Option(names = {"-e", "--entry"}, description = "entry type", paramLabel = "Entry", split = ",")
    private String[] names;


    public static void main(String[] args) {
        args = new String[] {"-a Knuth", "-e BOOK", "D:\\Studia\\GITHUB\\ObjectOrientedProgramming---Project-1\\test.bib"};
        CommandLine.run(new Main(), args);
    }

    public void run() {
        BibDocument document = BibParser.parse(file);
        BibPrinter printer = new BibPrinter(
                new BibWholeDocumentPrinter(),
                BibFilter.filter(document /*new BibNameFilter(names), new BibAuthorFilter(authors)*/)
                );

        System.out.println(printer.print());
    }
}
