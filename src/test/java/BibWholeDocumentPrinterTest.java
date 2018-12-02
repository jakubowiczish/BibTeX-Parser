import com.plotnikowski.bibparser.BibDocument;
import com.plotnikowski.bibparser.BibParser;
import com.plotnikowski.bibparser.BibPrinter;
import com.plotnikowski.bibparser.BibWholeDocumentPrinter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class BibWholeDocumentPrinterTest {
    @Test
    public void printTest() {
        File file = new File("test2.bib");
        BibDocument expectedDocument = BibParser.parse(file);
        String realResult =
                "------------------------------------------------------------------\n" +
                        "# ARTICLE                                                        #\n" +
                        "------------------------------------------------------------------\n" +
                        "# article-full                                                   #\n" +
                        "------------------------------------------------------------------\n" +
                        "# author                                                         #\n" +
                        "#               - Leslie A. Aamport                              #\n" +
                        "------------------------------------------------------------------\n" +
                        "# title        # The Gnats and Gnus Document Preparation System  #\n" +
                        "------------------------------------------------------------------\n" +
                        "# journal      # mbox G-Animal's Journal                         #\n" +
                        "------------------------------------------------------------------\n" +
                        "# year         # 1986                                            #\n" +
                        "------------------------------------------------------------------\n" +
                        "# volume       # 41                                              #\n" +
                        "------------------------------------------------------------------\n" +
                        "# number       # 7                                               #\n" +
                        "------------------------------------------------------------------\n" +
                        "# pages        # 73+                                             #\n" +
                        "------------------------------------------------------------------\n" +
                        "# month        # jul                                             #\n" +
                        "------------------------------------------------------------------\n" +
                        "# note         # This is a full ARTICLE entry                    #\n" +
                        "------------------------------------------------------------------\n" +
                        "------------------------------------------------------------------\n" +
                        "# BOOK                                                           #\n" +
                        "------------------------------------------------------------------\n" +
                        "# book-full                                                      #\n" +
                        "------------------------------------------------------------------\n" +
                        "# author                                                         #\n" +
                        "#               - Donald E. Knuth                                #\n" +
                        "------------------------------------------------------------------\n" +
                        "# title        # Seminumerical Algorithms                        #\n" +
                        "------------------------------------------------------------------\n" +
                        "# volume       # 2                                               #\n" +
                        "------------------------------------------------------------------\n" +
                        "# series       # The Art of Computer Programming                 #\n" +
                        "------------------------------------------------------------------\n" +
                        "# publisher    # Addison-Wesley                                  #\n" +
                        "------------------------------------------------------------------\n" +
                        "# address      # Reading Massachusetts                           #\n" +
                        "------------------------------------------------------------------\n" +
                        "# edition      # Second                                          #\n" +
                        "------------------------------------------------------------------\n" +
                        "# month        # 10 jan                                          #\n" +
                        "------------------------------------------------------------------\n" +
                        "# year         # noopsort 1973c 1981                             #\n" +
                        "------------------------------------------------------------------\n" +
                        "# note         # This is a full BOOK entry                       #\n" +
                        "------------------------------------------------------------------\n";

        BibPrinter wholePrinter = new BibPrinter(new BibWholeDocumentPrinter(), expectedDocument);
        String result = wholePrinter.print();

        assertEquals(result, realResult);
    }
}
