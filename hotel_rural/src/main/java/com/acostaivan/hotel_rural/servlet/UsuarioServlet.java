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
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "buscar":
                buscar(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            case "cambiarActivo":
                cambiarActivo(request, response);
                break;
            default:
                listar(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "insertar":
                insertar(request, response);
                break;
            case "actualizar":
                actualizar(request, response);
                break;
            default:
                listar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/WEB-INF/views/usuarios/listar.jsp")
                .forward(request, response);
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String rol = request.getParameter("rol");
        String activoStr = request.getParameter("activo");

        Boolean activo = (activoStr != null && !activoStr.isEmpty())
                ? Boolean.parseBoolean(activoStr) : null;

        List<Usuario> usuarios = usuarioDAO.buscar(nombre, rol, activo);
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("busqueda", true);
        request.getRequestDispatcher("/WEB-INF/views/usuarios/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/usuarios/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.buscarPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/WEB-INF/views/usuarios/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Usuario u = obtenerDatosFormulario(request);
        if (usuarioDAO.existeEmail(u.getEmail())) {
            try {
                request.setAttribute("error", "El email ya está registrado");
                request.getRequestDispatcher("/WEB-INF/views/usuarios/formulario.jsp")
                        .forward(request, response);
            } catch (ServletException e) {
                System.err.println("Error: " + e.getMessage());
            }
            return;
        }
        usuarioDAO.insertar(u);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Usuario u = obtenerDatosFormulario(request);
        u.setId(Integer.parseInt(request.getParameter("id")));
        usuarioDAO.actualizar(u);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private void cambiarActivo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario u = usuarioDAO.buscarPorId(id);
        u.setActivo(!u.isActivo());
        usuarioDAO.actualizar(u);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private Usuario obtenerDatosFormulario(HttpServletRequest request) {
        Usuario u = new Usuario();
        u.setNombre(request.getParameter("nombre"));
        u.setEmail(request.getParameter("email"));
        u.setPassword(request.getParameter("password"));
        u.setRol(request.getParameter("rol"));
        u.setEdad(Integer.parseInt(request.getParameter("edad")));
        u.setSaldo(new BigDecimal(request.getParameter("saldo")));
        u.setFechaRegistro(LocalDate.parse(request.getParameter("fechaRegistro")));
        u.setActivo(request.getParameter("activo") != null);
        return u;
    }
}