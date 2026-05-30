# Sistema de Gestión de Productos MVC

## Descripción

Aplicación web desarrollada en Java utilizando el patrón de arquitectura MVC (Modelo-Vista-Controlador) para la gestión de productos. El sistema permite realizar operaciones CRUD (Crear, Consultar, Actualizar y Eliminar) sobre un inventario de productos, incorporando autenticación de usuarios, validaciones del lado del servidor e internacionalización (i18n).

## Características

### Gestión de Productos

* Registro de productos.
* Consulta de productos registrados.
* Edición de productos existentes.
* Eliminación de productos.
* Visualización del inventario en formato tabular.

### Autenticación

* Inicio de sesión mediante HttpSession.
* Control de acceso a recursos protegidos.
* Cierre de sesión.
* Gestión de roles básicos (ADMIN y VIEWER).

### Validaciones

* Nombre obligatorio.
* Longitud máxima de 100 caracteres para el nombre.
* Precio numérico válido.
* Precio no negativo.
* Stock entero válido.
* Stock no negativo.
* Conservación de datos ingresados cuando ocurren errores.

### Internacionalización (i18n)

* Soporte para español e inglés.
* Cambio dinámico de idioma.
* Persistencia del idioma seleccionado durante la sesión.

## Tecnologías Utilizadas

* Java 17
* Jakarta Servlet 6.0
* JSP (Java Server Pages)
* JSTL 3.0.1
* Maven
* HTML5
* CSS3
* Apache Tomcat


## Usuarios de Prueba

| Usuario | Contraseña | Rol    |
| ------- | ---------- | ------ |
| admin   | Admin123!  | ADMIN  |
| viewer  | View456!   | VIEWER |

## Instalación y Ejecución

### Requisitos

* JDK 17 o superior.
* Apache Tomcat 10 o superior.
* Maven 3.8 o superior.

### Compilación

mvn clean package

### Despliegue

1. Generar el archivo WAR mediante Maven.
2. Copiar el archivo generado en la carpeta webapps de Tomcat.
3. Iniciar el servidor Tomcat.
4. Acceder a la aplicación desde el navegador.

### URL de acceso

http://localhost:8080/mvc-productos

## Patrón Arquitectónico

El proyecto implementa el patrón Modelo-Vista-Controlador (MVC):

### Modelo

Representa los datos de la aplicación mediante las clases Producto y Usuario.

### Vista

Implementada mediante JSP y JSTL para la presentación de información al usuario.

### Controlador

Implementado mediante Servlets que gestionan las solicitudes HTTP y coordinan la interacción entre modelo y vista.

## Funcionalidades Implementadas

* CRUD completo de productos.
* Autenticación basada en sesiones.
* Protección de rutas.
* Validación de formularios.
* Mensajes de error personalizados.
* Internacionalización español/inglés.
* Persistencia de idioma en sesión.
* Cierre de sesión seguro.

## Autor

Andrés Jiménez

Ingeniería de Sistemas
Universidad Francisco de Paula Santander (UFPS)
