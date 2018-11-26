package com.plotnikowski.bibparser;

import java.io.*;

/**
 * Class used to clean given file up - delete not needed fields
 */
public class BibStringCleaner {
    /**
     *
     *
     * @param file a path to a file
     * @return Cleaned up String - deleted not needed fields for instance @comment, @preamble and others
     * @throws IOException
     */
    public static String removeRedundantLines(String file) throws IOException {

        BufferedReader bufferedReader = null;
        try {
            File inputFile = new File(file);

            if (!inputFile.isFile()) {
                throw new FileNotFoundException("Parameter is not a file");
            }
            bufferedReader = new BufferedReader(new FileReader(inputFile));

            String line = null;
            StringBuilder stringBuilder = new StringBuilder();

            int counter = 0;
            boolean countBrackets = false;

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    if (line.charAt(0) == '@' || counter != 0) {
                        if (line.startsWith("@preamble") || line.startsWith("@comment")) {
                            countBrackets = false;
                        } else {
                            stringBuilder.append(line);
                            stringBuilder.append("\n");
                            countBrackets = true;
                        }
                    }
                }
                if (countBrackets)
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == '{') {
                            counter++;
                        } else if (line.charAt(i) == '}') {
                            counter--;
                        }
                    }
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
            if(ch == '\"'){
                quoteCounter++;
                quoteCounter = quoteCounter % 2;
            }

            if(quoteCounter == 1){
                stringBuilder.append(ch);
            } else {
                if(ch != ' ' && ch != '\t' && ch != '\n' && ch != '\r'){
                    stringBuilder.append(ch);
                }
            }
        }
        return stringBuilder.toString();
    }
}

