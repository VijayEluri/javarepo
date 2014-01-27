package com.cabfinder;

import com.cabfinder.asynchandler.CabAvailabilityChangedAsyncHandler;
import com.cabfinder.asynchandler.CabPositionChangedAsyncHandler;
import com.cabfinder.services.CabFinderService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 16:27
 */
@Singleton
@Startup
public class CabFinder implements CabStatusListener {

    @Inject
    private CabApp cabApp;

    /**
     * There are a maximum number of cabs that can be returned to the user at any time
     */
    private Double maxCabs = (double) 10;

    private CabPositionChangedAsyncHandler cabPositionChangedAsyncHandler;
    private CabAvailabilityChangedAsyncHandler cabAvailabilityChangedAsyncHandler;

    @Inject
    private CabFinderService cabFinderService;

    @PostConstruct
    public void init() {
        initialize(cabApp, maxCabs);
        cabApp.register(this);
    }

    /**
     * Initiates CabFinder. Called only once per app startup.
     *
     * @param app     An application object providing services implemented by
     *                the rest of the application.
     * @param maxCabs Nearest number of cabs that can be returned to the user
     */
    public void initialize(CabApp app, Double maxCabs) {
        this.cabApp = app;
        this.maxCabs = maxCabs;
    }

    /**
     * Gets nearest cabs within 1km of the current user’s location.
     * These must be the *nearest possible* @maxCabs in the 1km area.
     *
     * @return An unordered list of the nearest cabs.
     */
    public Cab[] getNearestCabs() {
        Position userPosition = cabApp.getUserPosition();

        TreeMap<Double, Cab> cabsWithinLimits = new TreeMap<Double, Cab>();

        Iterator<Cab> cabs = cabApp.getCabs();
        while (cabs.hasNext()) {
            Cab cab = cabs.next();
            double cabPositionX = cab.getCabPosition().getX();
            double cabPositionY = cab.getCabPosition().getY();
            double userPositionX = userPosition.getX();
            double userPositionY = userPosition.getY();
            if (cab.isAvailable()) {
                if (isCabWithinLimits(cabPositionX, cabPositionY, userPositionX, userPositionY)) {
                    cabsWithinLimits.put(distanceToCab(cabPositionX - userPositionX, cabPositionY - userPositionY), cab);
                }
            }
        }

        return getMaximumNumberOfCabs(cabsWithinLimits);
    }

    /**
     * Find the first 10 according to maxCabs and return array of the values (Cab objects)
     *
     * @param cabsWithinLimits Complete list of Cabs Found
     * @return Array with up to the maximum number of Cabs.
     */
    private Cab[] getMaximumNumberOfCabs(TreeMap<Double, Cab> cabsWithinLimits) {
        Collection<Cab> cabsWithinLimitsCollection = cabsWithinLimits.values();
        Iterator<Cab> it = cabsWithinLimitsCollection.iterator();

        Cab[] cabsFound = new Cab[cabsWithinLimitsCollection.size()];

        int count = 0;
        while(it.hasNext() && count < this.maxCabs){
            cabsFound[count] = it.next();
            count++;
        }
        return cabsFound;
    }

    /**
     * Pythagorean theorem to calculate distance to Cab
     * a^2 + o^2 = h^2
     *
     * @param adjacent Distance in X
     * @param opposite Distance in Y
     * @return Distance to taxi in meters.
     */
    private Double distanceToCab(double adjacent, double opposite) {
        return Math.sqrt(Math.pow(adjacent, 2) + Math.pow(opposite, 2));

    }

    /**
     * Radius is 1km (1000 m)
     *
     * @param cabPositionX  Cab Position in X
     * @param cabPositionY  Cab Position in Y
     * @param userPositionX User Position in X
     * @param userPositionY User Position in Y
     * @return True if cab is within limits in both X and Y
     */
    private boolean isCabWithinLimits(double cabPositionX, double cabPositionY, double userPositionX, double userPositionY) {
        /*
      X meters from the user position
     */
        int radiusX = 1000;
        /*
      Y meters form the user position
     */
        int radiusY = 1000;
        return cabPositionX < userPositionX + radiusX & cabPositionY < userPositionY + radiusY;
    }


    /**
     * Asynchronous Callback per CabStatusListener (see below). Called when the position of a cab has changed.
     */
    public void onCabPositionChanged(Cab cab) {
        cabFinderService.updateCabPosition(cab, cabPositionChangedAsyncHandler);
    }

    /**
     * Asynchronous Callback per CabStatusListener (see below). Called when a cab’s availability changes.
     *
     * @param cab         The cab whose availability has changed
     * @param isAvailable true if the cab is now available, false otherwise
     */
    public void onCabAvailabilityChanged(Cab cab, boolean isAvailable) {
        cabFinderService.updateCabAvailability(cab, isAvailable, cabAvailabilityChangedAsyncHandler);
    }


    public void setCabFinderService(CabFinderService cabFinderService) {
        this.cabFinderService = cabFinderService;
    }

    public void setCabPositionChangedAsyncHandler(CabPositionChangedAsyncHandler cabPositionChangedAsyncHandler) {
        this.cabPositionChangedAsyncHandler = cabPositionChangedAsyncHandler;
    }

    public void setCabAvailabilityChangedAsyncHandler(CabAvailabilityChangedAsyncHandler cabAvailabilityChangedAsyncHandler) {
        this.cabAvailabilityChangedAsyncHandler = cabAvailabilityChangedAsyncHandler;
    }
}
