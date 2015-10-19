package com.rr.pricer;

import com.rr.pricer.models.BasicQuote;
import com.rr.pricer.models.RateWithDurationDiscounts;

import java.time.ZonedDateTime;

public interface PricerWithDurationDiscounts {
	
    BasicQuote getQuote(ZonedDateTime start, ZonedDateTime end, RateWithDurationDiscounts rate);
}
