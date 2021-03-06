import com.plotnikowski.bibparser.BibDocument;
import com.plotnikowski.bibparser.BibObject;
import com.plotnikowski.bibparser.BibPair;
import com.plotnikowski.bibparser.BibParser;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BibParserTest {

    @Test
    public void parseTest() {
        File file = new File("bibParserTest.bib");
        BibDocument expectedDocument = BibParser.parse(file);

        String name = "BOOKLET";
        String quoteKey = "booklet-full";
        BibPair[] pairs = new BibPair[]{
                new BibPair("author", "Jill C. Knvth"),
                new BibPair("title", "The Programming of Computer Art"),
                new BibPair("howpublished", "Vernier Art Center"),
                new BibPair("address", "Stanford California"),
                new BibPair("month", "feb"),
                new BibPair("year", "1988"),
                new BibPair("note", "This is a full BOOKLET entry")
        };

        BibObject object = new BibObject(name, quoteKey, pairs);

        ArrayList<BibObject> objects = new ArrayList<>();
        objects.add(object);
        BibDocument realDocument = new BibDocument(objects);

        assertNotNull(expectedDocument);
        assertNotNull(realDocument);
        assertEquals(expectedDocument, realDocument);
    }
}
