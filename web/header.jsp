<%-- 
    Document   : header
    Created on : Aug 18, 2021, 9:00:00 PM
    Author     : Admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="css/headerCss.css"/>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <div class="title">
            <c:if test="${user.getRoleID() eq 'US001' || user == null}">
            <a href="MainController?action=Home">
                <h2>
                    Welcome to VapeShop
                </h2>
            </a>
            </c:if>
            <c:if test="${user.getRoleID() eq 'AD002'}">
                <a href="MainController?action=ViewDashboard">
                <h2>
                    Welcome to VapeShop
                </h2>
            </a>
            </c:if>
        </div>

        <div class="navbar">
            <nav class="navlink">
                <c:if test="${user.getRoleID() eq 'US001' || user == null}">
                    <a class="logo" href="MainController?action=Home">
                        <img src="img/Logo2.png" alt="logo"/>
                    </a>
                    <a class="link" href="MainController?action=Home">Home</a>
                    <a class="link" href="MainController?action=ViewCar">Product</a>
                </c:if>
                <c:if test="${user.getRoleID() eq 'AD002'}">
                    <a class="logo" href="MainController?action=ViewDashboard">
                        <img src="img/Logo2.png" alt="logo"/>
                    </a>
                    <a class="link" href="MainController?action=ViewDashboard">Dashboard</a>
                </c:if>
            </nav>
            
            <c:if test="${user.getRoleID() eq 'US001' || user == null}">
            <form class="navSearch" action="MainController">
                <input type="text" name="search" value="${param.search}" placeholder="Search for vegertable"/>
                <input type="submit" value="Search" name="action" />
            </form>
            </c:if>

            <c:if test="${user == null}">
                <button id="Sign-in-form" class="btn" onclick="openForm()">Sign In</button>
            </c:if>
            <c:if test="${user != null}">
                <c:url var="signout" value="MainController">
                    <c:param name="action" value="Sign out"/>
                </c:url>
                <a class="signoutLink" href="${signout}">Sign out</a>
            </c:if>
        </div>

        <div class="form-popup" id="myForm">
            <form action="MainController" class="form-container" method="POST">
                <h2>Sign In</h2>

                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" value="${param.username}" required=""/>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required=""/>
                <br>
                <center><font color="red">${requestScope.ERROR}</font></center><br>

                <input type="submit" name="action" class="btn" value="Sign In"/>
                <p>--------------------  or  --------------------</p>
                <p> If you haven't sign up yet! 
                    <span><a href="MainController?action=GotoRegister">Sign up at here</a></span>
                </p>
            </form>
        </div>

        <script>
            var x = 0;

            function openForm() {
                ++x;
                if (x === 1) {
                    document.getElementById("myForm").style.display = "block";
                }
                
                if (x === 2) {
                    document.getElementById("myForm").style.display = "none";
                    x = 0;
                }
            }
            
            <c:if test="${requestScope.ERROR != null}">
            window.onload = function () {
                var button = document.getElementById("Sign-in-form");
                button.click();
            };
            </c:if>
        </script>
    </body>
</html>
