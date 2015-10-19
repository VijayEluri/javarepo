package com.rr.pricer;

import com.rr.pricer.models.BasicQuote;
import com.rr.pricer.models.BasicRate;

import java.time.ZonedDateTime;

public interface BasicPricer {
	
    BasicQuote getQuote(ZonedDateTime start, ZonedDateTime end, BasicRate rate);
}
