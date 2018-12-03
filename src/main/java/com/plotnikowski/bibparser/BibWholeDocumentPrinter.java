package com.plotnikowski.bibparser;

/**
 * Uses Strategy Design Pattern
 *
 * Class that is used to print whole document
 */
public class BibWholeDocumentPrinter implements IPrint {

    private static String fixedLengthString(String string, int length) {
        return String.format("%" + length + "s", string);
    }

    /**
     * Creates horizontal line
     *
     * @param character character which we want to repeat through the whole horizontal line
     * @param lenght    length of the line
     * @return created horizontal line of characters
     */
    private static String repeat(String character, int lenght) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /**
     * @param authors array of authors that should be formatted
     * @return formatted array
     */
    public static String[] handleNames(String[] authors) {
        for (int i = 0; i < authors.length; i++) {
            String[] names;
            String resultName;
            if (authors[i].contains("|")) {
                names = authors[i].split("\\|");
                resultName = names[1] + " " + names[0];
            } else {
                resultName = authors[i];
            }
            authors[i] = resultName.trim();
        }
        return authors;
    }

    /**
     * Returns prepared String
     *
     * @param document document that is to be printed
     * @return prepared String to be printed
     */
    public String print(BibDocument document) {

        StringBuilder finalResult = new StringBuilder();

        int maxRightLength = 0;
        int maxLeftLength = 0;

        for (BibObject object : document) {
            int fieldLength = object.getMaxFieldLength();
            int valueLength = object.getMaxValueLength();

            int nameL = object.getName().length();
            int keyL = object.getQuoteKey().length();

            maxLeftLength = Math.max(Math.max(fieldLength, maxLeftLength), Math.max(nameL, keyL));

            maxRightLength = Math.max(Math.max(valueLength, maxRightLength), Math.max(nameL, keyL));

        }

        int horizontalLineLength = maxLeftLength + maxRightLength + 8;
        String horizontalCharacter = "-";
        String horizontalLine = repeat(horizontalCharacter, horizontalLineLength);

        String verticalCharacter = "#";
        for (BibObject object : document) {

            String nameOfPublication = object.getName();
            String key = object.getQuoteKey();

            int nameLength = maxLeftLength + maxRightLength + 4 - nameOfPublication.length();
            int keyLength = maxLeftLength + maxRightLength + 4 - key.length();

            String alignmentN = fixedLengthString(" ", nameLength) + " " + verticalCharacter;
            String alignmentK = fixedLengthString(" ", keyLength) + " " + verticalCharacter;

            String realName = verticalCharacter + " " + nameOfPublication + alignmentN;
            String realKey = verticalCharacter + " " + key + alignmentK;

            StringBuilder builder = new StringBuilder();

            builder.append(horizontalLine).append('\n');
            builder.append(realName).append('\n');

            builder.append(horizontalLine).append('\n');
            builder.append(realKey).append('\n');

            for (BibPair pair : object.getBibPairs()) {

                int fieldLength = pair.getField().length();
                int valueLength = pair.getValue().length();

                String field = pair.getField();
                String value = pair.getValue();

                int lengthV = maxRightLength + 1 - valueLength;
                int lengthF = maxLeftLength + 1 - fieldLength;

                String alignmentV = fixedLengthString(" ", lengthV) + " " + verticalCharacter;
                String alignmentF = fixedLengthString(" ", lengthF);

                String realField = verticalCharacter + " " + field + alignmentF;
                String realValue = verticalCharacter + " " + value + alignmentV;

                if (field.equals("author") || field.equals("editor")) {
                    String[] authors = BibUtils.splitAuthors(value);

                    authors = handleNames(authors);

                    int lengthAdd = maxRightLength + 4;
                    String alignmentE = fixedLengthString(" ", lengthAdd) + verticalCharacter;

                    builder.append(horizontalLine).append('\n');
                    builder.append(realField).append(alignmentE).append('\n');
                    int length = realField.length();

                    String gap = verticalCharacter + fixedLengthString(" ", length);

                    for (String author : authors) {
                        int lengthA = maxRightLength + 1 - author.length();
                        String alignmentA = fixedLengthString(" ", lengthA) + verticalCharacter;

                        String realAuthor = "- " + author + alignmentA;
                        builder.append(gap).append(realAuthor).append('\n');
                    }

                } else {
                    builder.append(horizontalLine).append('\n');
                    builder.append(realField).append(realValue).append('\n');
                }
            }

            builder.append(horizontalLine).append('\n');
            finalResult.append(builder);
//            System.out.println(builder);
        }
        return finalResult.toString();
    }
}
