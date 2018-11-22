package com.plotnikowski.bibparser;

import java.io.File;

/**
 * Class used to build a new path to a file if file is generated (redundant for now on)
 */
public class FilePathBuilder {
    /**
     * Function adds .tmp extension to a filename
     * @param path File path
     * @return modified file path
     */
    public static String buildTemporaryFilePath(String path) {
        if (!path.contains(".")) return path + ".tmp";
        int index = path.lastIndexOf(".");
        return path.substring(0, index) + ".tmp";
    }
}
