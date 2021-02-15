<%@ page import="principal.UsuarioDao" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Andres
  Date: 12/18/2020
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Contactos</title>
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

    UsuarioDao dao = new UsuarioDao();
    ArrayList<String> array = new ArrayList<String>();
    array=dao.getContactos(userName);


%>
<h2>Contactos</h2>
<table>
    <tr>
        <th> Usuario </th>
    </tr>
    <% for(int row=0; row < array.size(); row ++) { %>
    <tr>
        <td><%=array.get(row)%> </td>
    </tr>
    <% } %>
</table>
<input type="button" onclick="location.href='contactos.html'" class="btn btn-outline-secondary" value="Volver">
<input type="button" onclick="location.href='chatRoom.html'" class="btn btn-outline-secondary" value="Sala de Chat">
</body>
</html>
