package com.cor.hysterix.command.caching;

import java.util.ArrayList;
import java.util.List;

import com.cor.hysterix.domain.Horse;
import com.cor.hysterix.service.BettingService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * Get List of Horses in a specific race. Note, calls via this command are cached.
 */
public class CommandGetHorsesInRaceWithCaching extends HystrixCommand<List<Horse>> {

    private final BettingService service;
    private final String raceCourseId;

    /**
     * CommandGetHorsesInRaceWithCaching.
     *
     * @param service      Remote Broker Service
     * @param raceCourseId Id of race course
     */
    public CommandGetHorsesInRaceWithCaching(BettingService service, String raceCourseId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BettingServiceGroup"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("BettingServicePool")));

        this.service = service;
        this.raceCourseId = raceCourseId;
    }

    @Override
    protected List<Horse> run() {
        return service.getHorsesInRace(raceCourseId);
    }

    @Override
    protected List<Horse> getFallback() {
        // can log here, throw exception or return default
        return new ArrayList<Horse>();
    }

    /**
     * Caching can easily be added to a HystrixCommand or HystrixObserveableCommand by simply overriding the getCacheKey() method
     *
     * @return
     */
    @Override
    protected String getCacheKey() {
        return raceCourseId;
    }

}