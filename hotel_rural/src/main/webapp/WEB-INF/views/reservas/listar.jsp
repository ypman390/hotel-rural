<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservas - Hotel Rural</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/habitaciones">
            🏡 Hotel Rural
        </a>
        <div class="navbar-nav ms-auto">
            <span class="navbar-text text-white me-3">
                👤 ${sessionScope.usuarioLogueado.nombre}
                <span class="badge bg-light text-success">${sessionScope.rol}</span>
            </span>
            <a class="btn btn-outline-light btn-sm"
               href="${pageContext.request.contextPath}/logout">
                Cerrar sesión
            </a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <!-- Formulario búsqueda -->
    <div class="card mb-4">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/reservas" method="get" class="row g-3">
                <input type="hidden" name="accion" value="buscar">
                <c:if test="${sessionScope.rol == 'ADMIN'}">
                <div class="col-md-2">
                    <label class="form-label">ID Usuario</label>
                    <input type="number" name="usuarioId" class="form-control" min="1" placeholder="Ej: 1">
                </div>
                <div class="col-md-2">
                    <label class="form-label">ID Habitación</label>
                    <input type="number" name="habitacionId" class="form-control" min="1" placeholder="Ej: 1">
                </div>
                </c:if>
                <div class="col-md-2">
                    <label class="form-label">Estado</label>
                    <select name="confirmada" class="form-select">
                        <option value="">Todas</option>
                        <option value="true">Confirmada</option>
                        <option value="false">Pendiente</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Fecha entrada desde</label>
                    <input type="date" name="fechaInicio" class="form-control">
                </div>
                <div class="col-md-2">
                    <label class="form-label
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>📅 Reservas</h1>
        <a href="${pageContext.request.contextPath}/reservas?accion=nueva"
           class="btn btn-success">+ Nueva Reserva</a>
    </div>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <c:if test="${sessionScope.rol == 'ADMIN'}">
                <th>Cliente</th>
            </c:if>
            <th>Habitación</th>
            <th>Huéspedes</th>
            <th>Entrada</th>
            <th>Salida</th>
            <th>Precio total</th>
            <th>Estado</th>
            <th>Observaciones</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="r" items="${reservas}">
            <tr>
                <td>${r.id}</td>
                <c:if test="${sessionScope.rol == 'ADMIN'}">
                    <td>${r.nombreUsuario}</td>
                </c:if>
                <td>${r.nombreHabitacion}</td>
                <td>${r.numeroHuespedes}</td>
                <td>${r.fechaInicio}</td>
                <td>${r.fechaFin}</td>
                <td>${r.precioTotal} €</td>
                <td>
                    <c:choose>
                        <c:when test="${r.confirmada}">
                            <span class="badge bg-success">Confirmada</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-warning text-dark">Pendiente</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${r.observaciones}</td>
                <td>
                    <c:if test="${sessionScope.rol == 'ADMIN'}">
                        <c:if test="${!r.confirmada}">
                            <a href="${pageContext.request.contextPath}/reservas?accion=confirmar&id=${r.id}"
                               class="btn btn-success btn-sm">Confirmar</a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/reservas?accion=editar&id=${r.id}"
                           class="btn btn-warning btn-sm">Editar</a>
                        <a href="${pageContext.request.contextPath}/reservas?accion=eliminar&id=${r.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Eliminar esta reserva?')">Eliminar</a>
                    </c:if>
                    <c:if test="${sessionScope.rol == 'CLIENTE' && !r.confirmada}">
                        <a href="${pageContext.request.contextPath}/reservas?accion=editar&id=${r.id}"
                           class="btn btn-warning btn-sm">Editar</a>
                        <a href="${pageContext.request.contextPath}/reservas?accion=eliminar&id=${r.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Cancelar esta reserva?')">Cancelar</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>