package com.acostaivan.hotel_rural.servlet;

import com.acostaivan.hotel_rural.dao.HabitacionDAO;
import com.acostaivan.hotel_rural.dao.ReservaDAO;
import com.acostaivan.hotel_rural.dao.ServicioExtraDAO;
import com.acostaivan.hotel_rural.dao.impl.HabitacionDAOImpl;
import com.acostaivan.hotel_rural.dao.impl.ReservaDAOImpl;
import com.acostaivan.hotel_rural.dao.impl.ServicioExtraDAOImpl;
import com.acostaivan.hotel_rural.modelo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@WebServlet("/reservas")
public class ReservaServlet extends HttpServlet {

    private ReservaDAO reservaDAO;
    private HabitacionDAO habitacionDAO;
    private ServicioExtraDAO servicioExtraDAO;

    @Override
    public void init() {
        reservaDAO = new ReservaDAOImpl();
        habitacionDAO = new HabitacionDAOImpl();
        servicioExtraDAO = new ServicioExtraDAOImpl();
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
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            case "confirmar":
                confirmar(request, response);
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

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if ("ADMIN".equals(usuario.getRol())) {
            List<ReservaDetalle> reservas = reservaDAO.listarConDetalle();
            request.setAttribute("reservas", reservas);
        } else {
            List<ReservaDetalle> reservas = reservaDAO.listarConDetallePorUsuario(usuario.getId());
            request.setAttribute("reservas", reservas);
        }

        request.getRequestDispatcher("/WEB-INF/views/reservas/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNueva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Habitacion> habitaciones = habitacionDAO.listarDisponibles();
        List<ServicioExtra> servicios = servicioExtraDAO .listarActivos();
        request.setAttribute("habitaciones", habitaciones);
        request.setAttribute("servicios", servicios);
        request.getRequestDispatcher("/WEB-INF/views/reservas/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Reserva reserva = reservaDAO.buscarPorId(id);
        List<Habitacion> habitaciones = habitacionDAO.listarDisponibles();
        List<ServicioExtra> servicios = servicioExtraDAO .listarActivos();
        request.setAttribute("reserva", reserva);
        request.setAttribute("habitaciones", habitaciones);
        request.setAttribute("servicios", servicios);
        request.getRequestDispatcher("/WEB-INF/views/reservas/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        Reserva r = obtenerDatosFormulario(request);
        r.setUsuarioId(usuario.getId());

        // Calcular precio total automáticamente
        Habitacion habitacion = habitacionDAO.buscarPorId(r.getHabitacionId());
        long dias = ChronoUnit.DAYS.between(r.getFechaInicio(), r.getFechaFin());
        BigDecimal precioTotal = habitacion.getPrecioNoche()
                .multiply(BigDecimal.valueOf(dias));
        r.setPrecioTotal(precioTotal);

        reservaDAO.insertar(r);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Reserva r = obtenerDatosFormulario(request);
        r.setId(Integer.parseInt(request.getParameter("id")));

        // Recalcular precio total
        Habitacion habitacion = habitacionDAO.buscarPorId(r.getHabitacionId());
        long dias = ChronoUnit.DAYS.between(r.getFechaInicio(), r.getFechaFin());
        BigDecimal precioTotal = habitacion.getPrecioNoche()
                .multiply(BigDecimal.valueOf(dias));
        r.setPrecioTotal(precioTotal);

        reservaDAO.actualizar(r);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        reservaDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private void confirmar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Reserva r = reservaDAO.buscarPorId(id);
        r.setConfirmada(true);
        reservaDAO.actualizar(r);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private Reserva obtenerDatosFormulario(HttpServletRequest request) {
        Reserva r = new Reserva();
        r.setHabitacionId(Integer.parseInt(request.getParameter("habitacionId")));
        r.setNumeroHuespedes(Integer.parseInt(request.getParameter("numeroHuespedes")));
        r.setFechaInicio(LocalDate.parse(request.getParameter("fechaInicio")));
        r.setFechaFin(LocalDate.parse(request.getParameter("fechaFin")));
        r.setConfirmada(false);
        r.setObservaciones(request.getParameter("observaciones"));
        return r;
    }
}