<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>

    <div>
        <@l.logout/>
        <span><a href="/user">User List</a> </span>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="textMessage" placeholder="input message text" />
            <input type="text" name="tag" placeholder="input tag" />
            <input type="file" name="File">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">add post</button>
        </form>
    </div>
    <div>List Message</div>

    <form method="get" action="/check">
        <input type="text" name="filterTag" value="${RequestParameters.filterTag!""}" placeholder="filter by tag" />
        <button type="submit">Find</button>
    </form>

    <#list nameModel as message>
        <div>
            <b>${message.id!""}</b>
            <span>${message.text!""}</span>
            <i> ${message.tag!""}</i>
            <strong>${message.authorName!""}</strong>
        </div>
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}">
            </#if>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>