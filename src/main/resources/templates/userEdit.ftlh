<#import "parts/common.ftlh" as c>
<@c.page>
    User Editor
    <form action="/user" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" value="${user.id}" name="userID">
        <input type="text" value="${user.username}" name="username">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.role?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>

        </#list>

        <button type="submit">Save</button>
    </form>
</@c.page>