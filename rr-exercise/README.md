# Prices interfaces implementation

Write a paragraph about the thinking that went into each method. Did you make compromises? Were they in favor of the owner, or were they in favor of the renter?

In this code I'm implementing the interfaces in ```PricingService``` class. The thinking behind each method implementation is as follows:
## Basic Rate Pricer

```public BasicQuote getQuote(ZonedDateTime start, ZonedDateTime end, BasicRate rate)```

This Method assumes that the rates to be used are present in the *BasicRate* object. A validation should be made in advance to guarantee that the values are adequate, but this is not part of the scope.

I compromised the Quote to benefit the **Renter**. The more days that are part of the time interval, the cheaper it will become to the Renter. I see the price in different "levels", once the Renters chooses more than 6 days, the total price will be calculated on the weekly level. When a full month is reached, the price will be calculated according to the monthly level.
 

## Pricer With Duration Discounts

``` public BasicQuote getQuote(ZonedDateTime start, ZonedDateTime end, RateWithDurationDiscounts rate) ```

This method assumes that the discount rates are present in *RateWithDurationDiscounts*. A validation should be made in advance to guarantee that the values are adequate, but this is not part of the scope.

The Quote will be calculated using this discount rates. Results of the multiplied BigDecimals are rounded with two (2) decimals and *RoundingMode.CEILING*. Sames as with the other method, the prices are calculated on reachable *levels* (daily, weekly or monthly).