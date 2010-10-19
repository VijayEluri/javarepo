<%-- 
    Document   : quiz_results
    Created on : Feb 20, 2008, 2:50:11 PM
    Author     : mire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" type="text/css" href="../css/styles.css" title="Style">
        <title>NetLight - NetQuiz</title>
    </head>
    <body>
        
        <div class="centerDiv300">
            <h2>Thanks for participating</h2>
            <br>
            <a class="info">Right answers: </a> <h1 class="results"><s:property value="%{score}"/> </h1>
            <br>
            <br>
        </div>
        <br>
    </body>
</html>
