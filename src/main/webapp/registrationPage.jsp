<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Sign Up"/>
<body>
<div class="container">
    <main class="container_main">
        <div class="page-content">
            <div class="form">
                <p style="color: red;"><c:if test="${not empty errorMessage}"><fmt:message
                        key="registration.error"/></c:if></p>
                <p style="color: red;"><c:if test="${not empty errorPass}"><fmt:message
                        key="registration.error_pass"/></c:if></p>
                <form method="POST" action="${pageContext.request.contextPath}/registration">
                    <label for="login"><fmt:message key="login"/></label>
                    <input id="login" type="text" name="login" value="${user.login}" pattern="[A-Za-z]{3,20}"/>
                    <label for="email"><fmt:message key="email"/></label>
                    <input id="email" type="email" name="email" value="${user.email}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
                    <label for="name"><fmt:message key="name"/></label>
                    <input id="name" type="text" name="name" value="${user.name}" pattern="[A-ZА-Я][a-zа-я]+"/>
                    <label for="surname"><fmt:message key="surname"/></label>
                    <input id="surname" type="text" name="surname" value="${user.surname}" pattern="[A-ZА-Я][a-zа-я]+"/>
                    <label for="pass1"><fmt:message key="password"/></label>
                    <input id="pass1" type="password" name="password"/>
                    <label for="pass2"><fmt:message key="repeat_password"/></label>
                    <input id="pass2" type="password" name="password2"/>
                    <input type="submit" value="<fmt:message key="registration.sign_up"/>"/>
                </form>
            </div>
        </div>
    </main>
</div>
</body>
</html>