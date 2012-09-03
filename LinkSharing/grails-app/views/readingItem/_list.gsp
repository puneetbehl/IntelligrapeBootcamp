<table id="dlist">

    <thead>
    <tr>
        <th><g:select id="selectReadingItem" name="selectReadingItem"
                      from="['Select Options', 'Check All', 'Uncheck All', 'Inverse']">
        </g:select>
        </th>
        <g:sortableColumn property="favorite" title="${message(code: 'readingItem.isFavorite.label', default: 'Favorite')}" />

        <th><g:message code="readingItem.resource.label" default="Resource" /></th>

        <th><g:message code="readingItem.user.label" default="User" /></th>

    </tr>
    </thead>
    <tbody>
    <g:each in="${list}" status="i" var="item">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>%{--<g:link controller="readingItem" action="markRead" id="${item.id}">--}%
                <g:checkBox name="status" value="${item.id}" />
            %{--</g:link>--}%</td>

            <td><g:link controller="readingItem" action="show" id="${item.id}">${fieldValue(bean: item, field: "isFavorite")}</g:link></td>

            <td>${fieldValue(bean: item, field: "resource")}</td>

            <td>${fieldValue(bean: item, field: "user")}</td>

        </tr>
    </g:each>
    </tbody>
</table>
<fieldset class="buttons">
    <input type="button" name="mark-read-button" class="save" value="Mark Read"/>
    <input type="button" name="mark-unread-button" class="save" value="Mark Unread"/>
</fieldset>