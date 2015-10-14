package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author Miguel Reyes
 *         Date: 10/13/15
 *         Time: 7:20 PM
 * SpringBootApplication is a convenience annotation that adds all of the following:
 * Configuration tags the class as a source of bean definitions for the application context.
 * EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
 * ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the GreetingController
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * Enables traditional deployment to a Tomcat container. Remove 'extends' and this method if container deployment is not needed.
     * http://docs.spring.io/spring-boot/docs/current/reference/html/howto-traditional-deployment.html
     *
     * @param application SpringApplicationBuilder
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
