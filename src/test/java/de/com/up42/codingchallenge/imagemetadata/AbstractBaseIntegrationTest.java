package de.com.up42.codingchallenge.imagemetadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.com.up42.codingchallenge.imagemetadata.repositories.ImageMetadataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

@IntegrationTest
@Slf4j
public abstract class AbstractBaseIntegrationTest {

    public static final String APP_ENDPOINT_ROOT = "/api";
    public static final String APP_ENDPOINT_VERSION = APP_ENDPOINT_ROOT + "";
    public static final String APP_ENDPOINT_BASE_STAFFMEMBERS = APP_ENDPOINT_VERSION + "/staff-members";
    public static final String APP_ENDPOINT_BASE_WEEKPLANS = APP_ENDPOINT_VERSION + "/week-plans";


    @Autowired
    protected MockMvc mockMVC;

    @Autowired
    protected ObjectMapper jacksonObjectMapper;

    @Autowired
    protected ImageMetadataRepository imageMetadataRepository;


    protected MockHttpServletResponse performMockMVCOperationGet(String pEndPointURL,
                                                                 Object[] pPathVariablesValuesArray,
                                                                 MultiValueMap<String, String> pRequestParamsMap) throws Exception {
        MockHttpServletResponse mockHttpServletResponse;
        RequestBuilder requestBuilder;
        MvcResult mvcResult;

        requestBuilder = MockMvcRequestBuilders.get(pEndPointURL, pPathVariablesValuesArray)
                                               .params(pRequestParamsMap)
                                               .contentType(MediaType.APPLICATION_JSON);

        mvcResult = mockMVC.perform(requestBuilder).andReturn();
        mockHttpServletResponse = mvcResult.getResponse();

        return mockHttpServletResponse;
    }


    protected MockHttpServletResponse performMockMVCOperationPost(String pEndPointURL,
                                                                  Object[] pPathVariablesValuesArray,
                                                                  Object pPayloadToPostInRequestBody,
                                                                  MultiValueMap<String, String> pRequestParamsMap) throws Exception {

        MockHttpServletResponse mockHttpServletResponse;
        RequestBuilder requestBuilder;
        MvcResult mvcResult;
        String strPayloadBodyAsJSON;

        strPayloadBodyAsJSON = this.jacksonObjectMapper.writeValueAsString(pPayloadToPostInRequestBody);

        requestBuilder = MockMvcRequestBuilders.post(pEndPointURL, pPathVariablesValuesArray)
                                               .params(pRequestParamsMap)
                                               .content(strPayloadBodyAsJSON)
                                               .contentType(MediaType.APPLICATION_JSON);

        mvcResult = mockMVC.perform(requestBuilder).andReturn();
        mockHttpServletResponse = mvcResult.getResponse();

        return mockHttpServletResponse;

    }
}
