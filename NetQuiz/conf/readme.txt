Requirerements to compile and deploy this project

----------------------------------------------------------------------------------------
1. Database
----------------------------------------------------------------------------------------

	- Create a Database with the name "netquiz" in a local instance of MySQL 6
	
	- The application needs a user that has full access to the Database. It's initially set up to use:
		username: root
		password: 
	  This can be configured in: 
	  	src\main\resources\applicationContext-netquiz.properties
	  	
	- Finally, make sure the DB is running when you compile the application
	
	- Run SQL queries to create sample users, copy/paste the inserts located in: 
		conf\db_users.txt

----------------------------------------------------------------------------------------
2. Maven2
----------------------------------------------------------------------------------------

     The goals to run from a terminal pointing to the project folder are:
    	- Compile:
    	 	mvn clean install
    
    	- Run webapp:
    	 	mvn jetty:run
    	 
    	 	
    	 If this works you can access: http://localhost:8080/netquiz
    	 username: miguel
    	 password: 1234
    	 
    	- Documentation:
    		mvn site:site
    		
    	 An html page is later accesible in directory: 
    	 	target\site\index.html
    	
