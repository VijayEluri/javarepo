Maven-plugin project created with Netbeans 7.3

$ mvn -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-mojo -DarchetypeVersion=1.0 -DarchetypeRepository=http://repo.maven.apache.org/maven2/ -DgroupId=com.miguel -DartifactId=my-maven-plugin -Dversion=1.0-SNAPSHOT -Dpackage=com.miguel -Dbasedir=/home/miguel/workspace/github.com/javarepo -Darchetype.interactive=false --batch-mode archetype:generate

$ mvn com.miguel:my-maven-plugin:touch
