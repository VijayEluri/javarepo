This is a demo project using:
- Infinispan 5.1.5.FINAL

Run:
$ cd <Infinispan_home>

Node1: 
./startServer.sh -r hotrod -c /tmp/miguel/infinispan-replication5.1.xml

Node2: 
./startServer.sh -r hotrod -p 11223 -c /tmp/miguel/infinispan-replication5.1.xml

Demo GUI:
./runGuiDemo.sh

Place config file in: 
/opt/jboss/infinispan-5.1.5.CR1/bin/infinispan-replication5.1.xml

$ tail -f infinispan.log

-----------------------------------------------
Infinispan API: http://docs.jboss.org/infinispan/5.1/apidocs/
Workspace: 	/home/miguel/workspace/github.com/javarepo/jdg-workspace
Infinispan_home:/opt/jboss/infinispan-5.1.5.FINAL/bin
