<%@ page import="principal.Usuario" %>
<%@ page import="principal.UsuarioDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="principal.Solicitud" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>ChatLearn</title>
    </head>

<body>

<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
    }

    ArrayList<Solicitud> array = new ArrayList<Solicitud>();

    UsuarioDao userDao=new UsuarioDao();

    try {
        array = userDao.getSolicitudes(userName);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    for(int i=0; i<array.size();i++){
        System.out.println(array.get(i).getEmisor());
    }

%>



    <h2>  Aceptar solicitudes  </h2>
    <form method="POST" action="aceptarSolicitud"name="Formulario">

        <% for(int row=0; row < array.size(); row ++) { %>
        <pre>
            <input type="radio" name="solicitud" value="<%= array.get(row).getEmisor() %>" > <%= array.get(row).getEmisor() %>
            <br>
        <% } %>
        </pre>
        <input type="submit" value="Aceptar" name="BotonAceptar">
        <input type="button" onclick="location.href='contactos.html'" class="btn btn-outline-secondary" value="Volver">

    </form>
    <br>
</body>

