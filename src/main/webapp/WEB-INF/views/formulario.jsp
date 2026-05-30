<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.universidad.mvc.model.Producto" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Formulario</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">

</head>

<body>

<%
    Producto producto =
            (Producto) request.getAttribute("producto");

    boolean editando =
            producto != null;
%>

<div class="contenedor-formulario">

    <c:if test="${not empty errores}">
        <div class="alert-error">
            <ul>
                <c:forEach var="e" items="${errores}">
                    <li>${e.value}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <h1>

        <%= editando
                ? "Editar Producto"
                : "Registrar Producto" %>

    </h1>

    <form method="post"
          action="${pageContext.request.contextPath}/productos">

        <input type="hidden"
               name="accion"
               value="<%= editando ? "actualizar" : "guardar" %>">

        <% if (editando) { %>

        <input type="hidden"
               name="id"
               value="<%= producto.getId() %>">

        <% } %>

        <label>

            Nombre

            <input type="text"
                   name="nombre"
                   required
                   value="<%= editando ? producto.getNombre() : "" %>">

            <c:if test="${not empty nombre}">
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        document.getElementsByName("nombre")[0].value =
                            "${nombre}";
                    });
                </script>
            </c:if>

            <c:if test="${not empty errores.nombre}">
                <span class="campo-error">
                        ${errores.nombre}
                </span>
            </c:if>

        </label>

        <label>

            Categoría

            <input type="text"
                   name="categoria"
                   value="<%= editando ? producto.getCategoria() : "" %>">

            <c:if test="${not empty categoria}">
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        document.getElementsByName("categoria")[0].value =
                            "${categoria}";
                    });
                </script>
            </c:if>

        </label>

        <label>

            Precio

            <input type="number"
                   step="0.01"
                   min="0"
                   name="precio"
                   required
                   value="<%= editando ? producto.getPrecio() : "" %>">

            <c:if test="${not empty precio}">
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        document.getElementsByName("precio")[0].value =
                            "${precio}";
                    });
                </script>
            </c:if>

            <c:if test="${not empty errores.precio}">
                <span class="campo-error">
                        ${errores.precio}
                </span>
            </c:if>

        </label>

        <label>

            Stock

            <input type="number"
                   min="0"
                   name="stock"
                   required
                   value="<%= editando ? producto.getStock() : "" %>">

            <c:if test="${not empty stock}">
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        document.getElementsByName("stock")[0].value =
                            "${stock}";
                    });
                </script>
            </c:if>

            <c:if test="${not empty errores.stock}">
                <span class="campo-error">
                        ${errores.stock}
                </span>
            </c:if>

        </label>

        <div class="acciones">

            <button type="submit">

                <%= editando
                        ? "Actualizar"
                        : "Guardar" %>

            </button>

            <a class="btn-cancelar"
               href="${pageContext.request.contextPath}/productos">

                Cancelar

            </a>

        </div>

    </form>

</div>

</body>
</html>