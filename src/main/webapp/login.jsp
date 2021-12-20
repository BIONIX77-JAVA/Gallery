
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${param.lang}">
<head>
    <title>Exhibition Calendar | Login</title>

</head>
<body>
<header>
    <jsp:include page="WEB-INF/views/_header.jsp"/>
</header>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <h1><fmt:message key="headline.login"/></h1>

            <c:if test="${requestScope.wrongInput}">
                <p class="text-danger" role="alert">
                    <fmt:message key="message.wrongInput"/>
                </p>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/login" role="form" class="form-horizontal">
                <div class="form-group">
                    <label for="InputEmail" class="control-label"><fmt:message key="form.email"/></label>
                    <input type="email" name="email" class="form-control" id="InputEmail"
                           placeholder="<fmt:message key="placeholder.email"/>">
                </div>
                <div class="form-group">
                    <label for="InputPassword" class="control-label"><fmt:message key="form.password"/></label>
                    <input type="password" name="password" class="form-control" id="InputPassword"
                           placeholder="<fmt:message key="placeholder.password"/>">
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="ref.submit"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
