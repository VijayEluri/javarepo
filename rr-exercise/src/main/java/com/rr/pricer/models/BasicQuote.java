package com.rr.pricer.models;

import java.math.BigDecimal;

public class BasicQuote {

    public static final BigDecimal OWNER_EARNINGS_PERCENTAGE = new BigDecimal(0.75);

    public BigDecimal renterCost;
    // Owners earn 75% of the trip cost
    public BigDecimal ownerEarnings;
}
