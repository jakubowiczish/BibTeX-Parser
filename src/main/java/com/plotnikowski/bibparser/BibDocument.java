package com.plotnikowski.bibparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Class representing document, uses Iterator Design Pattern
 * Contains list of objects that represent whole document
 */
public class BibDocument implements Iterable<BibObject> {
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

    @Override
    public Iterator<BibObject> iterator() {
        return objects.iterator();
    }

    @Override
    public void forEach(Consumer<? super BibObject> action) {
        objects.forEach(action);
    }

    @Override
    public Spliterator<BibObject> spliterator() {
        return objects.spliterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibDocument that = (BibDocument) o;
        return Objects.equals(objects, that.objects);
    }

}
