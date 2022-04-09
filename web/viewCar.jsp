<%-- 
    Document   : viewCar
    Created on : Aug 21, 2021, 10:46:37 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${user.getRoleID() eq 'AD002'}">
            <c:redirect url="MainController?action=ViewDashboard"/>
        </c:if>
        <header>
            <jsp:include page="header.jsp"></jsp:include>
            </header>
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
                                <input type="text" name="vegetableName" value="${vegetable.getVegetableName()}" readonly=""/>
                            </td>
                            <td>${vegetable.getQuanity()}</td>
                            <td>
                                <input type="number" name="price" value="${vegetable.getPrice()}" readonly=""/>
                            </td>
                            <td>
                                <input type="number" name="sale" value="${vegetable.getSale()}" readonly=""/>
                            </td>
                            <c:forEach var="category" items="${requestScope.CATEGORY}">
                                <c:if test="${vegetable.getCategoryID() eq category.getCategoryID()}">
                                    <td>${category.getCategoryName()}</td>
                                </c:if>
                            </c:forEach>
                                    <td>
                                        <input type="submit" value="Add" name="action" />
                                    </td>
                    </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </c:if>
                <h2>${requestScope.NOTIFY}</h2>
            <div>
                <a href="MainController?action=ViewCart">View Cart</a>
            </div>
            <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
