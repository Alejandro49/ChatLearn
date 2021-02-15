package principal;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRegistro
 */

// @ anotaciones en java para indicar las URL´s se podrá definir en el web.xml también. En caso de conflictos tiene prioridad el web.xml
@WebServlet(name="ServletRegistro", urlPatterns={"/html/formulario"})

public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("name");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String nombreUsuario = request.getParameter("UserName");
		String email = request.getParameter("email");
		String nacionalidad = request.getParameter("nationality");
		String lenguaMaterna = request.getParameter("lenguaMaterna");
		String dni = request.getParameter("dni");
		String telefono = request.getParameter("phone");
		String contrasenna = request.getParameter("password");
		
		Usuario user = new Usuario(nombre, apellido1, apellido2, nombreUsuario, email, nacionalidad, dni, telefono, contrasenna);
		
		UsuarioDao userDao = new UsuarioDao();
		boolean usuarioRegistrado = userDao.insert(user);
		boolean crearPerfilInicial = userDao.crearPerfilInicial(nombreUsuario, lenguaMaterna);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (usuarioRegistrado && crearPerfilInicial) {
			try {
				out.println("<html><head><title>Registro completado</title></head><body>");
				out.println("<h1>" + "Registro completado correctamente"   + "</h1>");
				out.println(" <input type=\"button\" onclick=\"location.href='../html/login.html'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
				out.println("</body></html>");
				} finally{out.close();}
			
			
		} else {
			
			try {
				out.println("<html><head><title>Registro No Completado </title></head><body>");
				out.println("<h1>" + "El Registro no se ha completado debido a un error"   + "</h1>");
				out.println(" <input type=\"button\" onclick=\"location.href='../html/login.html'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
				out.println("</body></html>");
				} finally{out.close();}
			
			
		}
		
		
	}
	

}
