package com.plotnikowski.bibparser;

/**
 * Class used to handle situations when we can have two possible names for one field
 */
public class BibFieldNames {
    private String name1;
    private String name2;

    BibFieldNames(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    BibFieldNames(String name) {
        this(name, null);
    }

    /**
     * Deals with situation, when fields' names are not exactly specified e.g.
     * when given publication is book, one of needed fields' names is author or editor
     *
     * @param string constains one or two possible names separated with '|'
     * @return array of created fields
     */
    public static BibFieldNames[] createArray(String string) {
        String[] split = string.split(", ");
        BibFieldNames[] bibFieldNames = new BibFieldNames[split.length];
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("|")) {
                String[] split2 = split[i].split("\\|");
                bibFieldNames[i] = new BibFieldNames(split2[0], split2[1]);
            } else {
                bibFieldNames[i] = new BibFieldNames(split[i]);
            }
        }
        return bibFieldNames;
    }

    /**
     * Checks whether there is needed or optional field in pair
     *
     * @param pair pair which fields' we want to check
     * @return boolean value that tells us whether we have name1 or name2 in given pair
     */
    public boolean contains(BibPair pair) {
        if (pair.getField().equals(name1)) return true;
        return pair.getField().equals(name2);
    }

    @Override
    public String toString() {
        return name1 + "|" + name2;
    }
}
