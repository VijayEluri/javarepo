### Description: 
Project from 
http://theopentutorials.com/examples/java-ee/ejb3/remote-jms-client-and-ejb3-mdb-consumer-eclipse-jboss-7-1/

- JMSApplicationClient.java: Client uses javax.jms and org.hornetq classes.
- QueueListenerMDB.java: Message Driven Bean created with JBDS wizzard.
- Queue gets a message that is consumed by the EJB.
- Both classes are part of the same project

### Setup: 
- AS7.1.1 (/opt/jboss/jboss-as-7.1.1.Final/)
- HornetQ configured in standalone.xml (not using standalone-full.xml)
- Uncomment 'guest' user from application-roles.properties

### To run:
1. Deploy EJB in AS7
2. Run client from inside JBDS (ctrl + F11)
	

### Requirements:
- JBDS project needs to have /opt/jboss/jboss-as-7.1.1.Final/bin/client/jboss-client.jar
