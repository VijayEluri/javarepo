<%-- 
    Document   : answer_quiz
    Created on : Feb 19, 2008, 2:01:57 PM
    Author     : mire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" type="text/css" href="/netquiz/css/styles.css" title="Style">
        <title>NetLight - NetQuiz</title>
    </head>
    <script type="text/javascript">
        function make_blank(id)
        {
            document.form1.questionIdToAnswerMap['1'].value ="";
        }
    </script>
    <body>
        <s:if test="quiz == null">
            No quiz found
            <br>
            <br>
        </s:if>
        <s:else>
            <div class="centerDiv">
                <br>
                Hey <a class="bold"><s:property value="email"/>!</a>                  
                Some info about this quiz:
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
                    <a class="info">When you're ready click on "Start" and answer as fast as you can!</a>
                    <br>
                    <h1>Timer will start counting when you click on Start!</h1>
                    
                    <br>
                    <br>
                    <table>
                        <tbody>
                            <s:form name="form1" method="POST">
                                <s:hidden name="email" value="%{email}"/>
                                <s:hidden name="uniqueKey" value="%{uniqueKey}"/>
                                <s:hidden name="id" value="%{id}"/>
                                <s:if test="start == true">
                                    <tr>
                                        <td class="tableHeader">Question</td>                                    
                                        <td class="tableHeader">Your Answer</td>
                                    </tr>
                                    <!-- Iterate quiz.questions object to know what question text to display and the id of that question
                                questionIdToAnswerMap helps to store user's answers related to the question id
                                -->
                                
                                        <s:iterator value="quiz.questions">
                                        <tr>
                                            <td><s:property value="%{question}"/></td>
                                            <s:textfield name="questionIdToAnswerMap['%{id}']" value="%{answer}" onclick="document.form1.questionIdToAnswerMap['%{id}'].value ='';" />
                                        </tr>
                                    </s:iterator>
                                    <tr> <td><s:submit action="gradeanswers" targets="quiz"/></td></tr>
                                    <tr> <td><s:reset type="reset" value="Reset" name="reset" /></td> </tr>
                                </s:if>
                                <s:else>
                                
                                    <tr> <td><s:submit value="Start" label="Start" action="startquiz" targets="quiz"/></td></tr>
                                </s:else>
                            </s:form>
                        </tbody>
                    </table>
                </s:else>                        
                <br>
            </div>
            
            <br>
        </s:else>   
        <br>
    </body>
</html>
