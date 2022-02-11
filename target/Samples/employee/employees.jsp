<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<h1>Employees table : </h1>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>ID</th>
        <th>Name</th>
        <th>Position</th>
        <th>Phone</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="employee" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.position}</td>
            <td>${employee.phone}</td>
            <td><a href="/Samples_war_exploded/employee/remove/${employee.id}">Remove</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>

<h2>To add new employee press "Add employee" button</h2>
<form method="POST" action="/Samples_war_exploded/employee/add">
    <table>
        <tr>
            <td><label for="name">Name</label></td>
            <td><input type="text" name="name" id="name"/></td>
        </tr>
        <tr>
            <td><label for="position">Position</label></td>
            <td><input type="text" name="position" id="position"/></td>
        </tr>
        <tr>
            <td><label for="phone">Phone</label></td>
            <td><input type="text" name="phone" id="phone"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Add Employee">
            </td>
        </tr>
    </table>
</form>

<br/><br/>
<form method="POST" action="/Samples_war_exploded/employee/findByName">
    <table>
        <tr>
            <td><label for="name">Name</label></td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Search by name">
            </td>
        </tr>
    </table>
</form>

<br/><br/>
<form method="POST" action="/Samples_war_exploded/employee/findByNameAndPosition">
    <table>
        <tr>
            <td><label for="name">Name</label></td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td><label for="position">Position</label></td>
            <td><input type="text" name="position"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Search by name and position">
            </td>
        </tr>
    </table>
</form>

</body>
</html>

