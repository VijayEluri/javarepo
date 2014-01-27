package com.cabfinder.services;

import com.cabfinder.Cab;
import com.cabfinder.asynchandler.CabAvailabilityChangedAsyncHandler;
import com.cabfinder.asynchandler.CabPositionChangedAsyncHandler;

import java.util.concurrent.Future;

/**
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 19:03
 */
public class CabFinderServiceImpl implements CabFinderService{

    private static final int NTHREDS = 10;

    @Override
    public Future<String> updateCabPosition(Cab cab, CabPositionChangedAsyncHandler callbackHandler) {
        //TODO Implement handling of location update
        return null;
    }

    @Override
    public Future<String> updateCabAvailability(Cab cab, boolean isAvailable, CabAvailabilityChangedAsyncHandler cabAvailabilityChangedAsyncHandler) {
        //TODO Implement handling of Availability update
        return null;
    }
}
