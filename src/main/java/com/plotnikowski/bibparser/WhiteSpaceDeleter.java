package com.plotnikowski.bibparser;

public class WhiteSpaceDeleter {
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
