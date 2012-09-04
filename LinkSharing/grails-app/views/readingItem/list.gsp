<%@ page import="com.ig.bc.ReadingItem" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'readingItem.label', default: 'ReadingItem')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-readingItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-readingItem" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <div id="readingItemListDiv">
        <g:render template="list" model="[list: readingItemInstanceList]"/>
    </div>

    <div class="pagination">
        <g:paginate total="${readingItemInstanceTotal}"/>
    </div>
</div>

<script type="text/javascript">
    urls = {
        markReadUrl: "${createLink(controller: "readingItem", action: "markRead")}",
        markUnreadUrl: "${createLink(controller: 'readingItem', action: 'markUnread')}",
        markFavUrl: "${createLink(controller: 'readingItem', action: 'markFav')}",
        unmarkFavUrl:"${createLink(controller: 'readingItem', action: 'unmarkFav')}",
        markCurrentReadUrl:"${createLink(controller: 'readingItem', action: 'markCurrentRead')}",
        markCurrentUnreadUrl:"${createLink(controller: 'readingItem', action: 'markCurrentUnread')}"
    }
</script>
</body>
</html>
