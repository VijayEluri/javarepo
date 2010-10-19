<%@ taglib prefix="s" uri="/struts-tags"%>

<a class="info">Admin users found</a>
<s:if test="users.size > 0">
    <table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Options</th>
            </tr>
        </thead>
        <s:iterator value="users">
            <tr id="row_<s:property value="id"/>">
                <td>
                    <s:property value="username" />
                </td>				
                <td>
                    <s:property value="password" />
                </td>
                <td>
                    <s:url id="removeUrl" action="removeUsers">
                        <s:param name="id" value="id" />
                    </s:url>
                    <s:a href="%{removeUrl}" theme="ajax" targets="users">Remove</s:a>
                    <s:a id="a_%{id}" theme="ajax" notifyTopics="/editUsers">Edit</s:a>
                </td>
            </tr>
        </s:iterator>
    </table>
</s:if>
    