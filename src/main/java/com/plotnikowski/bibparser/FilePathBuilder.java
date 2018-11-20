package com.plotnikowski.bibparser;

import java.io.File;


public class FilePathBuilder {
    /**
     * Functions adds .tmp extension to a filename
     * @param path File path
     * @return modified file path
     */
    public static String buildTemporaryFilePath(String path) {
        if (!path.contains(".")) return path + ".tmp";
        int index = path.lastIndexOf(".");
        return path.substring(0, index) + ".tmp";
    }
}
