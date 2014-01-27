package com.cabfinder.asynchandler;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.concurrent.ExecutionException;

/**
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 19:27
 */
public class CabAvailabilityChangedAsyncHandler implements AsyncHandler<CabAvailabilityChangedResponse> {

    private CabAvailabilityChangedResponse response;

    @Override
    public void handleResponse(Response<CabAvailabilityChangedResponse> res) {
        try {
            response = res.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public CabAvailabilityChangedResponse getResponse() {
        return response;
    }
}
