<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Hotel Rural</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-card {
            max-width: 420px;
            margin: 100px auto;
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
<div class="login-card">
    <div class="card">
        <div class="card-header">
            <h2>🏡 Hotel Rural</h2>
            <p class="mb-0">Accede a tu cuenta</p>
        </div>
        <div class="card-body p-4">

            <!-- Error de login -->
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("error") %>
            </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control"
                           placeholder="tu@email.com" required autofocus>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="password" class="form-control"
                           placeholder="••••••••" required>
                </div>
                <div class="d-grid mt-4">
                    <button type="submit" class="btn btn-success btn-lg">
                        Iniciar sesión
                    </button>
                </div>
            </form>

        </div>
        <div class="card-footer text-center text-muted py-3">
            <small>¿No tienes cuenta?
                <a href="${pageContext.request.contextPath}/registro">Regístrate aquí</a>
            </small>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>