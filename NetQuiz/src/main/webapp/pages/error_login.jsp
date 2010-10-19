<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" type="text/css" href="../css/styles.css" title="Style">
        <title>NetLight - NetQuiz</title>
    </head>
    <body>
        <div class="centerDiv500" style="text-align: center;">
            <h1>Username and password don't match!</h1>            
            <a class="info">Try again or it could be that you don't have a user on NetQuiz</a>
            <br>
            <br>
            <a href="../pages/menu.jsp"><img src="../img/Sign_In.png" width="72" height="72" alt="Go home"/></a>
            <br>
            <br>
        </div>
    </body>
</html>
