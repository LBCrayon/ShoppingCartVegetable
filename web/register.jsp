<%-- 
    Document   : register
    Created on : Aug 24, 2021, 2:36:50 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${user.getRoleID() eq 'US001'}">
            <c:redirect url="MainController?action=Home"/>
        </c:if>
        <c:if test="${user.getRoleID() eq 'AD002'}">
            <c:redirect url="MainController?action=ViewDashboard"/>
        </c:if>
        <form action="MainController" method="POST">
            Username <input type="text" name="username" value="${param.username}" required=""/><br>
            ${requestScope.ERROR.usernameError}<br>
            Fullname <input type="text" name="fullname" value="${param.fullname}" required=""/><br>
            ${requestScope.ERROR.fullnameError}<br>
            Email <input type="text" name="email" value="" required=""/><br>
            ${requestScope.ERROR.emailError}<br>
            Address <input type="text" name="address" value="${param.address}" /><br>
            ${requestScope.ERROR.addressError}<br>
            Phone <input type="text" name="phone" value="" /><br>
            ${requestScope.ERROR.phoneError}<br>
            Password <input type="password" name="password" value="" required="" min="8" max="1<br>6"/><br>
            ${requestScope.ERROR.passwordError}<br>
            Confirm <input type="password" name="confirm" value="" required="" min="8" max="16"/><br>
            ${requestScope.ERROR.confirmError}<br>
            <input type="submit" value="Create" name="action"/><br>
            <a href="MainController?action=Home">Already have account</a>
        </form>
    </body>
</html>
