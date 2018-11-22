package com.plotnikowski.bibparser;

import java.io.*;

/**
 * Class used to clean given file up - delete not needed fields
 */
public class StringCleaner {

    /**
     * @param file a path to a file
     * @return Cleaned up String - deleted not needed fields for instance @comment, @preamble and others
     * @throws IOException
     */
    public static String removeLines(String file) throws IOException {

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
                // System.out.println(line);

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
//            try (PrintWriter newWriter = new PrintWriter(FilePathBuilder.buildTemporaryFilePath(file))) {
//                newWriter.println(stringBuilder);
//            } catch (FileNotFoundException e1) {
//                e1.printStackTrace();
//            }

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
}
