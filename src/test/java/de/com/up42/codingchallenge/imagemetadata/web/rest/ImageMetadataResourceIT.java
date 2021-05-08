package de.com.up42.codingchallenge.imagemetadata.web.rest;

import de.com.up42.codingchallenge.imagemetadata.AbstractBaseIntegrationTest;
import de.com.up42.codingchallenge.imagemetadata.IntegrationTest;
import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
@Slf4j
public class ImageMetadataResourceIT extends AbstractBaseIntegrationTest {

    @Autowired
    private AppImgMetadataServiceProperties appKitchenServiceProperties;


    @Test
    public void testSearchAllFeaturesEndpoint() throws Exception {

        MockHttpServletResponse mockHttpServletResponse;
        List<FeatureResponseDTO> featuresFoundList;

        mockHttpServletResponse = this.performMockMVCOperationGet(AbstractBaseIntegrationTest.APP_ENDPOINT_BASE_FEATURES,
                                                                  new String[1],
                                                                  new LinkedMultiValueMap<>());

        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

        assertNotNull(mockHttpServletResponse.getContentAsByteArray());

        featuresFoundList = this.jacksonObjectMapper.readValue(mockHttpServletResponse.getContentAsByteArray(),
                                                               ArrayList.class);

        assertNotNull(featuresFoundList);
        assertEquals(14, featuresFoundList.size());

    }

    @Test
    public void testSearchOneFeatureByIdEndpointSuccess() throws Exception {

        MockHttpServletResponse mockHttpServletResponse;
        FeatureResponseDTO theFeatureFound;
        String[] pathVariablesArray = {"b3ac34e1-12e6-4dee-9e21-b717f472fcfd"};

        mockHttpServletResponse = this.performMockMVCOperationGet(AbstractBaseIntegrationTest.APP_ENDPOINT_FEATURES_BY_ID,
                                                                  pathVariablesArray,
                                                                  new LinkedMultiValueMap<>());

        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

        assertNotNull(mockHttpServletResponse.getContentAsByteArray());

        theFeatureFound = this.jacksonObjectMapper.readValue(mockHttpServletResponse.getContentAsByteArray(),
                                                             FeatureResponseDTO.class);

        assertNotNull(theFeatureFound);
        assertEquals("Sentinel-1B", theFeatureFound.getMissionName());

    }

    @Test
    public void testSearchOneFeatureByIdEndpointError404() throws Exception {

        MockHttpServletResponse mockHttpServletResponse;
        String[] pathVariablesArray = {"b3ac34e1-12e6-4dee-9e21-NonExistentID"};

        mockHttpServletResponse = this.performMockMVCOperationGet(AbstractBaseIntegrationTest.APP_ENDPOINT_FEATURES_BY_ID,
                                                                  pathVariablesArray,
                                                                  new LinkedMultiValueMap<>());

        assertEquals(HttpStatus.NOT_FOUND.value(), mockHttpServletResponse.getStatus());

    }

    @Test
    public void testShowQuicklookImageAsPNG() throws Exception {

        MockHttpServletResponse mockHttpServletResponse;
        FeatureResponseDTO theFeatureFound;
        String[] pathVariablesArray = {"b3ac34e1-12e6-4dee-9e21-b717f472fcfd"};

        mockHttpServletResponse = this.performMockMVCOperationGet(AbstractBaseIntegrationTest.APP_ENDPOINT_SHOW_QUICKLOOK_IMAGE_AS_PNG,
                                                                  pathVariablesArray,
                                                                  new LinkedMultiValueMap<>());

        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

        assertEquals(MediaType.IMAGE_PNG_VALUE, mockHttpServletResponse.getContentType());

        assertNotNull(mockHttpServletResponse.getContentAsByteArray());

    }

}
