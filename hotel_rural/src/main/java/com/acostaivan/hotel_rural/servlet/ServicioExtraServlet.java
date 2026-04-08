package com.acostaivan.hotel_rural.servlet;

import com.acostaivan.hotel_rural.dao.ServicioExtraDAO;
import com.acostaivan.hotel_rural.dao.impl.ServicioExtraDAOImpl;
import com.acostaivan.hotel_rural.modelo.ServicioExtra;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/servicios")
public class ServicioExtraServlet extends HttpServlet {

    private ServicioExtraDAO servicioDAO;

    @Override
    public void init() {
        servicioDAO = new ServicioExtraDAOImpl();
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
        List<ServicioExtra> servicios = servicioDAO.listarTodos();
        request.setAttribute("servicios", servicios);
        request.getRequestDispatcher("/WEB-INF/views/servicios/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/servicios/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServicioExtra servicio = servicioDAO.buscarPorId(id);
        request.setAttribute("servicio", servicio);
        request.getRequestDispatcher("/WEB-INF/views/servicios/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServicioExtra s = obtenerDatosFormulario(request);
        servicioDAO.insertar(s);
        response.sendRedirect(request.getContextPath() + "/servicios");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServicioExtra s = obtenerDatosFormulario(request);
        s.setId(Integer.parseInt(request.getParameter("id")));
        servicioDAO.actualizar(s);
        response.sendRedirect(request.getContextPath() + "/servicios");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        servicioDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/servicios");
    }

    private void cambiarActivo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServicioExtra s = servicioDAO.buscarPorId(id);
        s.setActivo(!s.isActivo());
        servicioDAO.actualizar(s);
        response.sendRedirect(request.getContextPath() + "/servicios");
    }

    private ServicioExtra obtenerDatosFormulario(HttpServletRequest request) {
        ServicioExtra s = new ServicioExtra();
        s.setNombre(request.getParameter("nombre"));
        s.setDescripcion(request.getParameter("descripcion"));
        s.setPrecio(new BigDecimal(request.getParameter("precio")));
        s.setDuracionMinutos(Integer.parseInt(request.getParameter("duracionMinutos")));
        s.setFechaCreacion(LocalDate.parse(request.getParameter("fechaCreacion")));
        s.setActivo(request.getParameter("activo") != null);
        return s;
    }
}