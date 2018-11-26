package com.plotnikowski.bibparser;


import java.util.ArrayList;

public class BibPredicateFilter implements IFilter {
    private final IPredicate predicate;

    public interface IPredicate {
        boolean test(BibObject object);
    }

    BibPredicateFilter(IPredicate predicate){
        this.predicate = predicate;
    }


    @Override
    public BibDocument filter(BibDocument document) {
        ArrayList<BibObject> objects = new ArrayList<>();

        for(BibObject object : document){
            if(predicate.test(object)){
                objects.add(object);
            }
        }

        return new BibDocument(objects);
    }
}