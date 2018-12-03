package com.plotnikowski.bibparser;

/**
 * Class that contains pattern for every type of record
 */
public class BibBuilders {
    /**
     * @return Array that contains builders for all types of records
     */
    public static BibBuilder[] getDefaultBuilders() {
        return new BibBuilder[]{
                new BibBuilder("ARTICLE", BibFieldNames.createArray("author, title, journal, year"),
                        BibFieldNames.createArray("volume, number, pages, month, note, key")),

                new BibBuilder("BOOK", BibFieldNames.createArray("author|editor, title, publisher, year"),
                        BibFieldNames.createArray("volume, series, address, edition, month, note, key")),

                new BibBuilder("INPROCEEDINGS", BibFieldNames.createArray("author, title, booktitle, year"),
                        BibFieldNames.createArray("editor, volume|number, series, pages, address, month, organization, publisher, note, key")),

                new BibBuilder("BOOKLET", BibFieldNames.createArray("title"),
                        BibFieldNames.createArray("author, howpublished, address, month, year, note, key")),

                new BibBuilder("INBOOK", BibFieldNames.createArray("author|editor, title, chapter|pages, publisher, year"),
                        BibFieldNames.createArray("volume|number, series, type, address, edition, month, note, key")),

                new BibBuilder("INCOLLECTION", BibFieldNames.createArray("author, title, booktitle, publisher, year"),
                        BibFieldNames.createArray("editor, volume|number, series, type, chapter, pages, address, edition, month, note, key")),

                new BibBuilder("MANUAL", BibFieldNames.createArray("title"),
                        BibFieldNames.createArray("author, organization, address, edition, month, year, note, key")),

                new BibBuilder("MASTERSTHESIS", BibFieldNames.createArray("author, title, school, year"),
                        BibFieldNames.createArray("type, address, month, note, key")),

                new BibBuilder("PHDTHESIS", BibFieldNames.createArray("author, title, school, year"),
                        BibFieldNames.createArray("type, address, month, note, key")),

                new BibBuilder("TECHREPORT", BibFieldNames.createArray("author, title, institution, year"),
                        BibFieldNames.createArray("editor, volume|number, series, address, month, organization, publisher, note, key")),

                new BibBuilder("MISC", new BibFieldNames[0],
                        BibFieldNames.createArray("author, title, howpublished, month, year, note, key")),

                new BibBuilder("UNPUBLISHED", BibFieldNames.createArray("author, title, note"),
                        BibFieldNames.createArray("month, year, key")),
        };
    }
}
