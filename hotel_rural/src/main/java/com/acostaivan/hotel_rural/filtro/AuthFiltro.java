package com.acostaivan.hotel_rural.filtro;

import com.acostaivan.hotel_rural.modelo.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest   = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String url = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // Rutas públicas que no necesitan login
        boolean esRutaPublica = url.equals(contextPath + "/login")
                || url.equals(contextPath + "/registro")
                || url.equals(contextPath + "/logout")
                || url.equals(contextPath + "/")
                || url.contains("/css/")
                || url.contains("/js/")
                || url.contains("/img/");

        if (esRutaPublica) {
            chain.doFilter(request, response);
            return;
        }

        // Verificar sesión
        HttpSession session = httpRequest.getSession(false);
        Usuario usuario = (session != null)
                ? (Usuario) session.getAttribute("usuarioLogueado")
                : null;

        if (usuario == null) {
            // No hay sesión → redirigir al login
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

        // Rutas solo para ADMIN
        boolean esRutaAdmin = url.contains("/usuarios");

        if (esRutaAdmin && !"ADMIN".equals(usuario.getRol())) {
            httpResponse.sendRedirect(contextPath + "/habitaciones");
            return;
        }

        chain.doFilter(request, response);
    }
}