import com.plotnikowski.bibparser.BibStringCleaner;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class BibStringCleanerTest {
    @Test
    public void testDeleteWhiteSpaces() {
        String toBeTested = "@MASTERSTHESIS{mastersthesis-full,\n" +
                "   author = \"douard Masterly\",\n" +
                "   title = \"Mastering Thesis Writing\",\n";

        String realResult = "@MASTERSTHESIS{mastersthesis-full,author=\"douard Masterly\",title=\"Mastering Thesis Writing\",";
        String expectedResult = BibStringCleaner.deleteWhiteSpaces(toBeTested, new LinkedList<>());
        assertEquals(expectedResult, realResult);
    }
}
