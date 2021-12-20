<%@ page import="alex.falendysh.epam_exhibition.model.User" %>
<%@ page import="alex.falendysh.epam_exhibition.util.filters.Utils" %>
<%@ page import="alex.falendysh.epam_exhibition.util.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>

<header class="container_header">
    <div class="header_menu">
        <c:set var="activeLink">
            <value>${requestScope[&#39;javax.servlet.forward.request_uri&#39;]}</value>
        </c:set>
        <c:set var="path" value="${pageContext.request.contextPath}"/>
        <a class="${activeLink.equals(path.concat("/")) || activeLink.equals(path.concat("/home")) ? 'active' : 'none'}"
           href="${path}/"><fmt:message key="header.home"/></a>
        <a class="${activeLink.equals(path.concat("/registration")) ? 'active' : 'none'}"
           href="${path}/registrationPage.jsp"><fmt:message key="header.registration"/></a>
        <a class="${activeLink.equals(path.concat("/tariffs")) ? 'active' : 'none'}"
           href="${path}/tariffs"><fmt:message key="header.tariffs"/></a>
        <a class="${activeLink.equals(path.concat("/calculation")) ? 'active' : 'none'}"
           href="${path}/calculation"><fmt:message key="header.calculate_the_cost"/></a>
        <a class="${activeLink.equals(path.concat("/application")) ? 'active' : 'none'}"
           href="${path}/application"><fmt:message key="header.make_an_application"/></a>
        <div class="menu_right">
            <a class="menu_locale" href="?locale=ua">УКР</a>
            <a class="menu_locale" href="?locale=en">ENG</a>
            <%
                User user = Utils.getUserInSession(session);
                if (user == null) {
            %>
            <a class="${activeLink.equals(path.concat("/login")) ? 'active' : 'none'}"
               href="${pageContext.request.contextPath}/login"><fmt:message key="header.sign_in"/></a>
            <a class="${activeLink.equals(path.concat("/registration")) ? 'active' : 'none'}"
               href="${path}/registration"><fmt:message key="header.sign_up"/></a>
            <%
            } else {
                if (user.getRole().equals(Role.ADMIN)) {
            %>
            <a class="${activeLink.equals(path.concat("/manage")) ? 'active' : 'none'}"
               href="${pageContext.request.contextPath}/manage"><fmt:message key="header.manage_applications"/></a>
            <% } %>
            <a class="${activeLink.equals(path.concat("/profile")) ? 'active' : 'none'}"
               href="${path}/profile"><fmt:message key="header.profile"/></a>
            <a href="${path}/logout"><fmt:message key="header.sign_out"/></a>
            <% } %>
        </div>
    </div>
</header>
