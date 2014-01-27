package com.cabfinder;

import com.cabfinder.*;
import com.cabfinder.asynchandler.CabAvailabilityChangedAsyncHandler;
import com.cabfinder.asynchandler.CabPositionChangedAsyncHandler;
import com.cabfinder.services.CabFinderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 *
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 17:16
 */
@RunWith(MockitoJUnitRunner.class)
public class CabFinderTest {

    private CabFinder cabFinder;

    @Mock
    Cab cab1, cab2, cab3;

    @Mock
    CabApp cabApp;

    @Mock
    CabStatusListener cabStatusListener;

    @Mock
    private CabFinderService cabFinderService;


    @Before
    public void before() {
        System.out.println("### Before");

        cabFinder = new CabFinder();
        cabFinder.initialize(cabApp, (double)10);

        cabFinder.setCabFinderService(cabFinderService);

    }

    @Test
    public void testGetNearestCabs() throws Exception {
        System.out.println("testGetNearestCabs");
        Set<Cab> cabs = new HashSet<Cab>();
        cabs.add(cab1);
        cabs.add(cab2);
        cabs.add(cab3);

        Position position1 = new Position(700, 700);
        Position position2 = new Position(1000, 1000);
        Position position3 = new Position(2000, 2000);

        Position userPosition = new Position(500, 500);

        when(cabApp.getUserPosition()).thenReturn(userPosition);
        when(cabApp.getCabs()).thenReturn(cabs.iterator());

        //Cabs within area:
        when(cab1.getCabPosition()).thenReturn(position1);
        when(cab1.isAvailable()).thenReturn(true);
        when(cab2.getCabPosition()).thenReturn(position2);
        when(cab2.isAvailable()).thenReturn(true);

        //Cab outside area:
        when(cab3.getCabPosition()).thenReturn(position3);
        when(cab3.isAvailable()).thenReturn(true);

        when(cabApp.getUserPosition()).thenReturn(userPosition);


        assertEquals(2, cabFinder.getNearestCabs().length);

    }

    @Test
    public void testOnCabPositionChanged() throws Exception {
        System.out.println("testOnCabPositionChanged");

        CabPositionChangedAsyncHandler cabPositionChangedAsyncHandler = mock(CabPositionChangedAsyncHandler.class);
        cabFinder.setCabPositionChangedAsyncHandler(cabPositionChangedAsyncHandler);

        cabFinder.onCabPositionChanged(cab1);

        verify(cabFinderService).updateCabPosition(cab1, cabPositionChangedAsyncHandler);

    }

    @Test
    public void testOnCabAvailabilityChanged() throws Exception {
        System.out.println("testOnCabAvailabilityChanged");
        CabAvailabilityChangedAsyncHandler cabAvailabilityChangedAsyncHandler = mock(CabAvailabilityChangedAsyncHandler.class);
        cabFinder.setCabAvailabilityChangedAsyncHandler(cabAvailabilityChangedAsyncHandler);

        cabFinder.onCabAvailabilityChanged(cab1, true);

        verify(cabFinderService).updateCabAvailability(cab1, true, cabAvailabilityChangedAsyncHandler);


    }
}
