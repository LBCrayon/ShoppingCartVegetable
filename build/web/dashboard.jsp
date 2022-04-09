<%-- 
    Document   : dashboard.jsp
    Created on : Aug 23, 2021, 3:17:14 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Page</title>
        <link rel="stylesheet" href="css/dashboard.css"/>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${user eq null || user.getRoleID() eq 'US001'}">
            <c:redirect url="MainController?action=Home"/>
        </c:if>
        <header>
            <jsp:include page="header.jsp"></jsp:include>
            </header>

            <div class="searchbar">
                <form action="MainController">
                    <input type="text" name="search" value="${param.search}" placeholder="Search for vegetable"/>
                    <select name="status">
                        <option value="true">Available</option>
                        <option value="false">Deleted</option>
                    </select>
                    <input type="submit" value="Search" name="action"/>
                </form>
            </div>
                    
            <c:if test="${user.getRoleID() eq 'AD002'}">
            <div class="welcome" id="welcome-popup">
                <h1>Welcome ${sessionScope.USER.getFullname()}</h1>
                <p>We hope you have a nice working day</p>
                <button class="btn" onclick="closeWelcome()">Ok</button>
            </div>
            </c:if>
                    
                    <div class="error">
                        <p>${requestScope.ERROR}</p>
                        <p>${requestScope.UPDATE_ERROR.errorName}</p>
                        <p>${requestScope.UPDATE_ERROR.errorQuantity}</p>
                    </div>

        <c:if test="${requestScope.CAR_LIST != null}">
            <div class="table">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price($)</th>
                            <th>Sale(%)</th>
                            <th>Category</th>
                            <th>Action</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vegetable" varStatus="counter" items="${requestScope.CAR_LIST}">
                            <tr>
                        <form action="MainController" method="POST">
                            <td>${counter.count}</td>
                            <td>
                                <input type="text" name="vegetableID" value="${vegetable.getVegetableID()}" readonly=""/>
                            </td>
                            <td>
                                <input type="text" name="vegetableName" value="${vegetable.getVegetableName()}" required=""/>
                            </td>
                            <td>
                                <input type="number" name="quanity" value="${vegetable.getQuanity()}" min="1" required=""/>
                            </td>
                            <td>
                                <input type="number" name="price" value="${vegetable.getPrice()}" min="100" max="1000000000" required=""/>
                            </td>
                            <td>
                                <input type="number" name="sale" value="${vegetable.getSale()}" min="0" max="60" required=""/>
                            </td>
                            <c:forEach var="category" items="${requestScope.CATEGORY}">
                                <c:if test="${vegetable.getCategoryID() eq category.getCategoryID()}">
                                    <td>${category.getCategoryName()}</td>
                                </c:if>
                            </c:forEach>
                            <td>
                                <c:if test="${param.status eq true || param.status eq null}">
                                    <c:url var="delete" value="MainController">
                                        <c:param name="action" value="Delete"/>
                                        <c:param name="search" value="${param.search}"/>
                                        <c:param name="vegetableID" value="${vegetable.getVegetableID()}"/>
                                        <c:param name="status" value="true"/>
                                    </c:url>
                                    <a href="${delete}">Delete</a>
                                </c:if>
                                <c:if test="${param.status eq false}">
                                    <c:url var="launch" value="MainController">
                                        <c:param name="action" value="Launch"/>
                                        <c:param name="search" value="${param.search}"/>
                                        <c:param name="vegetableID" value="${vegetable.getVegetableID()}"/>
                                        <c:param name="status" value="false"/>
                                    </c:url>
                                    <a href="${launch}">Launch</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${param.status eq null}">
                                    <input type="hidden" name="status" value="true" />
                                </c:if>
                                <c:if test="${param.status ne null}">
                                    <input type="hidden" name="status" value="${param.status}" />
                                </c:if>
                                <input type="hidden" name="search" value="${param.search}" />
                                <input type="submit" value="Update" name="action" />
                            </td>
                        </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <h2>${requestScope.NOTIFY}</h2>

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
