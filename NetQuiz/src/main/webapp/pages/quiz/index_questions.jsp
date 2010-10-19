<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NetLight - NetQuiz</title>
        <s:head theme="ajax" debug="false"/>
        <link rel ="stylesheet" type="text/css" href="/netquiz/css/styles.css" title="Style">
        <script type="text/javascript">
            dojo.event.topic.subscribe("/saveQuestion", function(data, type, request) {
                if(type == "load") {
                    dojo.byId("id").value = "";
                    dojo.byId("question").value = "";
                    dojo.byId("answer").value = "";
                    dojo.byId("priority").value = "";
                }
            });
            
            dojo.event.topic.subscribe("/editQuestion", function(data, type, request) {
                if(type == "before") {
                    var id = data.split("_")[1];
                    
                    var tr = dojo.byId("row_"+id);
                    var tds = tr.getElementsByTagName("td");
                    
                    dojo.byId("id").value = id;
                    dojo.byId("question").value = dojo.string.trim(dojo.dom.textContent(tds[0]));
                    dojo.byId("answer").value = dojo.string.trim(dojo.dom.textContent(tds[1]));
                    dojo.byId("priority").value = dojo.string.trim(dojo.dom.textContent(tds[2]));
                }
            });
        </script>        
    </head>
    <body>
        <s:url action="listQuestions" id="descrsUrlQuestions"/>
        <div class="centerDiv">
            <div style="text-align: right;">
                <s:a theme="ajax" notifyTopics="/refresh">Refresh</s:a>
            </div>
            <s:div id="questions" theme="ajax" href="%{descrsUrlQuestions}" loadingText="Loading..." listenTopics="/refresh"/>
            <br>
        </div>
        
        <br>
        
        <div class="centerDiv900">
            <br>
            <a class="info">Create a new question or click on Edit</a>
            <s:form action="saveQuestion" validate="true">
                <s:textfield id="id" name="question.id" cssStyle="display:none"/>
                <s:textfield id="question" label="Question" name="question.question" maxLength="115" size="130"/>
                <s:textfield id="answer" label="Answer" name="question.answer" maxLength="20"/>
                <s:textfield id="priority" label="Priority" name="question.priority" size="5" maxLength="2" tooltip="From 1-10"/>
                <s:submit theme="ajax" targets="questions" notifyTopics="/saveQuestion"/>
                <s:reset type="reset" value="Reset" name="reset" />
            </s:form>            
        </div>
        <br/>
        <div class="centerDivNoBorder">
            <div style="text-align: center;">
            <a href="../menu.jsp"><img src="../../img/House.png" width="72" height="72" alt="Go home"/></a>
            </div>
        </div>
    </body>
</html>
