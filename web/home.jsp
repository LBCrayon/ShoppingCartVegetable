<%-- 
    Document   : home
    Created on : Aug 18, 2021, 8:59:44 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="css/home.css"/>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${user.getRoleID() eq 'AD002'}">
            <c:redirect url="MainController?action=ViewDashboard"/>
        </c:if>
        <header>
            <jsp:include page="header.jsp"></jsp:include>
            </header>
            
            <div class="picture">
                <img src="img/vegetableShow.jpg"/>
            </div>
            
            <c:if test="${user.getRoleID() eq 'US001'}">
            <div class="welcome" id="welcome-popup">
                <h1>Welcome ${sessionScope.USER.getFullname()}</h1>
                <p>We hope you have a nice day shopping</p>
                <button class="btn" onclick="closeWelcome()">Ok</button>
            </div>
            </c:if>
            
            <div>
                
            </div>
            
            <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
        
        <script>
            function closeWelcome() {
                document.getElementById("welcome-popup").style.display = "none";
            }
        </script>
    </body>
</html>
