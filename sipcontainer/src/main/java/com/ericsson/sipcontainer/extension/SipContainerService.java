/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericsson.sipcontainer.extension;

import org.jboss.logging.Logger;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;

/**
 *
 * @author miguel
 */
public class SipContainerService implements Service<SipContainerService>{
    
    private final Logger log = Logger.getLogger(SipContainerService.class);

    @Override
    public void start(StartContext sc) throws StartException {
        log.info("SipContainerService start()");
    }

    @Override
    public void stop(StopContext sc) {
        log.info("SipContainerService stop()");
    }

    @Override
    public SipContainerService getValue() throws IllegalStateException, IllegalArgumentException {
        log.info("SipContainerService getValue()");
        return this;
    }
    
    public static ServiceName createServiceName(String suffix) {
        return ServiceName.JBOSS.append("SipContainer", suffix);
    }
    
    public void addDeployment(String name) {
//        deployments.add(name);
    }
 
    public void addCoolDeployment(String name) {
//        coolDeployments.add(name);
    }
 
    public void removeDeployment(String name) {
//        deployments.remove(name);
//        coolDeployments.remove(name);
    }
 
    void setTick(long tick) {
//        this.tick.set(tick);
    }
 
    public long getTick() {
//        return this.tick.get();
        return 0;
    }
    
}
