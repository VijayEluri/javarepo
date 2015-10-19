package com.rr.pricer.models;

import java.math.BigDecimal;

public class RateWithDurationDiscounts {
    public BigDecimal daily;

    // Percentage discount for a weekly rental, expressed as a decimal between 0 and 1
    public BigDecimal weeklyDiscount;
    // Percentage discount for a monthly rental, expressed as a decimal between 0 and 1
    public BigDecimal monthlyDiscount;

    public RateWithDurationDiscounts(BigDecimal daily, BigDecimal weeklyDiscount, BigDecimal monthlyDiscount) {
        this.daily = daily;
        this.weeklyDiscount = weeklyDiscount;
        this.monthlyDiscount = monthlyDiscount;
    }
}
