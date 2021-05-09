<#import "parts/common.ftlh" as c>
<@c.page>
<div> hello, ${name}</div>
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
</@c.page>