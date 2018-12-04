import com.plotnikowski.bibparser.*;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FilterTest {

    @Test
    public void filterAuthorTest() {

        BibDocument toBeFiltered = BibParser.parse(new File("filterTest.bib"));
        String[] authors = new String[]{"Lincoll", "Knuth"};

        BibPrinter expectedAuthorFilter = new BibPrinter(new BibWholeDocumentPrinter(),
                BibFilter.filter(toBeFiltered, new BibAuthorFilter(authors)));

        BibDocument realDocument = BibParser.parse(new File("realFilterTest.bib"));
        BibPrinter realAuthorFilter = new BibPrinter(new BibWholeDocumentPrinter(), realDocument);

        String expectedResult = expectedAuthorFilter.print();
        String realResult = realAuthorFilter.print();

        assertEquals(expectedResult, realResult);
    }

    @Test
    public void filterNameTest() {
        BibDocument toBeFiltered = BibParser.parse(new File("filterTest.bib"));
        String[] names = new String[]{"BOOK", "INCOLLECTION"};

        BibPrinter expectedNameFilter = new BibPrinter(new BibWholeDocumentPrinter(),
                BibFilter.filter(toBeFiltered, new BibNameFilter(names)));

        BibDocument realDocument = BibParser.parse(new File("realFilterTest.bib"));
        BibPrinter realNameFilter = new BibPrinter(new BibWholeDocumentPrinter(), realDocument);

        String expectedResult = expectedNameFilter.print();
        String realResult = realNameFilter.print();

        assertEquals(expectedResult, realResult);
    }
}
