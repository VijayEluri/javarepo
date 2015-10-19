package com.rr.pricer;

import com.rr.pricer.models.BasicQuote;
import com.rr.pricer.models.RateWithDurationDiscounts;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PricingServiceWithDurationDiscountTest {

    private final BigDecimal DAILY = new BigDecimal(50);
    private final BigDecimal WEEKLY_DISCOUNT = new BigDecimal(0.1);
    private final BigDecimal MONTHLY_DISCOUNT = new BigDecimal(0.5);

    private PricingService pricingService;
    private RateWithDurationDiscounts rateWithDurationDiscounts;

    @Before
    public void setup() {
        pricingService = new PricingService();
        rateWithDurationDiscounts = new RateWithDurationDiscounts(DAILY, WEEKLY_DISCOUNT, MONTHLY_DISCOUNT);
    }


    @Test
    public void testGetQuoteOneDay() throws Exception {
        DateTimeFormatter f = DateTimeFormatter.ISO_DATE_TIME;
        ZonedDateTime start = ZonedDateTime.parse("2015-09-28T00:00:00.000Z", f);
        ZonedDateTime end = ZonedDateTime.parse("2015-09-28T23:59:00.000Z", f);

        BasicQuote quote = pricingService.getQuote(start, end, rateWithDurationDiscounts);

        assertNotNull(quote);
    }

    @Test
    public void testGetQuoteTwoDays() throws Exception {
        BigDecimal days = new BigDecimal(2);
        ZonedDateTime start = ZonedDateTime.now();

        BasicQuote quote = pricingService.getQuote(start, start.plusDays(days.intValue() - 1), rateWithDurationDiscounts);

        assertNotNull(quote);
        assertEquals(DAILY.multiply(days), quote.renterCost);
        assertEquals(0, DAILY.multiply(days.multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE))
                .compareTo(quote.ownerEarnings));
    }

    @Test
    public void testGetQuoteOneWeek() throws Exception {
        BigDecimal days = new BigDecimal(7);
        ZonedDateTime start = ZonedDateTime.now();

        BasicQuote quote = pricingService.getQuote(start, start.plusDays(days.intValue() - 1), rateWithDurationDiscounts);

        assertNotNull(quote);
        assertEquals(DAILY.multiply(days)
                .multiply(new BigDecimal(1).subtract(WEEKLY_DISCOUNT))
                .setScale(2, RoundingMode.CEILING), quote.renterCost);
        assertEquals(0, DAILY.multiply(days)
                .multiply(new BigDecimal(1).subtract(WEEKLY_DISCOUNT))
                .multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                .setScale(2, RoundingMode.CEILING)
                .compareTo(quote.ownerEarnings));
    }

    @Test
    public void testGetQuoteOneMonth() throws Exception {
        BigDecimal days = new BigDecimal(30);
        ZonedDateTime start = ZonedDateTime.now();

        BasicQuote quote = pricingService.getQuote(start, start.plusDays(days.intValue() - 1), rateWithDurationDiscounts);

        assertNotNull(quote);
        assertEquals(DAILY.multiply(days)
                .multiply(new BigDecimal(1).subtract(MONTHLY_DISCOUNT))
                .setScale(2, RoundingMode.CEILING), quote.renterCost);
        assertEquals(0, DAILY.multiply(days)
                .multiply(new BigDecimal(1).subtract(MONTHLY_DISCOUNT))
                .multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                .setScale(2, RoundingMode.CEILING)
                .compareTo(quote.ownerEarnings));
    }

}