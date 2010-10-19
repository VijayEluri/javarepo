/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.user.action;

import com.opensymphony.xwork2.Action;
import se.netlight.netquiz.user.service.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlcleaner.HtmlCleaner;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import se.netlight.netquiz.user.model.EmailFromRebel;

/**
 *
 * @author mire
 */
public class ImportRebelUsers {

    private static Log logger = LogFactory.getLog(ImportRebelUsers.class);
    private List<EmailFromRebel> siteUsers;
    @Resource
    private EmailFromRebelService emailFromRebelService;

    public void setEmailFromRebelService(EmailFromRebelService emailFromRebelService) {
        this.emailFromRebelService = emailFromRebelService;
    }

    public String importUsers() {
        emailFromRebelService.removeAll();
        emailFromRebelService.saveAll(getUsersFromSite());
        logger.debug("imported users = " + siteUsers.size());
        return Action.SUCCESS;
    }

    /*
     * Get all the users stored on the rebel site, gets the name and the
     * e-mail add
     */
    private List getUsersFromSite() {
        siteUsers = new ArrayList<EmailFromRebel>();
        try {
            String cleanedHtml = getHtmlString();
            int start = 0;
            int end = 0;
            EmailFromRebel emailFromRebel;
            while (cleanedHtml.indexOf("@netlight.se") > 0) {
                start = cleanedHtml.indexOf("mailto:");
                end = cleanedHtml.indexOf("netlight.se");
                String email = cleanedHtml.substring(start + 7, end + 11);
                if (email.indexOf("@netlight.se") > 0) {
                    //logger.debug(email + " start: " + start + "end: " + end);
                    cleanedHtml = cleanedHtml.substring(end + 3);
                    emailFromRebel = new EmailFromRebel(email);
                    if (!siteUsers.contains(emailFromRebel)) {
                        logger.debug("Adding new user to list: " + emailFromRebel.getEmail());
                        siteUsers.add(emailFromRebel);
                    }
                } else {
                    logger.error("Did not manage to fetch email from string: " + email);
                    start = 0;
                    end = 0;
                }
            }

        } catch (HttpException ex) {
            Logger.getLogger(ImportRebelUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImportRebelUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return siteUsers;
    }

    private String getHtmlString() throws HttpException, IOException {

        int port = 80;
        String host = "www.netlight.se";
        String protocol = "http";
        String cleanedHtmlXml = null;

        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);
        httpClient.getHostConfiguration().setHost(host, port, protocol);

        HttpMethod httpMethod = new GetMethod("/mobile/index.html");
        int httpStatus = httpClient.executeMethod(httpMethod);
        if (httpStatus != HttpStatus.SC_OK) {
            logger.error("Http call executed with non ok response code, " + httpStatus);
            return cleanedHtmlXml;
        } else {
            byte[] responseBodyBytes = httpMethod.getResponseBody();
            httpMethod.releaseConnection();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(responseBodyBytes);
            // create an output stream where to write result
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // create http cleaner
            HtmlCleaner htmlCleaner = new HtmlCleaner(inputStream);
            htmlCleaner.setOmitComments(true);
            htmlCleaner.clean();
            htmlCleaner.writeXmlToStream(outputStream);

            // get xml as string, it is iso-8859-1 encoded
            cleanedHtmlXml = new String(outputStream.toByteArray(),
                    "ISO-8859-1");
        }
        return cleanedHtmlXml;
    }
}
