<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Servicios Extra - Hotel Rural</title>
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
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>🧾 Servicios Extra</h1>
        <c:if test="${sessionScope.rol == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/servicios?accion=nuevo"
               class="btn btn-success">+ Nuevo Servicio</a>
        </c:if>
    </div>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Duración</th>
            <th>Fecha creación</th>
            <th>Activo</th>
            <c:if test="${sessionScope.rol == 'ADMIN'}">
                <th>Acciones</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${servicios}">
            <tr>
                <td>${s.id}</td>
                <td>${s.nombre}</td>
                <td>${s.descripcion}</td>
                <td>${s.precio} €</td>
                <td>${s.duracionMinutos} min</td>
                <td>${s.fechaCreacion}</td>
                <td>
                    <c:choose>
                        <c:when test="${s.activo}">
                            <span class="badge bg-success">Sí</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-danger">No</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <c:if test="${sessionScope.rol == 'ADMIN'}">
                    <td>
                        <a href="${pageContext.request.contextPath}/servicios?accion=editar&id=${s.id}"
                           class="btn btn-warning btn-sm">Editar</a>
                        <a href="${pageContext.request.contextPath}/servicios?accion=eliminar&id=${s.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Eliminar este servicio?')">Eliminar</a>
                        <a href="${pageContext.request.contextPath}/servicios?accion=cambiarActivo&id=${s.id}"
                           class="btn btn-secondary btn-sm">
                                ${s.activo ? 'Desactivar' : 'Activar'}
                        </a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>