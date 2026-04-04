<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Habitaciones - Hotel Rural</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>🛏️ Habitaciones</h1>
        <a href="${pageContext.request.contextPath}/habitaciones?accion=nueva"
           class="btn btn-success">+ Nueva Habitación</a>
    </div>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio/noche</th>
            <th>Capacidad</th>
            <th>Disponible</th>
            <th>Valoración</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="h" items="${habitaciones}">
            <tr>
                <td>${h.id}</td>
                <td>${h.nombre}</td>
                <td>${h.descripcion}</td>
                <td>${h.precioNoche} €</td>
                <td>${h.capacidad}</td>
                <td>
                    <c:choose>
                        <c:when test="${h.disponible}">
                            <span class="badge bg-success">Sí</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-danger">No</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${h.valoracion}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/habitaciones?accion=editar&id=${h.id}"
                       class="btn btn-warning btn-sm">Editar</a>
                    <a href="${pageContext.request.contextPath}/habitaciones?accion=eliminar&id=${h.id}"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Eliminar esta habitación?')">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>