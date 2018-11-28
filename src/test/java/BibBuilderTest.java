import com.plotnikowski.bibparser.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibBuilderTest {

    @Test
    public void testBuild() {

        String name = "ARTICLE";
        String quoteKey = "article-full";

        BibPair[] bibPairs = new BibPair[]{
                new BibPair("author", "{L[eslie] A. Aamport}"),
                new BibPair("title", "{The Gnats and Gnus Document Preparation System}"),
                new BibPair("journal", "mbox G-Animal's Journal"),
                new BibPair("year", "1986"),
                new BibPair("volume", "41"),
                new BibPair("number", "7"),
                new BibPair("pages", "73+"),
                new BibPair("month", "jul"),
                new BibPair("note","This is a full ARTICLE entry")
        };

        BibObject object = new BibObject(name, quoteKey, bibPairs);


        BibFieldNames[] needed = BibFieldNames.createArray("author, title, journal, year");
        BibFieldNames[] optional = BibFieldNames.createArray("volume, number, pages, month, note, key");

        BibBuilder builder = new BibBuilder(name, needed, optional);

        assertEquals(object, builder.build(quoteKey, bibPairs));
    }


}
