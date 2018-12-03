package com.plotnikowski.bibparser;

import java.security.Policy;
import java.util.LinkedList;

/**
 * Class used to clean given file up - delete not needed fields
 */
public class BibStringCleaner {
    /**
     * Deletes white spaces from given String, except for white spaces between quotation marks
     *
     * @param toDelete String that is to be cleaned up from white spaces
     * @return String without white spaces apart from ones between quotation marks
     */
    public static String deleteWhiteSpaces(String toDelete, LinkedList<Integer> newLines) {
        StringBuilder stringBuilder = new StringBuilder();
        int quoteCounter = 0;

        for (int i = 0; i < toDelete.length(); i++) {
            char ch = toDelete.charAt(i);
            if (ch == '\"') {
                quoteCounter++;
                quoteCounter = quoteCounter % 2;
            }

            if (quoteCounter == 1) {
                stringBuilder.append(ch);
            } else {
                if (ch != ' ' && ch != '\t' && ch != '\n' && ch != '\r') {
                    stringBuilder.append(ch);
                } else if(ch == '\n'){
                    newLines.add(stringBuilder.length());
                }
            }
        }
        return stringBuilder.toString();
    }
}

