package com.plotnikowski.bibparser;

import java.util.ArrayList;

public class BibDocument {
    private ArrayList<BibObject> objects;

    public BibDocument(ArrayList<BibObject> objects) {
        this.objects = objects;
    }


    /**
     * Show all publications
     *
     * @return
     */
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
