<%-- 
    Document   : footer
    Created on : Jun 29, 2016, 4:28:18 PM
    Author     : zOzDarKzOz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

        <footer class="clearfix"></footer>
        <section id="back-to-top" title="Back to top">&uarr;</section>

        <spring:url value="/resources/core/js/jquery-2.2.4.min.js" var="jquery" />
        <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
        <spring:url value="/resources/core/js/html5.js" var="html5" />
        <spring:url value="/resources/core/js/slick.min.js" var="slickJs" />
        <spring:url value="/resources/core/js/script.js" var="script" />
        <script src="${jquery}"></script>
        <script src="${bootstrapJs}"></script>
        <script src="${html5}"></script>
        <script src="${slickJs}"></script>
        <script src="${script}"></script>
    </body>
</html>
