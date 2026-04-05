package com.acostaivan.hotel_rural.servlet;

import com.acostaivan.hotel_rural.dao.HabitacionDAO;
import com.acostaivan.hotel_rural.dao.impl.HabitacionDAOImpl;
import com.acostaivan.hotel_rural.modelo.Habitacion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/habitaciones")
public class HabitacionServlet extends HttpServlet {

    private HabitacionDAO habitacionDAO;

    @Override
    public void init() {
        habitacionDAO = new HabitacionDAOImpl();
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
            case "nueva":
                mostrarFormularioNueva(request, response);
                break;
            case "disponibilidad":
                cambiarDisponibilidad(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
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
        List<Habitacion> habitaciones = habitacionDAO.listarTodas();
        request.setAttribute("habitaciones", habitaciones);
        request.getRequestDispatcher("/WEB-INF/views/habitaciones/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNueva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/habitaciones/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Habitacion habitacion = habitacionDAO.buscarPorId(id);
        request.setAttribute("habitacion", habitacion);
        request.getRequestDispatcher("/WEB-INF/views/habitaciones/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Habitacion h = obtenerDatosFormulario(request);
        habitacionDAO.insertar(h);
        response.sendRedirect(request.getContextPath() + "/habitaciones");
    }

    private void cambiarDisponibilidad(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean disponible = Boolean.parseBoolean(request.getParameter("disponible"));
        habitacionDAO.cambiarDisponibilidad(id, disponible);
        response.sendRedirect(request.getContextPath() + "/habitaciones");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Habitacion h = obtenerDatosFormulario(request);
        h.setId(Integer.parseInt(request.getParameter("id")));
        habitacionDAO.actualizar(h);
        response.sendRedirect(request.getContextPath() + "/habitaciones");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        habitacionDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/habitaciones");
    }

    private Habitacion obtenerDatosFormulario(HttpServletRequest request) {
        Habitacion h = new Habitacion();
        h.setNombre(request.getParameter("nombre"));
        h.setDescripcion(request.getParameter("descripcion"));
        h.setPrecioNoche(new BigDecimal(request.getParameter("precioNoche")));
        h.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
        h.setFechaAlta(LocalDate.parse(request.getParameter("fechaAlta")));
        h.setDisponible(request.getParameter("disponible") != null);
        h.setImagen(request.getParameter("imagen"));
        String valoracion = request.getParameter("valoracion");
        h.setValoracion(valoracion != null && !valoracion.isEmpty()
                ? new BigDecimal(valoracion) : BigDecimal.ZERO);
        return h;
    }
}