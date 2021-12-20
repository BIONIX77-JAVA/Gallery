<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Edit Profile"/>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div class="page-content">
            <div class="form">
                <form name="form" id="update_user" action="${pageContext.request.contextPath}/profile/edit"
                      method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="hidden" name="role" value="${user.role}">
                    <label for="login"><fmt:message key="login"/></label>
                    <input id="login" type="text" name="login" value="${user.login}" pattern="[A-Za-z]{3,20}" required/><br>
                    <label for="email"><fmt:message key="email"/></label>
                    <input id="email" type="email" name="email" value="${user.email}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required/><br>
                    <label for="name"><fmt:message key="name"/></label>
                    <input id="name" type="text" name="name" value="${user.name}" pattern="[A-ZА-Я][a-zа-я]+" required/><br>
                    <label for="surname"><fmt:message key="surname"/></label>
                    <input id="surname" type="text" name="surname" value="${user.surname}" pattern="[A-ZА-Я][a-zа-я]+"
                           required/><br>
                    <label for="pass1"><fmt:message key="edit_user.write_password"/></label>
                    <input id="pass1" type="password" name="newPassword1"><br>
                    <label for="pass2"><fmt:message key="edit_user.repeat_password"/></label>
                    <input id="pass2" type="password" name="newPassword2"><br>
                    <input type="submit" value="<fmt:message key="edit_user.confirm_changes"/>"
                           onclick="return check()">
                </form>
                <a href="${pageContext.request.contextPath}/profile"><fmt:message key="edit_user.cancel"/></a>
            </div>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
    function check() {
        const form = document.getElementById("update_user");
        if (confirm('<fmt:message key="are_you_sure"/>')) {
            form.submit()
        }
    }
</script>
</body>
</html>
