package de.com.up42.codingchallenge.imagemetadata.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileIOUtilsTest {

    @Test
    public void testGetResourceFileAsString() {
        String fileName = "fake-data/image-source-data-test.json";

        String fileContents = FileIOUtils.getResourceFileAsString(fileName);

        assertNotNull(fileContents);
        assertEquals(Boolean.TRUE, (fileContents.length() > 0));

    }

}