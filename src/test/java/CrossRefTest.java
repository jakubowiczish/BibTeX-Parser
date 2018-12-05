import com.plotnikowski.bibparser.BibDocument;
import com.plotnikowski.bibparser.BibParser;
import com.plotnikowski.bibparser.BibPrinter;
import com.plotnikowski.bibparser.BibWholeDocumentPrinter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class CrossRefTest {

    @Test
    public void crossRefTest() {
        File file = new File("crossrefTest.bib");
        BibDocument expectedDocument = BibParser.parse(file);

        String realResult = "-------------------------------------------------\n" +
                "# BOOK                                          #\n" +
                "-------------------------------------------------\n" +
                "# book-full                                     #\n" +
                "-------------------------------------------------\n" +
                "# author                                        #\n" +
                "#             - Donald E. Knuth                 #\n" +
                "-------------------------------------------------\n" +
                "# title      # Seminumerical Algorithms         #\n" +
                "-------------------------------------------------\n" +
                "# volume     # 2                                #\n" +
                "-------------------------------------------------\n" +
                "# series     # The Art of Computer Programming  #\n" +
                "-------------------------------------------------\n" +
                "# publisher  # Addison-Wesley                   #\n" +
                "-------------------------------------------------\n" +
                "# address    # Reading Massachusetts            #\n" +
                "-------------------------------------------------\n" +
                "# edition    # Second                           #\n" +
                "-------------------------------------------------\n" +
                "# month      # 10 jan                           #\n" +
                "-------------------------------------------------\n" +
                "# year       # {\\noopsort{1973c}}1981           #\n" +
                "-------------------------------------------------\n" +
                "# note       # This is a full BOOK entry        #\n" +
                "-------------------------------------------------\n" +
                "-------------------------------------------------\n" +
                "# BOOK                                          #\n" +
                "-------------------------------------------------\n" +
                "# book-full2                                    #\n" +
                "-------------------------------------------------\n" +
                "# crossref   # book-full                        #\n" +
                "-------------------------------------------------\n" +
                "# year       # {\\noopsort{1973c}}1981           #\n" +
                "-------------------------------------------------\n" +
                "# note       # This is crossref entry           #\n" +
                "-------------------------------------------------\n" +
                "# author                                        #\n" +
                "#             - Donald E. Knuth                 #\n" +
                "-------------------------------------------------\n" +
                "# title      # Seminumerical Algorithms         #\n" +
                "-------------------------------------------------\n" +
                "# volume     # 2                                #\n" +
                "-------------------------------------------------\n" +
                "# series     # The Art of Computer Programming  #\n" +
                "-------------------------------------------------\n" +
                "# publisher  # Addison-Wesley                   #\n" +
                "-------------------------------------------------\n" +
                "# address    # Reading Massachusetts            #\n" +
                "-------------------------------------------------\n" +
                "# edition    # Second                           #\n" +
                "-------------------------------------------------\n" +
                "# month      # 10 jan                           #\n" +
                "-------------------------------------------------\n";

        BibPrinter wholePrinter = new BibPrinter(new BibWholeDocumentPrinter(), expectedDocument);
        String expectedResult = wholePrinter.print();

        assertEquals(expectedResult, realResult);

    }


}
