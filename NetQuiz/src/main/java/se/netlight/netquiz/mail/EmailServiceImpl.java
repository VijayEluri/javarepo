/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author mire
 */
public class EmailServiceImpl implements EmailService {

    private static Log logger = LogFactory.getLog(EmailServiceImpl.class);

    public void sendEmail(String to, String from, String subject, String body) {
        try {
            //Properties props = System.getProperties();
            //props.put("mailrelay1.bredband.net", smtpServer);
            //Session session = Session.getDefaultInstance(props, null);
            // -- Create a new message --
            Message msg = new MimeMessage(getSession());
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(body);
            // -- Set some other header information --
            msg.setHeader("X-Mailer", "LOTONtechEmail");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            logger.debug("Message sent OK.");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error sending email");
        }
    }

    private Session getSession() {
        Authenticator authenticator = new Authenticator();

        Properties properties = new Properties();
        //NOTE Works withouth validating user, I leave this here in case it's needed later
//        properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
//        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.host", "mailrelay1.bredband.net");
        properties.setProperty("mail.smtp.port", "25");

//        return Session.getInstance(properties, authenticator);
        return Session.getInstance(properties);
    }

    private class Authenticator extends javax.mail.Authenticator {

        private PasswordAuthentication authentication;

        public Authenticator() {
            String username = "info";
            String password = "info01";
            authentication = new PasswordAuthentication(username, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
