package com.plotnikowski.bibparser;

import java.util.Arrays;

public class TwoNames {
    private String name1;
    private String name2;

    TwoNames(String name1, String name2){
        this.name1 = name1;
        this.name2 = name2;
    }

    TwoNames(String name){
        this(name, null);
    }

    public static TwoNames[] createArray(String string) {
        String[] split = string.split(", ");
        TwoNames[] twoNames = new TwoNames[split.length];
        for (int i = 0; i < split.length; i++) {
            if(split[i].contains("|")) {
                String[] split2 = split[i].split("\\|");
                twoNames[i] = new TwoNames(split2[0], split2[1]);
            } else {
                twoNames[i] = new TwoNames(split[i]);
            }
        }
        return twoNames;
    }

    public boolean contains(BibPair pair) {
        if(pair.getField().equals(name1)) return true;
        return pair.getField().equals(name2);
    }

    @Override
    public String toString() {
        return name1 + "|" + name2;
    }
}
