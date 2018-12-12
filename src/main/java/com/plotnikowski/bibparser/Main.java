package com.plotnikowski.bibparser;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.ArrayList;

@Command(
        name = "BIBTEX PARSER",
        version = "BibTexParser version 1.0 \ncreated by Jakub Płotnikowski, 2018 \nAll rights reserved",
        header = "Presents filtered BibTex file",
        description = "Given BibTex file is parsed " +
                "and filtered",
        mixinStandardHelpOptions = true
)
public class Main implements Runnable {

    @Option(names = {"--file"}, description = "a path to BibTeX file that is to be parsed", paramLabel = "FILEPATH")
    private File file;

    @Option(names = {"--author"}, description = "authors separated with ','", paramLabel = "AUTHOR(S)", split = ",")
    private String[] authors;

    @Option(names = {"--name"}, description = "entry types e.g. BOOK, separated with ','", paramLabel = "ENTRY", split = ",")
    private String[] names;


    public static void main(String[] args) {
//        args = new String[]{"--file=D:\\Studia\\GITHUB\\ObjectOrientedProgramming---Project-1\\test.bib", "--name=BOOK"};
        CommandLine.run(new Main(), args);
    }

    public void run() {
        if (file == null) {
            System.out.println("Try adding -h for more information");
            return;
        }

        BibDocument document = BibParser.parse(file);
        ArrayList<IFilter> filters = new ArrayList<>();

        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                names[i] = names[i].toUpperCase();
            }
            filters.add(new BibNameFilter(names));
        }
        if (authors != null) {
            filters.add(new BibAuthorFilter(authors));
        }

        BibPrinter printer = new BibPrinter(
                new BibWholeDocumentPrinter(),
                BibFilter.filter(document, filters.toArray(new IFilter[0]))
        );
        System.out.println(printer.print());
    }
}
