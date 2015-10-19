package com.rr.pricer;

import com.rr.pricer.models.BasicQuote;
import com.rr.pricer.models.BasicRate;
import com.rr.pricer.models.RateWithDurationDiscounts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Miguel Reyes
 *         Date: 9/28/15
 *         Time: 11:45 AM
 */
public class PricingService implements BasicPricer, PricerWithDurationDiscounts {


    /**
     * Days are taken as FULL days. I.e. the total number of rental hours could be 24 hrs but if they land on different days, complete days are charged.
     *
     * @param start Start date for the rental
     * @param end   End date for the rental
     * @param rate  Rate used to calculate Quote
     * @return Quote with price for the given time interval and rate.
     */
    @Override
    public BasicQuote getQuote(ZonedDateTime start, ZonedDateTime end, BasicRate rate) {
        System.out.println("###############");
        System.out.println("start = " + start);
        System.out.println("end = " + end);

        //Get the closest time to the beginning of the day.
        ZonedDateTime startDate = start.minusHours(start.getHour());

        //Get the beginning of the NEXT day so that we can get the days in between
        ZonedDateTime endDate = end.plusHours(24 - end.getHour());

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("  (" + days + " days)");

        BasicQuote basicQuote = new BasicQuote();
        if (days < 7) {
            //Up to 6 days the renter pays the DAILY fee multiplied by the number of days.
            basicQuote.renterCost = rate.daily.multiply(new BigDecimal(days));
            basicQuote.ownerEarnings = basicQuote.renterCost.multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE);
        } else if (days >= 7 && days < 30) {
            //After 7 days the renter pays with the WEEKLY price, plus the fraction of the days remaining.
            BigDecimal fractionOfAWeek = new BigDecimal((double) days / (double) 7).setScale(2, RoundingMode.CEILING);
            basicQuote.renterCost = rate.weekly.multiply(fractionOfAWeek);
            basicQuote.ownerEarnings = basicQuote.renterCost.multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE);
        } else if (days >= 30) {
            //After 30 days the renter pays with the MONTHLY price, plus the fraction of the days remaining.
            BigDecimal fractionOfAMonth = new BigDecimal((double) days / (double) 30).setScale(2, RoundingMode.CEILING);
            basicQuote.renterCost = rate.monthly.multiply(fractionOfAMonth);
            basicQuote.ownerEarnings = basicQuote.renterCost.multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE);
        }
        System.out.println("  renterCost = " + basicQuote.renterCost);
        System.out.println("  ownerEarnings = " + basicQuote.ownerEarnings);
        return basicQuote;

    }

    /**
     * Days are taken as FULL days. I.e. the total number of rental hours could be 24 hrs but if they land on different days, complete days are charged.
     *
     * @param start Start date for the rental
     * @param end   End date for the rental
     * @param rate  Discount Rates used to calculate Quote
     * @return Quote with price for the given time interval and rate.
     */
    @Override
    public BasicQuote getQuote(ZonedDateTime start, ZonedDateTime end, RateWithDurationDiscounts rate) {
        System.out.println("###############");
        System.out.println("start = " + start);
        System.out.println("end = " + end);

        //Get the closest time to the beginning of the day.
        ZonedDateTime startDate = start.minusHours(start.getHour());

        //Get the beginning of the NEXT day so that we can get the days in between
        ZonedDateTime endDate = end.plusHours(24 - end.getHour());

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        BigDecimal rentalDays = new BigDecimal(days);
        System.out.println("  (" + days + " days)");

        BasicQuote basicQuote = new BasicQuote();
        if (days < 7) {
            //Up to 6 days the renter pays the daily fee multiplied.
            basicQuote.renterCost = rate.daily
                    .multiply(rentalDays);
            basicQuote.ownerEarnings = basicQuote.renterCost
                    .multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE);
        } else if (days >= 7 && days < 30) {
            //After 7 days the renter pays with the WEEKLY discount.
            basicQuote.renterCost = rate.daily
                    .multiply(rentalDays)
                    .multiply(new BigDecimal(1).subtract(rate.weeklyDiscount))
                    .setScale(2, RoundingMode.CEILING);
            basicQuote.ownerEarnings = basicQuote.renterCost
                    .multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                    .setScale(2, RoundingMode.CEILING);
        } else if (days >= 30) {
            //After 30 days the renter pays with the MONTHLY discount.
            basicQuote.renterCost = rate.daily
                    .multiply(rentalDays)
                    .multiply(new BigDecimal(1).subtract(rate.monthlyDiscount))
                    .setScale(2, RoundingMode.CEILING);
            basicQuote.ownerEarnings = basicQuote.renterCost
                    .multiply(BasicQuote.OWNER_EARNINGS_PERCENTAGE)
                    .setScale(2, RoundingMode.CEILING);
        }
        System.out.println("  renterCost = " + basicQuote.renterCost);
        System.out.println("  ownerEarnings = " + basicQuote.ownerEarnings);
        return basicQuote;
    }

}
