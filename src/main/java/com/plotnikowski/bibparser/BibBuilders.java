package com.plotnikowski.bibparser;

/**
 * Class that contains pattern for every type of record
 */
public class BibBuilders {
    /**
     *
     *
     * @return Array that contains builders for all types of record
     */
    public static BibBuilder[] getDefaultBuilders() {
        return new BibBuilder[]{
                new BibBuilder("ARTICLE", TwoNames.createArray("author, title, journal, year"),
                        TwoNames.createArray("volume, number, pages, month, note, key")),

                new BibBuilder("BOOK", TwoNames.createArray("author|editor, title, publisher, year"),
                        TwoNames.createArray("volume, series, address, edition, month, note, key")),

                new BibBuilder("INPROCEEDINGS", TwoNames.createArray("author, title, booktitle, year"),
                        TwoNames.createArray("editor, volume|number, series, pages, address, month, organization, publisher, note, key")),

                new BibBuilder("BOOKLET", TwoNames.createArray("title"),
                        TwoNames.createArray("author, howpublished, address, month, year, note, key")),

                new BibBuilder("INBOOK", TwoNames.createArray("author|editor, title, chapter|pages, publisher, year"),
                        TwoNames.createArray("volume|number, series, type, address, edition, month, note, key")),

                new BibBuilder("INCOLLECTION", TwoNames.createArray("author, title, booktitle, publisher, year"),
                        TwoNames.createArray("editor, volume|number, series, type, chapter, pages, address, edition, month, note, key")),

                new BibBuilder("MANUAL", TwoNames.createArray("title"),
                        TwoNames.createArray("author, organization, address, edition, month, year, note, key")),

                new BibBuilder("MASTERSTHESIS", TwoNames.createArray("author, title, school, year"),
                        TwoNames.createArray("type, address, month, note, key")),

                new BibBuilder("PHDTHESIS", TwoNames.createArray("author, title, school, year"),
                        TwoNames.createArray("type, address, month, note, key")),

                new BibBuilder("TECHREPORT", TwoNames.createArray("author, title, institution, year"),
                        TwoNames.createArray("editor, volume|number, series, address, month, organization, publisher, note, key")),

                new BibBuilder("MISC", new TwoNames[0],
                        TwoNames.createArray("author, title, howpublished, month, year, note, key")),

                new BibBuilder("UNPUBLISHED", TwoNames.createArray("author, title, note"),
                        TwoNames.createArray("month, year, key")),
        };
    }
}
