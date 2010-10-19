<%-- 
    Document   : index
    Created on : Feb 11, 2008, 5:37:50 PM
    Author     : miguelreyes
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
        <s:head theme="ajax" />
    </head>
    <body>
        <s:if test="quizes == null || quizes.size < 1">
            No quizes found
            <br>
            <br>
        </s:if>
        <div class="centerDiv500">
            <br>
        <s:else>
            <a class="info">Quizes found</a>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Start date</th>
                    <th>End date</th>                    
                </tr>
                <s:iterator value="quizes">
                    <tr>  
                        <td><a href="../quiz/viewQuiz.action?id=<s:property value="id"/>"><s:property value="name"/></a></td>  
                        <td><s:date name="startDate"/></td>
                        <td><s:date name="endDate"/></td>                        
                    </tr>
                </s:iterator>
            </table>
            <br>
        </s:else>
        </div>
        <br>
        <div class="centerDiv300">
            <br>
            <table border="1"> 
                <s:form target="quiz" action="save" method="POST" validate="true">
                    <tbody>
                        <tr><td class="info">Create new quiz:</td></tr>
                        <tr>
                            <td colspan="2">
                                <s:actionerror />
                                <s:fielderror />
                            </td>
                        </tr>
                        <tr>
                            <s:textfield label="Name" name="quiz.name" maxLength="20"></s:textfield>
                        </tr>
                        <tr>
                            <s:datetimepicker name="quiz.endDate" label="End date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
                        </tr>
                        <tr>
                            <s:submit />
                        </tr>
                        <tr>
                            <s:reset type="reset" value="Reset" name="reset" />
                        </tr>
                    </tbody>
                </s:form>
            </table>
        </div>
        <br>
        <div class="centerDivNoBorder">
            <div style="text-align: center;">
            <a href="/netquiz/pages/menu.jsp"><img src="/netquiz/img/House.png" width="72" height="72" alt="Go home"/></a>
            </div>
        </div>
    </body>
</html>
