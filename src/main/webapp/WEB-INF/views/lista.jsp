<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.universidad.mvc.model.Producto" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>
        <fmt:message key="app.titulo"/>
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">

</head>

<body>

<div class="contenedor">

    <div style="display:flex; justify-content:space-between; align-items:center;">

        <div>

            <strong>
                <fmt:message key="app.bienvenida"/>
            </strong>

            ${sessionScope.usuarioActual.username}

        </div>

        <div>

            <a href="${pageContext.request.contextPath}/idioma?lang=es">
                Español
            </a>

            |

            <a href="${pageContext.request.contextPath}/idioma?lang=en">
                English
            </a>

            |

            <a href="${pageContext.request.contextPath}/logout">
                Cerrar sesión
            </a>

        </div>

    </div>

    <h1>
        <fmt:message key="app.titulo"/>
    </h1>

    <%
        String mensaje =
                (String) request.getAttribute("mensaje");

        if (mensaje != null) {
    %>

    <div class="mensaje">
        <%= mensaje %>
    </div>

    <%
        }
    %>

    <a class="btn"
       href="${pageContext.request.contextPath}/productos?accion=formulario">

        + <fmt:message key="menu.nuevo"/>

    </a>

    <table>

        <thead>

        <tr>

            <th>ID</th>

            <th>
                <fmt:message key="tabla.nombre"/>
            </th>

            <th>
                <fmt:message key="tabla.categoria"/>
            </th>

            <th>
                <fmt:message key="tabla.precio"/>
            </th>

            <th>
                <fmt:message key="tabla.stock"/>
            </th>

            <th>
                <fmt:message key="tabla.acciones"/>
            </th>

        </tr>

        </thead>

        <tbody>

        <%
            List<Producto> productos =
                    (List<Producto>) request.getAttribute("productos");

            for (Producto p : productos) {
        %>

        <tr>

            <td><%= p.getId() %></td>

            <td><%= p.getNombre() %></td>

            <td><%= p.getCategoria() %></td>

            <td>$<%= p.getPrecio() %></td>

            <td><%= p.getStock() %></td>

            <td>

                <a href="${pageContext.request.contextPath}/productos?accion=editar&id=<%= p.getId() %>">

                    <fmt:message key="btn.editar"/>

                </a>

                |

                <a href="${pageContext.request.contextPath}/productos?accion=eliminar&id=<%= p.getId() %>"
                   onclick="return confirm('¿Eliminar producto?')">

                    <fmt:message key="btn.eliminar"/>

                </a>

            </td>

        </tr>

        <%
            }
        %>

        </tbody>

    </table>

</div>

</body>
</html>