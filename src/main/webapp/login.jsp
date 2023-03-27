<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Spartan&display=swap" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="format-detection" content="telephone=no"/>
    <title>Войти в систему</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link href="<c:url value="/css/login.css" />" rel="stylesheet">
    <link href="<c:url value="/css/background.css" />" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
</head>

<body>
    <div class="card text-white bg-dark login-form">
        <div class="card-header">
            Войти в систему
        </div>
        <div class="card-body">
            <form action="<c:url value="/car_rental"/>" method="POST" class="needs-validation"
                  novalidate
                  accept-charset="utf-8">
                <div class="form-group">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input id="login" type="text" class="form-control" name="login" placeholder="Логин" required
                               minlength="5" maxlength="50">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input id="password" type="password" class="form-control" name="password" placeholder="Пароль"
                               required minlength="4" maxlength="255">
                    </div>
                </div>

                <div class="form-group">

                    <p style="color: red">${feedback}</p>
                    <input type="hidden" name="command" value="login"/>
                    <button type="submit" class="btn btn-light">Войти</button>

                </div>
            </form>
        </div>
    </div>

</body>
</html>

<script src="<c:url value="/js/common.js" />"></script>
