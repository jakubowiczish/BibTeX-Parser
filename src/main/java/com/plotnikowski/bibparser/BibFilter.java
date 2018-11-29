package com.plotnikowski.bibparser;

public class BibFilter {
    public static BibDocument filter(BibDocument document, IFilter... filters){
        for (IFilter filter : filters){
            document = filter.filter(document);
        }
        return document;
    }
}

