package com.cabfinder.asynchandler;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Miguel Reyes
 *         Date: 26/01/14
 *         Time: 18:42
 */
public class CabPositionChangedAsyncHandler implements AsyncHandler<CabPositionChangedResponse> {

    private CabPositionChangedResponse response;

    @Override
    public void handleResponse(Response<CabPositionChangedResponse> res) {
        try {
            response = res.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public CabPositionChangedResponse getResponse() {
        return response;
    }
}
