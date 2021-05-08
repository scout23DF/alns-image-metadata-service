package de.com.up42.codingchallenge.imagemetadata.services.impl;

import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import de.com.up42.codingchallenge.imagemetadata.repositories.ImageMetadataRepository;
import de.com.up42.codingchallenge.imagemetadata.services.ImageMetadataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageMetadataServiceTest {

    @Mock
    private AppImgMetadataServiceProperties appKitchenServiceProperties;

    @Mock
    private ImageMetadataRepository imageMetadataRepository;


    @Autowired
    private final ImageMetadataService imageMetadataService = new ImageMetadataServiceImpl(appKitchenServiceProperties,
                                                                                           imageMetadataRepository);

    @Test
    public void testSomething1() {

        assertEquals("2025-02-10", "2025-02-10");

    }

}