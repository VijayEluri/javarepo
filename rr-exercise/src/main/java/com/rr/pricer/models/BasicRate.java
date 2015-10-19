package com.rr.pricer.models;

import java.math.BigDecimal;

public class BasicRate {

    // Daily cost in dollars
    public BigDecimal daily;
    // Weekly cost in dollars
    public BigDecimal weekly;
    // Monthly cost in dollars
    public BigDecimal monthly;

    public BasicRate(BigDecimal daily, BigDecimal weekly, BigDecimal monthly) {
        this.daily = daily;
        this.weekly = weekly;
        this.monthly = monthly;
    }

}
