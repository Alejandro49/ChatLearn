package principal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(name="ServletEnviarSolicitud", urlPatterns={"/html/enviarSolicitud"})
public class ServletEnviarSolicitud extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnviarSolicitud() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emisor = null;
        Cookie[] cookies = request.getCookies();

        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) emisor = cookie.getValue();
            }
        }

        String receptor = request.getParameter("receptor");

        UsuarioDao dao = new UsuarioDao();

        if(dao.enviarSolicitud(emisor, receptor)){

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                out.println("<html><head><title>Solicitud enviada </title></head><body>");
                out.println("<h1>" + "Solicitud enviada correctamente al usuario: " + receptor + "</h1>");
                out.println(" <input type=\"button\" onclick=\"location.href='contactos.html'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
                out.println("</body></html>");
            } finally{out.close();}
        }

        else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                out.println("<html><head><title>Error al enviar la solicitud </title></head><body>");
                out.println("<h1>" + "El usuario no existe, ya está en tu lista de contactos o ya se ha enviado una solicitud previamente. "   + "</h1>");
                out.println(" <input type=\"button\" onclick=\"location.href='dashboard.html'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
                out.println("</body></html>");
            } finally{out.close();}
        }

    }

}