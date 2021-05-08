package de.com.up42.codingchallenge.imagemetadata.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    public static Date[] getDaysOfWeekByReferenceDate(Date refDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().getFirstDayOfWeek());
            calendar.add(Calendar.DAY_OF_MONTH, -6);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().getFirstDayOfWeek());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Date[] daysOfWeek = new Date[5];
        for (int i = 0; i < 5; i++) {
            daysOfWeek[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysOfWeek;
    }

    public static String convertDateToString(Date referenceDate, String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);

        return sdf.format(referenceDate);
    }

    public static Date convertStringToDate(String referenceDate, String formatPattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);

        return sdf.parse(referenceDate);
    }

    public static Boolean weekDesiredIsInAllowedRange(Date baseDate,
                                                      Date referenceDate,
                                                      Integer numberOfWeeksAllowedInPlanning) {
        Boolean bolResult = true;

        Date[] daysOfWeekBaseDate = getDaysOfWeekByReferenceDate(baseDate);
        Date[] daysOfWeekRefParamDate = getDaysOfWeekByReferenceDate(referenceDate);

        Date mondayOfBaseDate = daysOfWeekBaseDate[0];
        Date fridayOfRefParamDate = daysOfWeekRefParamDate[4];

        Calendar calendarOfBaseDate = Calendar.getInstance();
        calendarOfBaseDate.setTime(mondayOfBaseDate);
        Calendar calendarOfRefParamDate = Calendar.getInstance();
        calendarOfRefParamDate.setTime(fridayOfRefParamDate);

        if (calendarOfBaseDate.get(Calendar.WEEK_OF_YEAR) < calendarOfRefParamDate.get(Calendar.WEEK_OF_YEAR)) {

            calendarOfBaseDate.add(Calendar.WEEK_OF_YEAR, numberOfWeeksAllowedInPlanning);
            bolResult = (calendarOfRefParamDate.get(Calendar.WEEK_OF_YEAR) <= calendarOfBaseDate.get(Calendar.WEEK_OF_YEAR));

        } else if (calendarOfBaseDate.get(Calendar.WEEK_OF_YEAR) > calendarOfRefParamDate.get(Calendar.WEEK_OF_YEAR)) {

            calendarOfBaseDate.add(Calendar.WEEK_OF_YEAR, (-1 * numberOfWeeksAllowedInPlanning));
            bolResult = (calendarOfBaseDate.get(Calendar.WEEK_OF_YEAR) <= calendarOfRefParamDate.get(Calendar.WEEK_OF_YEAR));

        }

        return bolResult;

    }

    public static Date getNextMonday(Date refDate) {
        Date dateResult = refDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        dateResult = calendar.getTime();

        return dateResult;
    }

    public static List<Date> listEveryDayInRange(Date startDate, Date endDate) {

        LocalDate startLocalDate = LocalDate.from(startDate.toInstant().atZone(ZoneId.systemDefault()));
        LocalDate endLocalDate   = LocalDate.from(endDate.toInstant().atZone(ZoneId.systemDefault()));

        return Stream.iterate(startLocalDate, date -> date.plusDays(1))
                     .limit(ChronoUnit.DAYS.between(startLocalDate, endLocalDate) + 1)
                     .map(localDateToConvert -> Date.from(localDateToConvert.atStartOfDay()
                                                                            .atZone(ZoneId.systemDefault())
                                                                            .toInstant()))
                     .collect(Collectors.toList());

    }

}
