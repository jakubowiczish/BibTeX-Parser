import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LearnTokenizer {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;

        try {
            String line;
            bufferedReader = new BufferedReader(new FileReader("test.txt"));

            while ((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);

                StringTokenizer stringTokenizer = new StringTokenizer(line, ",{=");

                while (stringTokenizer.hasMoreElements()) {
                    String takise = stringTokenizer.nextElement().toString();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("\n " + takise);

                    System.out.println(stringBuilder.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
