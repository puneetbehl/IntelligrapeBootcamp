<g:form controller="user" action="loginHandler" method="post" data-ajax="false">
    <ul data-role="listview" data-inset="true">
        <li data-role="list-divider" role="heading">
            Login
        </li>
        <li data-role="fieldcontain">
            <g:field type="email" name="email" placeholder="Email" value="" id="email" required="true"/>

        </li>
        <li data-role="fieldcontain">
            <g:passwordField name="password" id="password" value="" placeholder="Password" required="true"/>

        </li>
        <li data-role="fieldcontain">

            <input type="submit" value="Login" data-transition="fade"/>
        </li>
    </ul>
</g:form>