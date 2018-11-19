import java.io.File;

public class FilePathBuilder {
    public static String buildTemporaryFilePath(String path) {
        if (!path.contains(".")) return path + "_tmp";
        int index = path.lastIndexOf(".");
        return path.substring(0, index) + ".tmp";
    }
}
