package com.acostaivan.hotel_rural.servlet;

import com.acostaivan.hotel_rural.dao.UsuarioDAO;
import com.acostaivan.hotel_rural.dao.impl.UsuarioDAOImpl;
import com.acostaivan.hotel_rural.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si ya hay sesión activa redirigir según rol
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuarioLogueado") != null) {
            redirigirPorRol(request, response, (Usuario) session.getAttribute("usuarioLogueado"));
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email    = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.login(email, password);

        if (usuario != null) {
            // Crear sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);
            session.setAttribute("rol", usuario.getRol());
            session.setMaxInactiveInterval(30 * 60); // 30 minutos

            redirigirPorRol(request, response, usuario);
        } else {
            // Login fallido
            request.setAttribute("error", "Email o contraseña incorrectos");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(request, response);
        }
    }

    private void redirigirPorRol(HttpServletRequest request, HttpServletResponse response,
                                 Usuario usuario) throws IOException {
        if ("ADMIN".equals(usuario.getRol())) {
            response.sendRedirect(request.getContextPath() + "/habitaciones");
        } else {
            response.sendRedirect(request.getContextPath() + "/habitaciones");
        }
    }
}