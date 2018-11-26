package com.plotnikowski.bibparser;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        String filePath = "test.bib";
        // Scanner scanner = new Scanner(System.in);

        BibDocument document = BibParser.parse(filePath);
        // System.out.println(Arrays.toString(listOfOBjects.toArray()));
        // System.out.println(document);
//        String string = String.format("%17s| ", "");
//        System.out.println(string);


//        ArrayList<String> authors = new ArrayList<String>();
//        authors.add("Donald E. Knuth");
//        authors.add("D. H. Lawrie");
//        authors.add("A. H. Sameh");
//        authors.add("David J. Lipcoll");

        String auth = "Ulrich Underwood | Ned Net | Paul Pot";
        String[] authors = BibSeekerPrinter.splitAuthors(auth);

//        System.out.println(Arrays.toString(aut));

        BibPrinter printer = new BibPrinter(new BibWholeDocumentPrinter(), document);
        BibPrinter printer2 = new BibPrinter(new BibSeekerPrinter(authors), document);

        if (document != null) {
            printer2.print();
//            BibSeekerPrinter.printSpecificPublication(document, "BOOK");
//            BibSeekerPrinter.printPublicationOfSpecificAuthors(document, authors);
        }

//        try {
//            String result = BibStringCleaner.removeRedundantLines(filePath);
//            String cleaned = WhiteSpaceDeleter.deleteWhiteSpaces(result);
//            //System.out.println(cleaned);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        // checking
    }

}
