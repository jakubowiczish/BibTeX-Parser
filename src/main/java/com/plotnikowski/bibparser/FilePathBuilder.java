package com.plotnikowski.bibparser;

import java.io.File;

public class FilePathBuilder {
    public static String buildTemporaryFilePath(String path) {
        if (!path.contains(".")) return path + ".tmp";
        int index = path.lastIndexOf(".");
        return path.substring(0, index) + ".tmp";
    }
}
