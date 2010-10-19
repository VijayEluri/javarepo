package se.netlight.netquiz.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * HostPrecedingPropertyPlaceholderConfigurer
 * 
 * Extends PropertyPlaceholderConfigurer to insert $hostname.property if
 * the property name starts with HOST
 * sample properties file:
 * 
 *  jdbc.user=live_user
 *  server.jdbc.url=jdbc:postgresql://db.host.com:5432/db
 *  server.magic.file.location=/var/magic_file
 *
 *  jdbc.user=devel_user
 *  devel.jdbc.url=jdbc:postgresql://devel-db.host.com:5432/db
 *  devel.magic.file.location=c:\\var\magic_file
 *    
 * my.property=a property referenced through a method besides
 * HostPrecedingPropertyPlaceholderConfigurer
 * 
 *  <bean id="propertyConfigurer"
 *  class="com.util.spring.HostPrecedingPropertyPlaceholderConfigurer">
 *  <property name="location" value="classpath:config.properties" />
 * </bean>
 *
 * <bean id="dataSource"
 *  class="com.mchange.v2.c3p0.ComboPooledDataSource"
 *  destroy-method="close">
 *  <property name="driverClass" value="${jdbc.driverClass}" />
 *  <property name="jdbcUrl" value="${HOST.jdbc.url}" /><!--Do a host lookup!-->
 *  <property name="user" value="${jdbc.user}" />
 *  <property name="password" value="${jdbc.password}" />  
 * </bean>
 * 
 * 
 * @author Jeff Dwyer (blog) http://jdwyah.blogspot.com
 *
 */
public class HostPrecedingPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final Log LOG = LogFactory.getLog(HostPrecedingPropertyPlaceholderConfigurer.class);

    /**
     * resolvePlaceholder will first look for a hostname parameter ,if not found it looks for a property without the hostname infront. 
     */
    protected String resolvePlaceholder(String placeholder, Properties props) {

        try {

            String nodeSpecificParam = InetAddress.getLocalHost().getHostName() + "_" + placeholder;
            // look for param with the hostname_paramName in props;
            if (props.getProperty(nodeSpecificParam) != null) {
                return props.getProperty(nodeSpecificParam);
            } //           if no hostname found, look for the param without hostname,  
            else if (props.getProperty(placeholder) != null) {
                return props.getProperty(placeholder);
            } // if neither is found output an error.
            else {
                LOG.error("Cant find property, neither for " + nodeSpecificParam +
                        " or " + placeholder + " please add to properties file.");
            }
            return props.getProperty(placeholder); // this is like returning null

        } catch (UnknownHostException e) {
            LOG.warn(e);
            return null;
        }
    }
}
