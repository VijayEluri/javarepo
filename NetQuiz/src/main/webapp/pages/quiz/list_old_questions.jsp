<%-- 
    Document   : list_old_questions
    Created on : Feb 19, 2008, 10:27:37 AM
    Author     : mire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" type="text/css" href="../../css/styles.css" title="Style">
        <title>NetLight - NetQuiz</title>
    </head>
    <body>
        <div class="centerDiv">
            <br>
            <s:if test="questions == null || questions.size < 1">
                No questions found    
                <br>
                <br>
            </s:if> 
            <s:else>
                
                <a class="info">These are the questions used in previous quizes</a>
                <br>
                <br>
                <table>
                    <tr>
                        <th>Questions</th>
                        <th>Answer</th>
                        <th>Priority</th>       
                        <th>Used in quiz</th> 
                    </tr>
                    <s:iterator value="questions">
                        <tr>  
                            <td><s:property value="question"/></td>
                            <td><s:property value="answer"/></td>                        
                            <td><s:property value="priority"/></td> 
                            <td><s:property value="quiz.name"/></td> 
                        </tr>
                    </s:iterator>
                </table>
                <br>
                <br>
            </s:else>
        </div>
        <br>
        <div class="centerDivNoBorder">
            <div style="text-align: center;">
                <a href="../menu.jsp"><img src="../../img/House.png" width="72" height="72" alt="Go home"/></a>
            </div>
        </div>
    </body>
</html>
