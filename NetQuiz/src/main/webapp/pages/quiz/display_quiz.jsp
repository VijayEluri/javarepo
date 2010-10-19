<%-- 
    Document   : displayQuiz
    Created on : Feb 15, 2008, 5:39:48 PM
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
        <title>Netlight - NetQuiz</title>
    </head>
    <body>
        <s:if test="quiz == null">
            No quiz found
            <br>
            <br>
        </s:if>
        <s:else>
            <div class="centerDiv">
                <br>
                <a class="info">Some info about this quiz:</a>
                <br>
                <br>
                <table class="tableLeft">
                    <tbody>
                        <tr>
                            <td><s:label name="quiz.name" label="Name"/></td>
                        </tr>
                        <tr>
                            <td class="bold">Start date:</td>
                            <td><s:date name="quiz.startDate" format="yyyy/MM/dd" /></td>
                            
                        </tr>
                        <tr>
                            <td class="bold">End date:</td>
                            <td><s:date name="quiz.endDate" format="yyyy/MM/dd" /></td>
                            
                        </tr>
                        <s:if test="quiz.questions == null || quiz.questions.size < 1">
                            <tr>
                                <td> -- No questions found --</td>                               
                            </tr>
                        </s:if>
                    </tbody>
                </table>
                <s:else>
                    <br>
                    <a class="info">Questions in this Quiz:</a>
                    <br>
                    <br>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Question</th>                                    
                                <th>Answer</th>
                                <th>Priority</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="quiz.questions">
                                <tr>
                                    <td><s:property value="question"/></td>
                                    <td><s:property value="answer"/></td>
                                    <td><s:property value="priority"/></td>
                                </tr>
                                
                            </s:iterator>
                        </tbody>
                    </table>
                </s:else>                        
                <br>
            </div>
            <br>  
            <!-- WINNERS -->
            <div class="centerDiv300">
                <br> 
                <a class="info">Current winners:</a>
                <br> 
                <s:if test="winners == null || winners.size < 1">
                    No winners yet
                </s:if>
                <s:else>                
                    <br>                 
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Email</th>                                    
                                <th>Score</th>
                                <th>Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="winners">
                                <tr>
                                    <td><s:property value="email"/></td>
                                    <td><s:property value="score"/></td>                                                                      
                                    <td><s:property value="answeringTime"/></td>                                  
                                </tr>
                            </s:iterator> 
                        </tbody>
                    </table>
                    <br>                    
                </s:else>                
                
            </div>
            
            
            <br>
            
            
            <!-- PAIRS -->
            <s:if test="quiz.pairs == null || quiz.pairs.size < 1">
                No teams found
            </s:if>
            <s:else>
                <s:iterator value="quiz.pairs">
                    <s:label name="id" label="Team id" />
                    <br>
                    <s:if test="participants == null || participants.size < 1">
                        No Participants found in team
                    </s:if>
                    <s:else>                    
                        <table class="tableLeft" border="1">
                            <thead>
                                <tr>
                                    <th>Email</th>                                    
                                    <th>Key</th>
                                    <th>Score</th>
                                    <th>Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="participants">
                                    <tr>
                                        <td><s:property value="email"/></td>
                                        <td><s:property value="uniqueKey"/></td>
                                        <td><s:property value="score"/></td>                                                                      
                                        <td><s:property value="answeringTime"/></td>                                  
                                    </tr>
                                </s:iterator> 
                            </tbody>
                        </table>
                        <br>
                    </s:else>                    
                </s:iterator>                
                
            </s:else>
        </s:else>
        <br>
        <div class="centerDivNoBorder">
            <div style="text-align: center;">
                <a href="../menu.jsp"><img src="../../img/House.png" width="72" height="72" alt="Go home"/></a>
            </div>
        </div>
    </body>
</html>
