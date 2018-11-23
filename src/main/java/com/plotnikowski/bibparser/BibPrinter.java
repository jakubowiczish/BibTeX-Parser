package com.plotnikowski.bibparser;

public class BibPrinter {

    private static String fixedLengthString(String string, int length) {
        return String.format("%" + length + "s", string);
    }

    public static void printAll(BibDocument document) {

        int maxRightLength = 0;
        int maxLeftLength = 0;

        for (BibObject object : document.getObjects()) {
            int fieldLength = object.getMaxFieldLength();
            int valueLength = object.getMaxValueLength();

            int nameL = object.getName().length();
            int keyL = object.getQuoteKey().length();

            maxLeftLength = Math.max(Math.max(fieldLength, maxLeftLength), Math.max(nameL, keyL));

            maxRightLength = Math.max(Math.max(valueLength, maxRightLength), Math.max(nameL, keyL));

        }

        int horizontalLineLength = maxLeftLength + maxRightLength + 8;
        String character = "-";
        String horizontalLine = character.repeat(horizontalLineLength);

        for (BibObject object : document.getObjects()) {

            String nameOfPublication = object.getName();
            String key = object.getQuoteKey();

            int nameLength = maxLeftLength + maxRightLength + 4 - nameOfPublication.length();
            int keyLength = maxLeftLength + maxRightLength + 4 - key.length();

            String alignmentN = fixedLengthString(" ", nameLength) + " |";
            String alignmentK = fixedLengthString(" ", keyLength) + " |";

            String realName = "| " + nameOfPublication + alignmentN;
            String realKey = "| " + key + alignmentK;


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

                String alignmentV = fixedLengthString(" ", lengthV) + " |";
                String alignmentL = fixedLengthString(" ", lengthF);

                String realValue = "| " + value + alignmentV;
                String realField = "| " + field + alignmentL;

                builder.append(horizontalLine).append('\n');
                builder.append(realField).append(realValue).append('\n');
            }

            builder.append(horizontalLine).append('\n');
            System.out.println(builder);
        }
    }


/*
    public static void printAll(BibDocument document, String frame) {

        int maxRightLength = 0;
        int maxLeftLength = 0;

        for (BibObject object : document.getObjects()) {
            int fieldLength = object.getMaxFieldLength();
            int valueLength = object.getMaxValueLength();

            int nameL = object.getName().length();
            int keyL = object.getQuoteKey().length();

            maxLeftLength = Math.max(Math.max(fieldLength, maxLeftLength), Math.max(nameL, keyL));

            maxRightLength = Math.max(Math.max(valueLength, maxRightLength), Math.max(nameL, keyL));

        }

        int horizontalLineLength = maxLeftLength + maxRightLength + 8;
        String character = frame;
        String horizontalLine = character.repeat(horizontalLineLength);

        for (BibObject object : document.getObjects()) {

            String nameOfPublication = object.getName();
            String key = object.getQuoteKey();

            int nameLength = maxLeftLength + maxRightLength + 4 - nameOfPublication.length();
            int keyLength = maxLeftLength + maxRightLength + 4 - key.length();

            String alignmentN = fixedLengthString(" ", nameLength) + " " + frame ;
            String alignmentK = fixedLengthString(" ", keyLength) + " " + frame;

            String realName = frame + " " + nameOfPublication + alignmentN;
            String realKey = frame + " " + key + alignmentK;


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

                String alignmentV = fixedLengthString(" ", lengthV) + " " + frame;
                String alignmentL = fixedLengthString(" ", lengthF);

                String realValue = frame + " " + value + alignmentV;
                String realField = frame + " " + field + alignmentL;

                builder.append(horizontalLine).append('\n');
                builder.append(realField).append(realValue).append('\n');
            }

            builder.append(horizontalLine).append('\n');
            System.out.println(builder);
        }
    }
    */
}
