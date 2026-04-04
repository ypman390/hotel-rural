<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${habitacion == null ? 'Nueva Habitación' : 'Editar Habitación'} - Hotel Rural</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>${habitacion == null ? '➕ Nueva Habitación' : '✏️ Editar Habitación'}</h1>

    <form action="${pageContext.request.contextPath}/habitaciones" method="post" class="mt-4">
        <input type="hidden" name="accion" value="${habitacion == null ? 'insertar' : 'actualizar'}">
        <c:if test="${habitacion != null}">
            <input type="hidden" name="id" value="${habitacion.id}">
        </c:if>

        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" name="nombre" class="form-control" required
                   value="${habitacion != null ? habitacion.nombre : ''}">
        </div>

        <div class="mb-3">
            <label class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control" rows="3" required>${habitacion != null ? habitacion.descripcion : ''}</textarea>
        </div>

        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="form-label">Precio por noche (€)</label>
                <input type="number" name="precioNoche" class="form-control"
                       step="0.01" min="0" required
                       value="${habitacion != null ? habitacion.precioNoche : ''}">
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Capacidad</label>
                <input type="number" name="capacidad" class="form-control"
                       min="1" required
                       value="${habitacion != null ? habitacion.capacidad : ''}">
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Valoración (0-5)</label>
                <input type="number" name="valoracion" class="form-control"
                       step="0.01" min="0" max="5"
                       value="${habitacion != null ? habitacion.valoracion : '0'}">
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label">Fecha de alta</label>
                <input type="date" name="fechaAlta" class="form-control" required
                       value="${habitacion != null ? habitacion.fechaAlta : ''}">
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Imagen (URL)</label>
                <input type="text" name="imagen" class="form-control"
                       value="${habitacion != null ? habitacion.imagen : ''}">
            </div>
        </div>

        <div class="mb-3 form-check">
            <input type="checkbox" name="disponible" class="form-check-input" id="disponible"
            ${habitacion == null || habitacion.disponible ? 'checked' : ''}>
            <label class="form-check-label" for="disponible">Disponible</label>
        </div>

        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">
                ${habitacion == null ? 'Crear Habitación' : 'Guardar Cambios'}
            </button>
            <a href="${pageContext.request.contextPath}/habitaciones" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>