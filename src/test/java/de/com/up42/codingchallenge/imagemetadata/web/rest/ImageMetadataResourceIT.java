package de.com.up42.codingchallenge.imagemetadata.web.rest;

import de.com.up42.codingchallenge.imagemetadata.AbstractBaseIntegrationTest;
import de.com.up42.codingchallenge.imagemetadata.IntegrationTest;
import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

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

        Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

        Assertions.assertNotNull(mockHttpServletResponse.getContentAsByteArray());

        featuresFoundList = this.jacksonObjectMapper.readValue(mockHttpServletResponse.getContentAsByteArray(),
                                                               ArrayList.class);

        Assertions.assertNotNull(featuresFoundList);
        Assertions.assertTrue(featuresFoundList.size() == 14);

    }

}
