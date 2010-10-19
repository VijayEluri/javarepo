/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.action;

import java.util.List;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import se.netlight.netquiz.quiz.model.Pair;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.netquiz.user.model.EmailFromRebel;

/**
 *
 * @author mire
 */
public class QuizUtil {

    private static Log logger = LogFactory.getLog(QuizUtil.class);

    /**
     * Method that will create pairs of participants from a list of emails
     * and add this pairs to the Quiz
     * 
     * @param quiz Quiz to add pairs
     * @param emails Emails used to generate pairs
     */
    public void generatePairsAndParticipants(Quiz quiz, List<EmailFromRebel> emails) {

        int emailsFound = emails.size();
        logger.debug("******** emailsFound = " + emailsFound);
        int numberOfPairs = emails.size() / 2;
        logger.debug("numberOfPairs = " + numberOfPairs);

        //Check if it's an odd number
        int result = emailsFound % 2;
        boolean threesome = false;
        if (result > 0) {
            threesome = true;
            logger.debug("Last team will be a threesome");
        }

        Pair pair;

        int random_number = 0;
        int top = emailsFound - 1;
        logger.debug("top = " + top);

        //Create as many pairs as possible, and threesome if necessary
        for (int i = numberOfPairs; i >= 1; i--) {
            logger.debug("Creating new pair");
            pair = new Pair();

            //Add first participant
            random_number = myRandomNumber(top);
            pair.addParticipant(new Participant(emails.get(random_number).getEmail(), myRandomString()));
            logger.debug("emails.get(random_number).getEmail() = " + emails.get(random_number).getEmail());
            emails.remove(random_number);
            top--;

            //Add second participant
            random_number = myRandomNumber(top);
            pair.addParticipant(new Participant(emails.get(random_number).getEmail(), myRandomString()));
            logger.debug("emails.get(random_number).getEmail() = " + emails.get(random_number).getEmail());
            emails.remove(random_number);
            top--;

            //Add third if it's last team
            if (threesome && i == 1) {
                logger.debug(". Getting the last email");
                pair.addParticipant(new Participant(emails.get(0).getEmail(), myRandomString()));
                logger.debug("emails.get(random_number).getEmail() = " + emails.get(0).getEmail());
                emails.remove(random_number);
                top--;

            }

            quiz.addPair(pair);
        }
        logger.debug("Quiz has pairs: " + quiz.getPairs().size());
    }

    /**
     * Returns a random number betweek 0 and top
     * 
     * @param top Number of emails in the list
     * @return random number betweek 0 and top
     */
    private int myRandomNumber(int top) {
        if (top > 0) {
            Random r = new Random();
            int number = r.nextInt(top);
            int random_number = number % top;
            logger.debug("random_number = " + random_number);
            return random_number;
        } else {
            return top;
        }

    }

    public String myRandomString() {
        String str = new String("QAabcLdUK2eHfJgTP8XhiFj6DkNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int te = 0;
        for (int i = 1; i <= 6; i++) {
            te = r.nextInt(58);
            sb.append(str.charAt(te));
        }
        logger.debug(sb.toString());
        return sb.toString();
    }
}
