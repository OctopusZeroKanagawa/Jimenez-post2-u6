<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Iniciar Sesión</h1>

<p style="color:red">
    ${errorLogin}
</p>

<form method="post"
      action="${pageContext.request.contextPath}/login">

    <input type="text"
           name="username"
           placeholder="Usuario">

    <br><br>

    <input type="password"
           name="password"
           placeholder="Contraseña">

    <br><br>

    <button type="submit">
        Entrar
    </button>

</form>

</body>
</html>