<%@ taglib prefix="s" uri="/struts-tags"%>

<a class="info">These questions will be added to the next Quiz created</a>
<br>
<s:if test="questions.size > 0">
    <table>
        <thead>
            <tr>
                <th>Question</th>
                <th>Answer</th>
                <th>Priority</th>
                <th>Options</th>
            </tr>
        </thead>
        <s:iterator value="questions">
            <tr id="row_<s:property value="id"/>">
                <td>
                    <s:property value="question" />
                </td>				
                <td>
                    <s:property value="answer" />
                </td>
                <td>
                    <s:property value="priority" />
                </td>
                <td>
                    <s:url id="removeUrl" action="removeQuestion">
                        <s:param name="id" value="id" />
                    </s:url>
                    <s:a href="%{removeUrl}" theme="ajax" targets="questions">Remove</s:a>
                    <s:a id="a_%{id}" theme="ajax" notifyTopics="/editQuestion">Edit</s:a>
                </td>
            </tr>
        </s:iterator>        
    </table>
</s:if>
<s:else>
    <br>
    <a class="info">No questions found</a>
    <br>
    <br>
</s:else>
    