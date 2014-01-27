package com.cabfinder.services;

import com.cabfinder.Cab;
import com.cabfinder.asynchandler.CabAvailabilityChangedAsyncHandler;
import com.cabfinder.asynchandler.CabPositionChangedAsyncHandler;

import java.util.concurrent.Future;

/**
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 19:02
 */
public interface CabFinderService {

    Future<String> updateCabPosition(Cab position, CabPositionChangedAsyncHandler callbackHandler);

    Future<String> updateCabAvailability(Cab cab, boolean isAvailable, CabAvailabilityChangedAsyncHandler cabAvailabilityChangedAsyncHandler);

}
