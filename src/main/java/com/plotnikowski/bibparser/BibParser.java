package com.plotnikowski.bibparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BibParser {
    /**
     * @param parsed The whole (cleaned) string that will be modified
     * @param firstBracketIndex index of the first bracket after '@'
     * @return A index of last bracket - bracket that is ending a object
     */
    static int objectLastBracket(String parsed, int firstBracketIndex) {
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
    static ArrayList<BibObject> parse(String filePath) {
        try {
            ArrayList<BibObject> objects = new ArrayList<>();
            BibBuilder[] bibBuilders = BibBuilders.getDefaultBuilders();

            String toParse = StringCleaner.removeLines(filePath);
            String parsed = WhiteSpaceDeleter.deleteWhiteSpaces(toParse);

            int firstBracketIndex = 0;
            int lastBracketIndex = 0;
            int lastAtIndex = parsed.indexOf('@');


//            System.out.println(parsed.indexOf('@'));
//            System.out.println(parsed.indexOf('@', parsed.length() - 1));

            while (lastAtIndex != -1) {
                firstBracketIndex = parsed.indexOf('{', lastAtIndex);
                // System.out.println("First: " + firstBracketIndex + " Last: " + lastBracketIndex);
                lastBracketIndex = objectLastBracket(parsed, firstBracketIndex);


                String name = parsed.substring(lastAtIndex + 1, firstBracketIndex);
                String[] attributes = parsed.substring(firstBracketIndex + 1, lastBracketIndex).split(",");
                String quoteName = attributes[0];

                BibPair[] pairs = new BibPair[attributes.length - 1];

                for (int i = 0; i < pairs.length; i++) {
                    String[] pair = attributes[i + 1].split("=");
                    // System.out.println(attributes[i+1]);
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

                // System.out.println(name + " " + Arrays.toString(pairs));
                lastAtIndex = parsed.indexOf('@', lastAtIndex + 1);
                // System.out.println(lastAtIndex);

            }
            return objects;
            // System.out.println("elo");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

