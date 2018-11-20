package com.plotnikowski.bibparser;

public class BibBuilders {
    public static BibBuilder[] getDefaultBuilders(){
        return new BibBuilder[]{
                new BibBuilder("ARTICLE", "author, title, journal, year".split(", "), "volume, number, pages, month, note, key".split(", ")),
        };
    }
}
