package se.netlight.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.HSQLDialect;
import se.netlight.netquiz.quiz.model.Pair;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.netquiz.user.model.Authority;
import se.netlight.netquiz.user.model.EmailFromRebel;
import se.netlight.netquiz.user.model.User;

public class InMemSessionFactoryGenerator {
    
    public static SessionFactory getInMemSessionFactory() {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.setProperty(Environment.DRIVER, org.hsqldb.jdbcDriver.class.getName());
        configuration.setProperty(Environment.URL, "jdbc:hsqldb:mem:playdo");
        configuration.setProperty(Environment.DIALECT, HSQLDialect.class.getName());
        configuration.setProperty(Environment.USER, "sa");
        configuration.setProperty(Environment.PASS, "");
        configuration.setProperty(Environment.POOL_SIZE, "1");
        configuration.setProperty(Environment.SHOW_SQL, "true");
        configuration.setProperty(Environment.HBM2DDL_AUTO, "create");

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Authority.class);
        configuration.addAnnotatedClass(Quiz.class);
        configuration.addAnnotatedClass(Pair.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Participant.class);
        configuration.addAnnotatedClass(EmailFromRebel.class);
        
        return configuration.buildSessionFactory();
    }


}
