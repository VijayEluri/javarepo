/*
 * HostPrecedingPropertyMookie.java
 *
 * Created on Oct 15, 2007, 2:00:08 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author miguelreyes
 */
public class HostPrecedingPropertyReader {
    
    private static final Log log = LogFactory.getLog(HostPrecedingPropertyReader.class);

    private Properties myProperties;

    /**
     * Method to read properties from the file /application.properties
     *
     * @param propertyToRead The property to read no need to worry about the server
     * @return Value of the property read according to the server deployed
     */
    public String getProperty(String propertyToRead) {
        myProperties = new Properties();

        try {
            InputStream is = this.getClass().getResourceAsStream("/applicationContext-netquiz.properties");
            myProperties.load(is);
            String nodeSpecificParam = InetAddress.getLocalHost().getHostName() + "_" + propertyToRead;
            // look for param with the hostname_paramName in props;
            if (myProperties.getProperty(nodeSpecificParam) != null) {
                return myProperties.getProperty(nodeSpecificParam);
            } else if (myProperties.getProperty(propertyToRead) != null) {
                return myProperties.getProperty(propertyToRead);
            } else {
                log.error("Cant find property, neither for " + nodeSpecificParam + " or " + propertyToRead + " please add to properties file.");
            }
            return myProperties.getProperty(propertyToRead); // this is like returning null
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}