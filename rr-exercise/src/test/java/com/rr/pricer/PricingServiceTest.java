package com.rr.pricer;

import com.rr.pricer.models.BasicQuote;
import com.rr.pricer.models.BasicRate;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PricingServiceTest {

    public static final BigDecimal BASIC_RATE_DAILY = new BigDecimal(50);
    public static final BigDecimal BASIC_RATE_WEEKLY = new BigDecimal(250);
    public static final BigDecimal BASIC_RATE_MONTHLY = new BigDecimal(1500);

    private PricingService pricingService;

    @Before
    public void setup() {
        pricingService = new PricingService();
    }

    @Test
    public void testGetQuoteOneDay() throws Exception {
        //Get remaining hours until the end of the day.
//        ZonedDateTime start = ZonedDateTime.now();
//        int remainingHoursToday = 23 - start.getHour();
//        ZonedDateTime end = start.plusHours(remainingHoursToday);

        DateTimeFormatter f = DateTimeFormatter.ISO_DATE_TIME;
        ZonedDateTime start = ZonedDateTime.parse("2015-09-28T00:00:00.000Z", f);
        ZonedDateTime end = ZonedDateTime.parse("2015-09-28T23:59:00.000Z", f);

        BasicRate basicRate = new BasicRate(BASIC_RATE_DAILY, BASIC_RATE_WEEKLY, BASIC_RATE_MONTHLY);
        BasicQuote quote = pricingService.getQuote(start, end, basicRate);

        assertNotNull(quote);
        assertEquals(BASIC_RATE_DAILY, quote.renterCost);
        assertEquals(0, BASIC_RATE_DAILY.multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                .compareTo(quote.ownerEarnings));
    }

    @Test
    public void testGetQuoteTwoDays() throws Exception {
        BigDecimal days = new BigDecimal(2);
        ZonedDateTime start = ZonedDateTime.now();

        BasicRate basicRate = new BasicRate(BASIC_RATE_DAILY, BASIC_RATE_WEEKLY, BASIC_RATE_MONTHLY);
        BasicQuote quote = pricingService.getQuote(start, start.plusDays(days.intValue() - 1), basicRate);

        assertNotNull(quote);
        assertEquals(BASIC_RATE_DAILY.multiply(days), quote.renterCost);
        assertEquals(0, BASIC_RATE_DAILY.multiply(days.multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE))
                .compareTo(quote.ownerEarnings));
    }

    @Test
    public void testGetQuoteLessThanAWeek() {
        BigDecimal days = new BigDecimal(6);
        ZonedDateTime start = ZonedDateTime.now();
        ZonedDateTime end = start.plusDays(days.intValue() - 1);

        BasicRate basicRate = new BasicRate(BASIC_RATE_DAILY, BASIC_RATE_WEEKLY, BASIC_RATE_MONTHLY);
        BasicQuote quote = pricingService.getQuote(start, end, basicRate);

        assertNotNull(quote);
        assertEquals(BASIC_RATE_DAILY.multiply(days), quote.renterCost);
        assertEquals(0, BASIC_RATE_DAILY.multiply(days).multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                .compareTo(quote.ownerEarnings));

    }

    @Test
    public void testGetQuoteWeekly() throws Exception {
        Double days = (double) 10;
        ZonedDateTime start = ZonedDateTime.now();
        ZonedDateTime end = start.plusDays(days.intValue() - 1);

        BasicRate basicRate = new BasicRate(BASIC_RATE_DAILY, BASIC_RATE_WEEKLY, BASIC_RATE_MONTHLY);
        BasicQuote quote = pricingService.getQuote(start, end, basicRate);

        BigDecimal fractionOfAWeek = new BigDecimal(days / (double) 7).setScale(2, RoundingMode.CEILING);

        assertNotNull(quote);
        assertEquals(BASIC_RATE_WEEKLY.multiply(fractionOfAWeek), quote.renterCost);
        assertEquals(0, BASIC_RATE_WEEKLY.multiply(fractionOfAWeek)
                .multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                .compareTo(quote.ownerEarnings));
    }

    @Test
    public void testGetQuoteMonthly() throws Exception {
        Double days = (double) 30;
        ZonedDateTime start = ZonedDateTime.now();
        ZonedDateTime end = start.plusDays(days.intValue() - 1);

        BasicRate basicRate = new BasicRate(BASIC_RATE_DAILY, BASIC_RATE_WEEKLY, BASIC_RATE_MONTHLY);
        BasicQuote quote = pricingService.getQuote(start, end, basicRate);

        BigDecimal fractionOfAMonth = new BigDecimal( days / (double) 30).setScale(2, RoundingMode.CEILING);

        assertNotNull(quote);
        assertEquals(BASIC_RATE_MONTHLY.multiply(fractionOfAMonth), quote.renterCost);
        assertEquals(0, BASIC_RATE_MONTHLY.multiply(fractionOfAMonth).multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                .compareTo(quote.ownerEarnings));
    }

}