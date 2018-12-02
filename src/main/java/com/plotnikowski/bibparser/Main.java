package com.plotnikowski.bibparser;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@CommandLine.Command(name = "test", mixinStandardHelpOptions = true, version = "first tak o")
public class Main implements Runnable {

    @Option(names = {"--file"}, required = true, usageHelp = true,
            description = "a path to BibTeX file that is to be parsed", paramLabel = "File Path")
    private String filePath;

    @Option(names = {"--author"}, usageHelp = true, description = "authors", paramLabel = "Authors", split = ",")
    private String[] authors;

    @Option(names = {"--entry"}, usageHelp = true, description = "entry type", paramLabel = "Entry")
    private String[] names;


    public static void main(String[] args) {
        CommandLine.run(new Main(), args);
    }

    @Override
    public void run() {
        BibDocument document = BibParser.parse(filePath);
        BibPrinter printer = new BibPrinter(
                new BibWholeDocumentPrinter(),
                BibFilter.filter(document, new BibNameFilter(names), new BibAuthorFilter(authors))
                );

        System.out.println(printer);
    }
}
