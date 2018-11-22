package com.plotnikowski.bibparser;

import java.util.ArrayList;

public class BibDocument {
    private ArrayList<BibObject> objects;

    public BibDocument(ArrayList<BibObject> objects) {
        this.objects = objects;
    }


    @Override
    public String toString() {
        return objects + "";
    }
}
