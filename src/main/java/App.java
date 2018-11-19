import java.io.IOException;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        try {
            StringCleaner.removeLines("test.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
