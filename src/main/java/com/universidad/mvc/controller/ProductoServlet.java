package com.universidad.mvc.controller;

import com.universidad.mvc.model.Producto;
import com.universidad.mvc.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

import java.io.IOException;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private final ProductoService service = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)

            throws ServletException, IOException {

        if (!verificarSesion(request, response)) {
            return;
        }

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "listar":
                listar(request, response);
                break;

            case "formulario":
                mostrarFormulario(request, response);
                break;

            case "editar":
                mostrarEdicion(request, response);
                break;

            case "eliminar":
                eliminar(request, response);
                break;

            default:
                response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        if (!verificarSesion(request, response)) {
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {

            guardar(request, response);

        } else if ("actualizar".equals(accion)) {

            actualizar(request, response);

        } else {

            response.sendError(400);
        }
    }

    private void listar(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "productos",
                service.obtenerTodos()
        );

        String mensaje = request.getParameter("mensaje");

        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
        }

        forward(
                request,
                response,
                "/WEB-INF/views/lista.jsp"
        );
    }

    private void mostrarFormulario(HttpServletRequest request,
                                   HttpServletResponse response)
            throws ServletException, IOException {

        forward(
                request,
                response,
                "/WEB-INF/views/formulario.jsp"
        );
    }

    private void mostrarEdicion(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        Producto producto = service.obtenerPorId(id);

        request.setAttribute("producto", producto);

        forward(
                request,
                response,
                "/WEB-INF/views/formulario.jsp"
        );
    }

    private void guardar(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String precioStr = request.getParameter("precio");
        String stockStr = request.getParameter("stock");
        String categoria = request.getParameter("categoria");

        Map<String, String> errores = new LinkedHashMap<>();

        // Validación nombre
        if (nombre == null || nombre.trim().isEmpty()) {

            errores.put(
                    "nombre",
                    "El nombre del producto es obligatorio."
            );

        } else if (nombre.trim().length() > 100) {

            errores.put(
                    "nombre",
                    "El nombre no debe superar los 100 caracteres."
            );
        }

        // Validación precio
        double precio = 0;

        try {

            precio = Double.parseDouble(precioStr);

            if (precio < 0) {

                errores.put(
                        "precio",
                        "El precio no puede ser negativo."
                );
            }

        } catch (NumberFormatException e) {

            errores.put(
                    "precio",
                    "El precio debe ser un número válido (ej: 19.99)."
            );
        }

        // Validación stock
        int stock = 0;

        try {

            stock = Integer.parseInt(stockStr);

            if (stock < 0) {

                errores.put(
                        "stock",
                        "El stock no puede ser negativo."
                );
            }

        } catch (NumberFormatException e) {

            errores.put(
                    "stock",
                    "El stock debe ser un número entero."
            );
        }

        // Si hay errores
        if (!errores.isEmpty()) {

            request.setAttribute("errores", errores);

            request.setAttribute("nombre", nombre);
            request.setAttribute("precio", precioStr);
            request.setAttribute("stock", stockStr);
            request.setAttribute("categoria", categoria);

            forward(
                    request,
                    response,
                    "/WEB-INF/views/formulario.jsp"
            );

            return;
        }

        // Guardar producto
        Producto producto = new Producto(
                0,
                nombre.trim(),
                categoria,
                precio,
                stock
        );

        service.guardar(producto);

        response.sendRedirect(
                request.getContextPath()
                        + "/productos?mensaje=Producto+guardado"
        );
    }

    private void actualizar(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        Producto producto = extraerProducto(request, id);

        service.actualizar(producto);

        response.sendRedirect(
                request.getContextPath()
                        + "/productos?mensaje=Producto+actualizado"
        );
    }

    private void eliminar(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        service.eliminar(id);

        response.sendRedirect(
                request.getContextPath()
                        + "/productos?mensaje=Producto+eliminado"
        );
    }

    private Producto extraerProducto(HttpServletRequest request,
                                     int id) {

        return new Producto(
                id,
                request.getParameter("nombre"),
                request.getParameter("categoria"),
                Double.parseDouble(
                        request.getParameter("precio")
                ),
                Integer.parseInt(
                        request.getParameter("stock")
                )
        );
    }

    private void forward(HttpServletRequest request,
                         HttpServletResponse response,
                         String ruta)
            throws ServletException, IOException {

        request.getRequestDispatcher(ruta)
                .forward(request, response);
    }
    private boolean verificarSesion(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws IOException {

        HttpSession s =
                req.getSession(false);

        if(s == null
                || s.getAttribute("usuarioActual") == null) {

            resp.sendRedirect(
                    req.getContextPath()
                            + "/login"
            );

            return false;
        }

        return true;
    }
}