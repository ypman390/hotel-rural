<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${usuario == null ? 'Nuevo Usuario' : 'Editar Usuario'} - Hotel Rural</title>
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
    <h1>${usuario == null ? '➕ Nuevo Usuario' : '✏️ Editar Usuario'}</h1>

    <c:if test="${error != null}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/usuarios" method="post" class="mt-4">
        <input type="hidden" name="accion" value="${usuario == null ? 'insertar' : 'actualizar'}">
        <c:if test="${usuario != null}">
            <input type="hidden" name="id" value="${usuario.id}">
        </c:if>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" required
                       value="${usuario != null ? usuario.nombre : ''}">
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" required
                       value="${usuario != null ? usuario.email : ''}">
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label">Contraseña</label>
                <input type="password" name="password" class="form-control"
                ${usuario == null ? 'required' : ''}
                       placeholder="${usuario != null ? 'Dejar vacío para no cambiar' : ''}">
            </div>
            <div class="col-md-3 mb-3">
                <label class="form-label">Rol</label>
                <select name="rol" class="form-select" required>
                    <option value="CLIENTE" ${usuario != null && usuario.rol == 'CLIENTE' ? 'selected' : ''}>CLIENTE</option>
                    <option value="ADMIN"   ${usuario != null && usuario.rol == 'ADMIN'   ? 'selected' : ''}>ADMIN</option>
                </select>
            </div>
            <div class="col-md-3 mb-3">
                <label class="form-label">Edad</label>
                <input type="number" name="edad" class="form-control"
                       min="18" max="120" required
                       value="${usuario != null ? usuario.edad : ''}">
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label">Saldo (€)</label>
                <input type="number" name="saldo" class="form-control"
                       step="0.01" min="0" required
                       value="${usuario != null ? usuario.saldo : '0.00'}">
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Fecha de registro</label>
                <input type="date" name="fechaRegistro" class="form-control" required
                       value="${usuario != null ? usuario.fechaRegistro : ''}">
            </div>
        </div>

        <div class="mb-3 form-check">
            <input type="checkbox" name="activo" class="form-check-input" id="activo"
            ${usuario == null || usuario.activo ? 'checked' : ''}>
            <label class="form-check-label" for="activo">Activo</label>
        </div>

        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">
                ${usuario == null ? 'Crear Usuario' : 'Guardar Cambios'}
            </button>
            <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>