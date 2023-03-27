<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Список заказов</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
    <link href="<c:url value="/css/background.css" />" rel="stylesheet">
    <link href="<c:url value="/css/nav.css" />" rel="stylesheet">
    <link href="<c:url value="/css/client_list.css" />" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Система проката авто</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Клиенты
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <form class="form-inline my-2 my-lg-0 dropdown-item" action="<c:url value="/car_rental"/>"
                          method="GET">
                        <input type="hidden" name="command" value="to_clients_list"/>
                        <button type="submit" class="btn btn-link btn-thin dropdown-item">Клиенты</button>
                    </form>
                    <form class="form-inline my-2 my-lg-0 dropdown-item" action="<c:url value="/car_rental"/>"
                          method="GET">
                        <input type="hidden" name="command" value="to_add_client"/>
                        <button type="submit" class="btn btn-link btn-thin dropdown-item">Добавить клиента</button>
                    </form>
                </div>
            </li>
            <li class="nav-item">
                <form class="form-inline my-2 my-lg-0" action="<c:url value="/car_rental"/>" method="GET">
                    <input type="hidden" name="command" value="to_orders_list"/>
                    <button type="submit" class="btn btn-link nav-link active">Заказы</button>
                </form>
            </li>
            <li class="nav-item">
                <form class="form-inline my-2 my-lg-0" action="<c:url value="/car_rental"/>" method="GET">
                    <input type="hidden" name="command" value="to_cars_list"/>
                    <button type="submit" class="btn btn-link nav-link active">Машины</button>
                </form>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" action="<c:url value="/car_rental"/>" method="GET">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit" class="btn btn-outline-light my-2 my-sm-0">Выйти из системы</button>
        </form>
    </div>
</nav>
<div class="card bg-dark text-white form-client-list">
    <div class="card-header">
        Список заказов
    </div>
    <div class="card-body">
        <table class="table table-light table-stripped" id="clients">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Клиент</th>
                <th scope="col">Машина</th>
                <th scope="col">Дата начала</th>
                <th scope="col">Дата конца</th>
                <th scope="col">Действия</th>
                <th scope="col">Печать</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${orders}" var="item" varStatus="currentNumber">
                <tr>
                    <th scope="row"><c:out value="${currentNumber.count}"/></th>
                    <td><c:out
                            value="${clients[item.clientId - 1].surname} ${clients[item.clientId - 1].name} ${clients[item.clientId - 1].patronymic} (id: ${item.clientId})"/></td>
                    <td>${cars[item.carId - 1].model}</td>
                    <td>${item.startDate}</td>
                    <td>${item.startDate}</td>
                    <td>
                        <form class="form-inline my-0 my-lg-0" action="<c:url value="/car_rental"/>" method="POST">
                            <input type="hidden" name="command" value="remove_order"/>
                            <input type="hidden" name="order_id" value="${item.id}">
                            <button type="submit" class="btn btn-dark">Удалить</button>
                        </form>
                    </td>
                    <td>
                        <form class="form-inline my-0 my-lg-0" action="<c:url value="/car_rental"/>" method="POST">
                            <input type="hidden" name="command" value="to_print"/>
                            <input type="hidden" name="order_id" value="${item.id}">
                            <button type="submit" class="btn btn-dark">Напечатать</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        $('#clients').dataTable();
    });
</script>
</html>