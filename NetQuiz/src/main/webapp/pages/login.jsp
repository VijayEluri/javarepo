<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" type="text/css" href="./css/styles.css" title="Style">    
        
        <title>NetLight - NetQuiz Login</title>
    </head>
    
    <body>
        <div class="centerDiv300">
            <br/>
            <img src="img/netlight_banner_new2.jpg" width="216" height="72" alt="NetLight"/>
            <h1>Login to NetQuiz</h1>
            <br>        
            <s:form action="/j_acegi_security_check.action">
                <s:textfield label="User name" name="j_username"/> 
                <s:password label="Password" name="j_password"/> 
                <s:submit label="Login"/>            
            </s:form>
        </div>
    </body>
</html>