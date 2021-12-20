<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <meta charset="UTF-8">
    <title>Sign-Up</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="WEB-INF/css/style.css">
</head>
<body background="WEB-INF/images/images.jpeg">
<h2>Online EPAM Art Gallery ( Project EPAM_Exhibition)</h2>
<div class="form">
    <div id="signup">
        <h1>Sign Up for Free</h1>

        <form action="${pageContext.request.contextPath}/signupServlet" method="post">

            <div class="field-wrap">
                <label>
                    Firstname: <span class="req">*</span>
                </label>
                <input type="text" name="first_name" pattern="[A-Za-z.\s]{1,}"required autocomplete="off" title="Firstname should only contain letters. e.g. john" />
            </div>
            <div class="field-wrap">
                <label>
                    Lastname: <span class="req">*</span>
                </label>
                <input type="text" name="last_name" pattern="[A-Za-z.\s]{1,}"required autocomplete="off" title="Lastname should only contain letters. e.g. john" />
            </div>
            <div class="field-wrap">
                <label>
                    Mobile No:<span class="req">*</span>
                </label>
                <input type="text" name="phone" Pattern= "^[0-9.()-]{10,25}$" required autocomplete="off" title="enter valid mobile no." />
            </div>

            <div class="field-wrap">
                <label>
                    Email: <span class="req">*</span>
                </label>
                <input type="text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required autocomplete="off" title="Enter valid email"/>
            </div>

            <div class="field-wrap">
                <label>
                    Set A Password : <span class="req">*</span>
                </label>
                <label>
                    <input type="password" name="password" required autocomplete="off"/>
                </label>
            </div>


            lets Enjoy of our Exhibition
            <th><small>
                <input type="submit" name="registration" value="registration">
            </small>
        </form>

    </div>

</div>
</body>
</html>