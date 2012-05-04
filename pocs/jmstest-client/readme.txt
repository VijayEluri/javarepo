### Description:
I got this sample project from Siamak.

- JMSClient.java: Client uses only classes from javax.jms
- SampleMDB.java: Message Driven Bean

### Setup: 
- EAP5.1.2 with HornetQ (node1, node2)
- Workspace: /home/miguel/workspace/github.com/javarepo/volvo-workspace

### Run:
1. Disable firewall
$ sudo service iptables stop

2. Start clustered servers:
/opt/jboss/jboss-eap-5.1.2-hornetq/jboss-as/bin
./run.sh -c node1 -Dhornetq.server-id=1 -Dhornetq.backup.server-id=2
./run.sh -c node2 -Djboss.service.binding.set=ports-01 -Dhornetq.server-id=3 -Dhornetq.backup.server-id=4

3. Run JMSClient (ctrl + F11)
