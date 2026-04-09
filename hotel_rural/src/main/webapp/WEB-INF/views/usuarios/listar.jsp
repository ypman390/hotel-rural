<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios - Hotel Rural</title>
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
            <form action="${pageContext.request.contextPath}/usuarios" method="get" class="row g-3">
                <input type="hidden" name="accion" value="buscar">
                <div class="col-md-4">
                    <label class="form-label">Nombre</label>
                    <input type="text" name="nombre" class="form-control" placeholder="Buscar por nombre">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Rol</label>
                    <select name="rol" class="form-select">
                        <option value="">Todos</option>
                        <option value="ADMIN">ADMIN</option>
                        <option value="CLIENTE">CLIENTE</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Activo</label>
                    <select name="activo" class="form-select">
                        <option value="">Todos</option>
                        <option value="true">Sí</option>
                        <option value="false">No</option>
                    </select>
                </div>
                <div class="col-md-2 d-flex align-items-end gap-2">
                    <button type="submit" class="btn btn-primary">🔍 Buscar</button>
                    <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-secondary">↺</a>
                </div>
            </form>
        </div>
    </div>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>👤 Usuarios</h1>
        <a href="${pageContext.request.contextPath}/usuarios?accion=nuevo"
           class="btn btn-success">+ Nuevo Usuario</a>
    </div>

    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Rol</th>
            <th>Edad</th>
            <th>Saldo</th>
            <th>Fecha registro</th>
            <th>Activo</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${usuarios}">
            <tr>
                <td>${u.id}</td>
                <td>${u.nombre}</td>
                <td>${u.email}</td>
                <td>
                        <span class="badge ${u.rol == 'ADMIN' ? 'bg-danger' : 'bg-primary'}">
                                ${u.rol}
                        </span>
                </td>
                <td>${u.edad}</td>
                <td>${u.saldo} €</td>
                <td>${u.fechaRegistro}</td>
                <td>
                    <c:choose>
                        <c:when test="${u.activo}">
                            <span class="badge bg-success">Sí</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-danger">No</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/usuarios?accion=editar&id=${u.id}"
                       class="btn btn-warning btn-sm">Editar</a>
                    <a href="${pageContext.request.contextPath}/usuarios?accion=eliminar&id=${u.id}"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Eliminar este usuario?')">Eliminar</a>
                    <a href="${pageContext.request.contextPath}/usuarios?accion=cambiarActivo&id=${u.id}"
                       class="btn btn-secondary btn-sm">
                            ${u.activo ? 'Desactivar' : 'Activar'}
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>