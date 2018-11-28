package com.plotnikowski.bibparser;

import java.io.*;
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
    public static BibDocument parse(String filePath) {
        try {
            Map<String, String> stringMap = new HashMap<>();
            ArrayList<BibObject> objects = new ArrayList<>();
            BibDocument bibDocument = new BibDocument(objects);

            BibBuilder[] bibBuilders = BibBuilders.getDefaultBuilders();

            String fileContent = BibParser.receiveFile(filePath);
            String withoutWhiteSpaces = BibStringCleaner.deleteWhiteSpaces(fileContent);

            int firstBracketIndex = 0;
            int lastBracketIndex = 0;
            int lastAtIndex = withoutWhiteSpaces.indexOf('@');

            while (lastAtIndex != -1) {
                firstBracketIndex = withoutWhiteSpaces.indexOf('{', lastAtIndex);
                lastBracketIndex = objectLastBracket(withoutWhiteSpaces, firstBracketIndex);

                String entry = withoutWhiteSpaces.substring(lastAtIndex + 1, firstBracketIndex);
                String[] attributes = withoutWhiteSpaces.substring(firstBracketIndex + 1, lastBracketIndex).split(",");

                if (!handleString(stringMap, entry, attributes) && !isEntryRedundant(entry)) {
                    String quoteName = attributes[0];

                    BibPair[] pairs = new BibPair[attributes.length - 1];

                    for (int i = 0; i < pairs.length; i++) {
                        String[] pair = attributes[i + 1].split("=");
                        
                        pairs[i] = new BibPair(pair[0], pair[1].substring(1, pair[1].length() - 1));
                    }

                    boolean found = false;
                    for (BibBuilder bibBuilder : bibBuilders) {
                        if (bibBuilder.getName().equals(entry)) {
                            found = true;
                            objects.add(bibBuilder.build(quoteName, pairs));
                            break;
                        }
                    }

                    if (!found) {
                        throw new IllegalArgumentException("Cannot parse field with " + entry + " entry");
                    }
                }
                lastAtIndex = withoutWhiteSpaces.indexOf('@', lastAtIndex + 1);
            }

            for (BibObject bibObject : objects) {
                bibObject.handleStringMap(stringMap);
            }

            return bibDocument;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String receiveFile(String filePath) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File inputFile = new File(filePath);

            if (!inputFile.isFile()) {
                throw new FileNotFoundException("Parameter is not a file");
            }
            bufferedReader = new BufferedReader(new FileReader(inputFile));

            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }

            return stringBuilder.toString();

        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static boolean isEntryRedundant(String entry){
        String[] redundantEntries = new String[]{"preamble", "comment"};

        for (String redundantEntry : redundantEntries){
            if(entry.equals(redundantEntry)){
                return true;
            }
        }
        return false;
    }

    private static boolean handleString(Map<String, String> stringMap, String name, String[] attributes) {
        if (name.equals("STRING")) {
            for (String attribute : attributes) {
                String[] pair = attribute.split("=");
                stringMap.put(pair[0], pair[1]);
            }
            return true;
        }
        return false;
    }
}

