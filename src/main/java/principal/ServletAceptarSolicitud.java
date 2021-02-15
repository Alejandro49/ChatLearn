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
@WebServlet(name="ServletAceptarSolicitud", urlPatterns={"/html/aceptarSolicitud"})
public class ServletAceptarSolicitud extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAceptarSolicitud() {
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
        String receptor = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) receptor = cookie.getValue();
            }
        }
        String emisor = request.getParameter("solicitud");

        UsuarioDao dao = new UsuarioDao();
        if(dao.aceptarSolicitud(receptor, emisor)){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println("<html><head><title>Solicitud aceptada </title></head><body>");
                out.println("<h1>" + "Solicitud aceptada "   + "</h1>");
                out.println(" <input type=\"button\" onclick=\"location.href='solicitudes.jsp'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
                out.println("</body></html>");
            } finally{out.close();}
        }

        else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                out.println("<html><head><title>Error al aceptar solicitud </title></head><body>");
                out.println("<h1>" + "Error al aceptar solicitud "   + "</h1>");
                out.println(" <input type=\"button\" onclick=\"location.href='dashboard.html'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
                out.println("</body></html>");
            } finally{out.close();}
        }

    }

}
