package com.plotnikowski.bibparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to parse bibtex file content into object representation
 */
public class BibParser {
    /**
     * @param parsed            The whole (cleaned) string that will be modified
     * @param firstBracketIndex index of the first bracket after '@'
     * @return A index of last bracket - bracket that is ending a object
     */
    private static int objectLastBracket(String parsed, int firstBracketIndex) {
        int counter = 1;

        int lastCheckedBracketIndex = firstBracketIndex;
        while (counter > 0) {
            int nextOpeningBracketIndex = parsed.indexOf('{', lastCheckedBracketIndex + 1);
            int nextClosingBracketIndex = parsed.indexOf('}', lastCheckedBracketIndex + 1);

            if (nextOpeningBracketIndex != -1 && nextOpeningBracketIndex < nextClosingBracketIndex) {
                counter++;
                lastCheckedBracketIndex = nextOpeningBracketIndex;
            } else {
                counter--;
                lastCheckedBracketIndex = nextClosingBracketIndex;
            }
        }
        return lastCheckedBracketIndex;
    }

    /**
     * @param filePath Path to file that will be parsed
     * @return list of parsed Objects
     */
    static BibDocument parse(String filePath) {
        try {
            Map<String, String> stringMap = new HashMap<>();
            ArrayList<BibObject> objects = new ArrayList<>();
            BibDocument bibDocument = new BibDocument(objects);

            BibBuilder[] bibBuilders = BibBuilders.getDefaultBuilders();

            String toParse = StringCleaner.removeLines(filePath);
            String parsed = WhiteSpaceDeleter.deleteWhiteSpaces(toParse);

            int firstBracketIndex = 0;
            int lastBracketIndex = 0;
            int lastAtIndex = parsed.indexOf('@');

            while (lastAtIndex != -1) {
                firstBracketIndex = parsed.indexOf('{', lastAtIndex);
                lastBracketIndex = objectLastBracket(parsed, firstBracketIndex);

                String name = parsed.substring(lastAtIndex + 1, firstBracketIndex);
                String[] attributes = parsed.substring(firstBracketIndex + 1, lastBracketIndex).split(",");

                if(!handleString(stringMap, name, attributes)) {
                    String quoteName = attributes[0];

                    BibPair[] pairs = new BibPair[attributes.length - 1];

                    for (int i = 0; i < pairs.length; i++) {
                        String[] pair = attributes[i + 1].split("=");
                        pairs[i] = new BibPair(pair[0], pair[1].substring(1, pair[1].length() - 1));
                    }

                    boolean found = false;
                    for (BibBuilder bibBuilder : bibBuilders) {
                        if (bibBuilder.getName().equals(name)) {
                            found = true;
                            objects.add(bibBuilder.build(quoteName, pairs));
                            break;
                        }
                    }

                    if (!found) {
                        throw new IllegalArgumentException("Cannot parse field with " + name + " name");
                    }
                }
                lastAtIndex = parsed.indexOf('@', lastAtIndex + 1);
            }
            for(BibObject bibObject : objects) {
                bibObject.handleStringMap(stringMap);
            }

            return bibDocument;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean handleString(Map<String, String> stringMap, String name, String[] attributes) {
        if(name.equals("STRING")) {
            for(String attribute : attributes) {
                String[] pair = attribute.split("=");
                stringMap.put(pair[0], pair[1]);
            }
            return true;
        }
        return false;
    }
}

