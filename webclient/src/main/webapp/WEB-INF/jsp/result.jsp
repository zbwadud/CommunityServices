<%-- 
    Document   : result
    Created on : 17/07/2015, 9:46:13 AM
    Author     : Zaid Wadud @ NZQA 2015
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<body>
    <h1>Submitted</h1>
    <ul>
        <li>
            <c:out value="${comment.author}" />            
        </li>
        <li>
            <c:out value="${comment.text}" />
        </li>
    </ul>

    <a href="<spring:url value="/contactform" />">Back</a>
</body>
</html>