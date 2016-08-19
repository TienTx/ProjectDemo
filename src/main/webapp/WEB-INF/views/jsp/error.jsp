<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="fragments/header.jsp" %>

<section class="clearfix">
    <h1>Error Page</h1>
    <p>${exception.message}</p>
    <!-- Exception: ${exception.message}.
    <c:forEach items="${exception.stackTrace}" var="stackTrace"> 
        ${stackTrace} 
    </c:forEach>
    -->
</section>

<%@include file="fragments/footer.jsp" %>