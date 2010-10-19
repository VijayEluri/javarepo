<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" type="text/css" href="../../css/styles.css" title="Style">
        <title>NetLight - NetQuiz</title>             
        <s:head theme="ajax" debug="false"/>
        <script type="text/javascript">
            dojo.event.topic.subscribe("/saveUsers", function(data, type, request) {
                if(type == "load") {
                    dojo.byId("id").value = "";
                    dojo.byId("username").value = "";
                    dojo.byId("password").value = "";
                }
            });
            
            dojo.event.topic.subscribe("/editUsers", function(data, type, request) {
                if(type == "before") {
                    var id = data.split("_")[1];
                    
                    var tr = dojo.byId("row_"+id);
                    var tds = tr.getElementsByTagName("td");
                    
                    dojo.byId("id").value = id;
                    dojo.byId("username").value = dojo.string.trim(dojo.dom.textContent(tds[0]));
                    dojo.byId("password").value = dojo.string.trim(dojo.dom.textContent(tds[1]));
                }
            });
        </script>
    </head>
    <body>        
        <div class="centerDivNoBorder">
            <s:url action="listUsers" id="descrsUrl"/>
            <div class="centerDiv300">
                <div style="text-align: right;">
                    <s:a theme="ajax" notifyTopics="/refresh">Refresh</s:a>
                </div>
                <s:div id="users" theme="ajax" href="%{descrsUrl}" loadingText="Loading..." listenTopics="/refresh"/>
            </div>
            
            <br>
            
            <div class="centerDiv300">
                <br>
                <a class="info">Create a new user or click on Edit</a>
                <s:form action="saveUsers" validate="true">
                    <s:textfield id="id" name="user.id" cssStyle="display:none"/>
                    <s:textfield id="username" label="User name" name="user.username" maxLength="20"  tooltip="Max 20 characters"/>
                    <s:textfield id="password" label="Password" name="user.password" maxLength="8" tooltip="Max 8 characters"/>
                    <s:submit theme="ajax" targets="users" notifyTopics="/saveUsers"/>
                    <s:reset type="reset" value="Reset" name="reset" />
                </s:form>
                
                <a class="info">NOTE: Users automatically get the Role of Admin in Authorizations table</a>
                <br>
                <br>
            </div>
            <br>
            <div class="centerDivNoBorder">
                <div style="text-align: center;">
                    <a href="../menu.jsp"><img src="../../img/House.png" width="72" height="72" alt="Go home"/></a>
                </div>
            </div>
        </div>
    </body>
</html>
