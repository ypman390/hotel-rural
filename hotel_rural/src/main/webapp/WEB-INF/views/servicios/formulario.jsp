<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${servicio == null ? 'Nuevo Servicio' : 'Editar Servicio'} - Hotel Rural</title>
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
    <h1>${servicio == null ? '➕ Nuevo Servicio' : '✏️ Editar Servicio'}</h1>

    <form action="${pageContext.request.contextPath}/servicios" method="post" class="mt-4">
        <input type="hidden" name="accion" value="${servicio == null ? 'insertar' : 'actualizar'}">
        <c:if test="${servicio != null}">
            <input type="hidden" name="id" value="${servicio.id}">
        </c:if>

        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" name="nombre" class="form-control" required
                   value="${servicio != null ? servicio.nombre : ''}">
        </div>

        <div class="mb-3">
            <label class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control" rows="3"
                      required>${servicio != null ? servicio.descripcion : ''}</textarea>
        </div>

        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="form-label">Precio (€)</label>
                <input type="number" name="precio" class="form-control"
                       step="0.01" min="0" required
                       value="${servicio != null ? servicio.precio : ''}">
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Duración (minutos)</label>
                <input type="number" name="duracionMinutos" class="form-control"
                       min="1" required
                       value="${servicio != null ? servicio.duracionMinutos : ''}">
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Fecha de creación</label>
                <input type="date" name="fechaCreacion" class="form-control" required
                       value="${servicio != null ? servicio.fechaCreacion : ''}">
            </div>
        </div>

        <div class="mb-3 form-check">
            <input type="checkbox" name="activo" class="form-check-input" id="activo"
            ${servicio == null || servicio.activo ? 'checked' : ''}>
            <label class="form-check-label" for="activo">Activo</label>
        </div>

        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">
                ${servicio == null ? 'Crear Servicio' : 'Guardar Cambios'}
            </button>
            <a href="${pageContext.request.contextPath}/servicios" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>