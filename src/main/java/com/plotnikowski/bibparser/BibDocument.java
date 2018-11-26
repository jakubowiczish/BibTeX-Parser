package com.plotnikowski.bibparser;

import java.util.ArrayList;

/**
 * Class representing document
 */
public class BibDocument {
    private ArrayList<BibObject> objects;

    public BibDocument(ArrayList<BibObject> objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "BibDocument{" +
                "objects=" + objects +
                '}';
    }

    public ArrayList<BibObject> getObjects() {
        return objects;
    }
}
