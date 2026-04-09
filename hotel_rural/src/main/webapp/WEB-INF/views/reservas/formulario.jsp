<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${reserva == null ? 'Nueva Reserva' : 'Editar Reserva'} - Hotel Rural</title>
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
    <h1>${reserva == null ? '➕ Nueva Reserva' : '✏️ Editar Reserva'}</h1>

    <form action="${pageContext.request.contextPath}/reservas" method="post" class="mt-4">
        <input type="hidden" name="accion" value="${reserva == null ? 'insertar' : 'actualizar'}">
        <c:if test="${reserva != null}">
            <input type="hidden" name="id" value="${reserva.id}">
        </c:if>

        <div class="mb-3">
            <label class="form-label">Habitación</label>
            <select name="habitacionId" class="form-select" required>
                <option value="">Selecciona una habitación</option>
                <c:forEach var="h" items="${habitaciones}">
                    <option value="${h.id}"
                        ${reserva != null && reserva.habitacionId == h.id ? 'selected' : ''}>
                            ${h.nombre} — ${h.precioNoche} €/noche (cap. ${h.capacidad})
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Número de huéspedes</label>
            <input type="number" name="numeroHuespedes" class="form-control"
                   min="1" required
                   value="${reserva != null ? reserva.numeroHuespedes : '1'}">
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label">Fecha de entrada</label>
                <input type="date" name="fechaInicio" class="form-control" required
                       value="${reserva != null ? reserva.fechaInicio : ''}">
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Fecha de salida</label>
                <input type="date" name="fechaFin" class="form-control" required
                       value="${reserva != null ? reserva.fechaFin : ''}">
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">Observaciones</label>
            <textarea name="observaciones" class="form-control"
                      rows="3">${reserva != null ? reserva.observaciones : ''}</textarea>
        </div>

        <!-- Servicios Extra -->
        <c:if test="${not empty servicios}">
            <div class="mb-3">
                <label class="form-label">Servicios Extra</label>
                <div class="border rounded p-3">
                    <c:forEach var="s" items="${servicios}">
                        <div class="form-check mb-2">
                            <input type="checkbox" name="serviciosIds" value="${s.id}"
                                   class="form-check-input" id="servicio_${s.id}">
                            <label class="form-check-label" for="servicio_${s.id}">
                                    ${s.nombre} — ${s.precio} €
                                <small class="text-muted">(${s.duracionMinutos} min)</small>
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">
                ${reserva == null ? 'Crear Reserva' : 'Guardar Cambios'}
            </button>
            <a href="${pageContext.request.contextPath}/reservas" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>