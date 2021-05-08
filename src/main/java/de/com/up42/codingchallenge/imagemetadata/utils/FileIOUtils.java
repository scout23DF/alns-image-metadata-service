package de.com.up42.codingchallenge.imagemetadata.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileIOUtils {

    public static String getResourceFileAsString(String fileName) {

        InputStream is = getResourceFileAsInputStream(fileName);

        if (is != null) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String) reader.lines()
                                  .collect(Collectors.joining(System.lineSeparator()));

        } else {
            throw new RuntimeException("Resource not found: " + fileName);
        }
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = FileIOUtils.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

}
