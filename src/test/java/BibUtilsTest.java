import com.plotnikowski.bibparser.BibUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibUtilsTest {
    @Test
    public void splitAuthorsTest() {
        String authorsField = "Jakub Płotnikowski and Jill C. Knvth and Daniel D. Lincoll and Larry Manmaker";
        String[] expectedAuthors = BibUtils.splitAuthors(authorsField);

        String[] realAuthors = new String[]{"Jakub Płotnikowski", "Jill C. Knvth", "Daniel D. Lincoll", "Larry Manmaker"};
        assertArrayEquals(expectedAuthors, realAuthors);
    }
}
