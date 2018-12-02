package com.plotnikowski.bibparser;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.Arrays;

@CommandLine.Command(name = "test", mixinStandardHelpOptions = true, version = "first tak o")
public class Main implements Runnable {

    @Option(names = {"-f", "--file"}, required = true,
            description = "a path to BibTeX file that is to be parsed", paramLabel = "FilePath")
    private String filePath;

    @Option(names = {"-a", "--author"},  description = "authors", paramLabel = "Authors", split = ",")
    private String[] authors;

    @Option(names = {"-e", "--entry"}, description = "entry type", paramLabel = "Entry", split = ",")
    private String[] names;


    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
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
