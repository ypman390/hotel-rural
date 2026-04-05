<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Hotel Rural</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .registro-card {
            max-width: 480px;
            margin: 60px auto;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: #198754;
            color: white;
            text-align: center;
            border-radius: 12px 12px 0 0 !important;
            padding: 24px;
        }
    </style>
</head>
<body>
<div class="registro-card">
    <div class="card">
        <div class="card-header">
            <h2>🏡 Hotel Rural</h2>
            <p class="mb-0">Crear nueva cuenta</p>
        </div>
        <div class="card-body p-4">

            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("error") %>
            </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/registro" method="post">
                <div class="mb-3">
                    <label class="form-label">Nombre completo</label>
                    <input type="text" name="nombre" class="form-control"
                           placeholder="Tu nombre" required autofocus>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control"
                           placeholder="tu@email.com" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="password" class="form-control"
                           placeholder="••••••••" required minlength="4">
                </div>
                <div class="mb-3">
                    <label class="form-label">Edad</label>
                    <input type="number" name="edad" class="form-control"
                           min="18" max="120" required placeholder="Mayor de 18 años">
                </div>
                <div class="d-grid mt-4">
                    <button type="submit" class="btn btn-success btn-lg">
                        Crear cuenta
                    </button>
                </div>
            </form>

        </div>
        <div class="card-footer text-center py-3">
            <small>¿Ya tienes cuenta?
                <a href="${pageContext.request.contextPath}/login">Inicia sesión</a>
            </small>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>