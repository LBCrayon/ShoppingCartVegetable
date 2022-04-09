<%-- 
    Document   : viewCart
    Created on : Aug 24, 2021, 1:18:19 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${user eq null}">
            <c:redirect url="MainController?action=Home"/>
        </c:if>
        <c:if test="${user.getRoleID() eq 'AD002'}">
            <c:redirect url="MainController?action=ViewDashboard"/>
        </c:if>
        <h1>
            This is your order:
        </h1>
        <div class="table">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${cart != null}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>VegetableID</th>
                            <th>Vegetable Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Sale</th>
                            <th>Total</th>
                            <th>Remove</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="list" varStatus="counter" items="${sessionScope.CART.cart}">
                            <c:set var="vegetable" value="${list.value}"/>
                            <c:set var="price" value="${vegetable.getPrice() * 
                                                        vegetable.getQuanity()}"/>
                            <c:set var="sale" value="${vegetable.getPrice() * 
                                                       vegetable.getQuanity()* 
                                                       (vegetable.getSale()/100)}"/>
                            <c:set var="total" value="${total = total + price - 
                                                        sale}"/>
                        <tr>
                            <form action="MainController" method="POST">
                            <td>${counter.count}</td>
                            <td>
                                <input type="text" name="vegetableID" value="${vegetable.getVegetableID()}" readonly=""/>
                            </td>
                            <td>${vegetable.getVegetableName()}</td>
                            <td>
                                <input type="number" name="quanity" value="${vegetable.getQuanity()}" />
                            </td>
                            <td>${price}</td>
                            <td>${sale}</td>
                            <td>${price - sale}</td>
                            <td><input type="submit" value="Remove" name="action"/></td>
                            <td><input type="submit" value="Update" name="action" /></td>
                            </form>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <h1>Total: ${total}</h1>
                
                <form action="MainController" method="POST">
                    <input type="hidden" name="total" value="${total}" />
                    <input type="submit" value="Check Out" name="action" />
                </form>
            </c:if>
        </div>
            <div>
                <h1>${requestScope.MESSAGE}</h1>
                <a href="MainController?action=ViewCar">Order more vegetable</a>
            </div>
    </body>
</html>
