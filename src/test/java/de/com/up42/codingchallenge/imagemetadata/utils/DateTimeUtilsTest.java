package de.com.up42.codingchallenge.imagemetadata.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilsTest {

    @Test
    public void checkWeekContent() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date[] daysOfCurrentWeek = DateTimeUtils.getDaysOfWeekByReferenceDate(sdf.parse("2020-12-14"));
        assertEquals("2020-12-14", sdf.format(daysOfCurrentWeek[0]));
        assertEquals("2020-12-15", sdf.format(daysOfCurrentWeek[1]));
        assertEquals("2020-12-16", sdf.format(daysOfCurrentWeek[2]));
        assertEquals("2020-12-17", sdf.format(daysOfCurrentWeek[3]));
        assertEquals("2020-12-18", sdf.format(daysOfCurrentWeek[4]));
    }

    @Test
    public void checkWeekEndOfYearContent() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date[] daysOfCurrentWeek = DateTimeUtils.getDaysOfWeekByReferenceDate(sdf.parse("2020-01-01"));
        assertEquals("2019-12-30", sdf.format(daysOfCurrentWeek[0]));
        assertEquals("2019-12-31", sdf.format(daysOfCurrentWeek[1]));
        assertEquals("2020-01-01", sdf.format(daysOfCurrentWeek[2]));
        assertEquals("2020-01-02", sdf.format(daysOfCurrentWeek[3]));
        assertEquals("2020-01-03", sdf.format(daysOfCurrentWeek[4]));
    }

    @Test
    public void checkWeekInFuture() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date[] daysOfCurrentWeek = DateTimeUtils.getDaysOfWeekByReferenceDate(sdf.parse("2025-02-16"));
        assertEquals("2025-02-10", sdf.format(daysOfCurrentWeek[0]));
        assertEquals("2025-02-11", sdf.format(daysOfCurrentWeek[1]));
        assertEquals("2025-02-12", sdf.format(daysOfCurrentWeek[2]));
        assertEquals("2025-02-13", sdf.format(daysOfCurrentWeek[3]));
        assertEquals("2025-02-14", sdf.format(daysOfCurrentWeek[4]));
    }

    @Test
    public void checkSize() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date[] daysOfCurrentWeek = DateTimeUtils.getDaysOfWeekByReferenceDate(sdf.parse("2020-01-01"));
        assertEquals(5, daysOfCurrentWeek.length);

    }

    @Test
    public void checkWeekDesiredIsInAllowedRangeInPast() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Boolean bolResultSameDay = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-04-26"), 6);
        assertEquals(bolResultSameDay, Boolean.TRUE);

        Boolean bolResultMinus3Days = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-04-23"), 6);
        assertEquals(bolResultMinus3Days, Boolean.TRUE);

        Boolean bolResultMinus4Weeks = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-03-31"), 6);
        assertEquals(bolResultMinus4Weeks, Boolean.TRUE);

        Boolean bolResultMinus6Weeks = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-03-18"), 6);
        assertEquals(bolResultMinus6Weeks, Boolean.TRUE);

        Boolean bolResultMinus7Weeks = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-03-11"), 6);
        assertEquals(bolResultMinus7Weeks, Boolean.FALSE);

        Boolean bolResultMinus3Months = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-01-26"), 6);
        assertEquals(bolResultMinus3Months, Boolean.FALSE);

    }

    @Test
    public void checkWeekDesiredIsInAllowedRangeInFuture() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Boolean bolResultPlus3Days = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-04-29"), 6);
        assertEquals(bolResultPlus3Days, Boolean.TRUE);

        Boolean bolResultPlus4Weeks = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-05-27"), 6);
        assertEquals(bolResultPlus4Weeks, Boolean.TRUE);

        Boolean bolResultPlus6Weeks = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-06-10"), 6);
        assertEquals(bolResultPlus6Weeks, Boolean.TRUE);

        Boolean bolResultPlus7Weeks = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-06-18"), 6);
        assertEquals(bolResultPlus7Weeks, Boolean.FALSE);

        Boolean bolResultPlus3Months = DateTimeUtils.weekDesiredIsInAllowedRange(sdf.parse("2021-04-26"), sdf.parse("2021-07-29"), 6);
        assertEquals(bolResultPlus3Months, Boolean.FALSE);

    }

}