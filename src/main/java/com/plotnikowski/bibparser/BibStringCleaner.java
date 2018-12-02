package com.plotnikowski.bibparser;

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
    public static String deleteWhiteSpaces(String toDelete) {
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
                }
            }
        }
        return stringBuilder.toString();
    }
}

