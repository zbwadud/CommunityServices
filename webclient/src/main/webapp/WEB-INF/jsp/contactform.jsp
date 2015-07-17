<%-- 
    Document   : contactform
    Created on : Jun 28, 2015, 10:24:14 PM
    Author     : Zaid Wadud
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1>Form</h1>
    <%--<a href="<spring:url value="/user_list.html" />"><spring:message code="user.list" /></a>--%>
    <form:form method="POST" action="/contactform" modelAttribute="comment">
        <form:errors path="" element="div" />
        <div>
            <p>"Author:"</p> <form:input  path="author" />
        </div>
        <div>            
            <p>"Message:"</p> <form:textarea path="text" />          
        </div>        
        <div>
            <input type="submit" />
        </div>
    </form:form>
</body>
</html>


