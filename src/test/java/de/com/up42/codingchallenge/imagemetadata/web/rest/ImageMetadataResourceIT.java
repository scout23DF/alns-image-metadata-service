package de.com.up42.codingchallenge.imagemetadata.web.rest;

import de.com.up42.codingchallenge.imagemetadata.AbstractBaseIntegrationTest;
import de.com.up42.codingchallenge.imagemetadata.IntegrationTest;
import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
@Slf4j
public class ImageMetadataResourceIT extends AbstractBaseIntegrationTest {

    @Autowired
    private AppImgMetadataServiceProperties appKitchenServiceProperties;


    /*
    @Test
    public void testPlanWeekFromRequestDTO() throws Exception {

        WeekPlanRequestDTO weekPlanRequestDTO = null;
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        MockHttpServletResponse mockHttpServletResponse;
        List<DayOfService> dayOfServiceList;
        Integer numberOfWeeksAllowedInPlanning = appKitchenServiceProperties.getNumberOfWeeksAllowedInPlanning();

        weekPlanRequestDTO = new WeekPlanRequestDTO();
        weekPlanRequestDTO.setReferenceDate(DateTimeUtils.convertDateToString((new Date()), "yyyy-MM-dd"));
        weekPlanRequestDTO.setQtyWeeksAheadToPlan(numberOfWeeksAllowedInPlanning);
        weekPlanRequestDTO.setShouldConsiderVacationDays(Boolean.TRUE);
        weekPlanRequestDTO.setShouldRegenerateAllDays(Boolean.TRUE);

        mockHttpServletResponse = this.performMockMVCOperationPost(AbstractBaseIntegrationTest.APP_ENDPOINT_BASE_WEEKPLANS,
                                                                   new String[1],
                                                                   weekPlanRequestDTO,
                                                                   new LinkedMultiValueMap<>());

        Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

        Assertions.assertNotNull(mockHttpServletResponse.getContentAsByteArray());

        dayOfServiceList = this.jacksonObjectMapper.readValue(mockHttpServletResponse.getContentAsByteArray(),
                                                              ArrayList.class);

        Assertions.assertNotNull(dayOfServiceList);
        Assertions.assertTrue(dayOfServiceList.size() == (numberOfWeeksAllowedInPlanning * 5));

    }
    */
}
