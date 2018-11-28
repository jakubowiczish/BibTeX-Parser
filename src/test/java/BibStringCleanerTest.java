import com.plotnikowski.bibparser.BibStringCleaner;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibStringCleanerTest {
    @Test
    public void testDeleteWhiteSpaces() {
        String toBeTested = "@MASTERSTHESIS{mastersthesis-full,\n" +
                "   author = \"douard Masterly\",\n" +
                "   title = \"Mastering Thesis Writing\",\n";

        String result = "@MASTERSTHESIS{mastersthesis-full,author=\"douard Masterly\",title=\"Mastering Thesis Writing\",";
        assertEquals(BibStringCleaner.deleteWhiteSpaces(toBeTested), result);
    }
}