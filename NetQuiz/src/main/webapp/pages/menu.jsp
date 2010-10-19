<%-- 
    Document   : menu
    Created on : Jan 10, 2008, 9:43:29 AM
    Author     : mire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" type="text/css" href="../css/styles.css" title="Style">
        <title>NetLight - NetQuiz Home</title>
    </head>
    <body>
        <div class="centerDiv300">
        <table border="0">
            <thead>
                <tr>
                <th><img src="../img/netlight_banner_new2.jpg" width="216" height="72" alt="netlight_banner_new2"/></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="user/index.jsp">Manage admin users</a></td>
                </tr>
                <tr>
                    <td><a href="quiz/list.action">Manage Quizes</a></td>
                </tr>                
                <tr>
                    <td><a href="quiz/index_questions.jsp">Manage questions</a></td>
                </tr>
                <tr>
                    <td><a href="questions/listOldQuestions.action">View old questions</a></td>
                </tr>
                <tr>
                    <td><a href="quiz/import.action">Import users</a></td>
                </tr>
                <tr>
                    <td><a href="quiz/answerquiz.action?id=33&email=miguel.reyes@netlight.se&uniqueKey=DHSMxW">Answer quiz</a></td>
                </tr>
            </tbody>
        </table>
        <br>
        <div class="centerDiv300NoBorder">
            <div style="text-align: center;">
            <a href="../j_acegi_logout" target="_top"><img src="../img/Door.png" width="72" height="72" alt="Log out"/></a>
            </div>
        </div>
        <br>
        </div>
        
    </body>
</html>
