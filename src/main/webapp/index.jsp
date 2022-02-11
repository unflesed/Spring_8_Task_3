<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<h2>Hello World!</h2>
<br/>
<br/>

<sec:authorize access="hasAuthority('ROLE_ADMIN')">
    <h1>Only admin can see this header :)</h1>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <h2>Hello, <sec:authentication property="name"/>!</h2>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <h1>Another header for authenticated user!!!</h1>
    <h3><sec:authentication property="name"/></h3>
    <p><sec:authentication property="details"/></p>
    <p><sec:authentication property="principal"/></p>
</sec:authorize>

</body>
</html>
