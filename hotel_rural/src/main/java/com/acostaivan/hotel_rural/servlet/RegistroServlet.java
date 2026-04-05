package com.acostaivan.hotel_rural.servlet;

import com.acostaivan.hotel_rural.dao.UsuarioDAO;
import com.acostaivan.hotel_rural.dao.impl.UsuarioDAOImpl;
import com.acostaivan.hotel_rural.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registro.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        // Verificar si el email ya existe
        if (usuarioDAO.existeEmail(email)) {
            request.setAttribute("error", "El email ya está registrado");
            request.getRequestDispatcher("/WEB-INF/views/registro.jsp")
                    .forward(request, response);
            return;
        }

        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setEmail(email);
        usuario.setPassword(request.getParameter("password"));
        usuario.setRol("CLIENTE");
        usuario.setEdad(Integer.parseInt(request.getParameter("edad")));
        usuario.setSaldo(BigDecimal.ZERO);
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setActivo(true);

        usuarioDAO.insertar(usuario);

        // Redirigir al login con mensaje de éxito
        request.getSession().setAttribute("mensaje", "Registro exitoso, ya puedes iniciar sesión");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}