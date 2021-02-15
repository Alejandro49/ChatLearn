package principal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class UsuarioDao {
	
	

		private String dbUrl = "jdbc:mysql://localhost:3306/chatlearn?serverTimezone=UTC"; 
		private String dbUname = "root";
		private String dbPassword = "1234";
		private String dbDriver = "com.mysql.cj.jdbc.Driver"; 
		
		
		public void cargarDriver(String dbDriver) {
			try {
				Class.forName(dbDriver);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		}
		
		public Connection getConnection() {
			
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return conn;
			
		}
		
		public boolean insert(Usuario user) { 
			
			cargarDriver(dbDriver); 
			Connection conn = getConnection();
			
			boolean insercionUsuario = true;
			
			String sql = "insert into chatlearn.usuario(nombre, apellido_1, apellido_2, userName, email, nacionalidad, dni, telefono, contrasena) values(?,?,?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getNombre());
				ps.setString(2, user.getApellido1());
				ps.setString(3, user.getApellido2());
				ps.setString(4, user.getNombreUsuario());
				ps.setString(5, user.getEmail());
				ps.setString(6, user.getNacionalidad());
				ps.setString(7, user.getDni());
				ps.setString(8, user.getTelefono());
				ps.setString(9, user.getContrasenna());
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				insercionUsuario = false;
			}
			return insercionUsuario;
		}
		
		public boolean validarUsuario(Usuario user) { 
			cargarDriver(dbDriver); 
			Connection conn = getConnection();
			
			boolean loginUsuario = false;
			
			String sql = "select * from chatlearn.usuario where userName = ? and contrasena = ?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getNombreUsuario());
				ps.setString(2, user.getContrasenna());
				
				ResultSet resultadoConsulta = ps.executeQuery();
				loginUsuario = resultadoConsulta.next();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
			
			return loginUsuario;
		}

	public boolean crearPerfilInicial(String userName, String lenguaMaterna) {

		cargarDriver(dbDriver);
		Connection conn = getConnection();

		boolean crearPerfilInicial = true;
		String sql1 = "select id from chatlearn.usuario where userName=?";
		String sql2 = "insert into chatlearn.perfil(id, lenguaMaterna) values(?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, userName);
			ResultSet getUserId = ps.executeQuery();
			getUserId.next();

			int auxId = getUserId.getInt("id");

			PreparedStatement ps2 = conn.prepareStatement(sql2);

			ps2.setInt(1, auxId);
			ps2.setString(2, lenguaMaterna);
			ps2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			crearPerfilInicial = false;
		}

		return crearPerfilInicial;
	}

	public Usuario getPerfil(Usuario user) throws SQLException {
			cargarDriver(dbDriver);
			Connection conn = getConnection();
			String sql = "select perfil.foto, perfil.interes1,perfil.interes2,perfil.interes3,perfil.interes4,perfil.interes5," +
					"perfil.lenguaMaterna, perfil.calificacion from chatlearn.perfil join chatlearn.usuario on perfil.id=usuario.id where usuario.userName = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getNombreUsuario());
			try (ResultSet resultadoConsulta = ps.executeQuery()) {
				resultadoConsulta.next();

				user.setFoto(resultadoConsulta.getString("foto"));
				user.setIntereses(resultadoConsulta.getString("interes1"));
				user.setIntereses(resultadoConsulta.getString("interes2"));
				user.setIntereses(resultadoConsulta.getString("interes3"));
				user.setIntereses(resultadoConsulta.getString("interes4"));
				user.setIntereses(resultadoConsulta.getString("interes5"));
				user.setLengua(resultadoConsulta.getString("lenguaMaterna"));
				user.setCalificacion(resultadoConsulta.getInt("calificacion"));
			}
			catch(SQLException e) { e.printStackTrace(); }
			conn.close();
			return user;
			}


		public ArrayList<String> getContactos(String user) throws SQLException {
			ArrayList<String> array = new ArrayList<String>();
			cargarDriver(dbDriver);
			Connection conn = getConnection();
			String sql = "SELECT contactos.contacto FROM chatlearn.contactos where contactos.usuario=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user);
				ResultSet resultadoConsulta = ps.executeQuery();
				while(resultadoConsulta.next()) {
					array.add(resultadoConsulta.getString("contacto"));
				}


			}catch(SQLException e) {
				e.printStackTrace();
			}
			conn.close();
			return array;
		}

	public ArrayList<String> buscarContacto(String user) throws SQLException {
		ArrayList<String> array = new ArrayList<String>();
		cargarDriver(dbDriver);
		Connection conn = getConnection();
		String sql = "SELECT usuario.userName FROM chatlearn.usuario where usuario.userName LIKE  ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + user + "%");
			ResultSet resultadoConsulta = ps.executeQuery();
			while(resultadoConsulta.next()) {
				array.add(resultadoConsulta.getString("userName"));
			}


		}catch(SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return array;
	}

		public boolean insertContacto(String usuario, String contacto) throws SQLException {
			boolean aux=true;
			cargarDriver(dbDriver);
			Connection conn = getConnection();
			String sql = "INSERT INTO chatlearn.contactos(usuario, contacto) VALUES(?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, usuario);
				ps.setString(2, contacto);
				ResultSet resultadoConsulta = ps.executeQuery();


			}catch(SQLException e){
				e.printStackTrace();
				aux=false;
			}

			conn.close();
			return aux;
		}

	public ArrayList<Solicitud> getSolicitudes(String userName) throws SQLException{
		ArrayList<Solicitud> array = new ArrayList<Solicitud>();

		cargarDriver(dbDriver);
		Connection conn = getConnection();

		String sol_recibidas = "select emisor, receptor from chatlearn.solicitudes where receptor=? and aceptada=0";

		try {
			PreparedStatement ps = conn.prepareStatement(sol_recibidas);
			ps.setString(1, userName);
			ResultSet resultadoConsulta = ps.executeQuery();
			while(resultadoConsulta.next()){

				Solicitud solicitud = new Solicitud();
				solicitud.setReceptor(resultadoConsulta.getString("receptor"));
				solicitud.setEmisor(resultadoConsulta.getString("emisor"));
				array.add(solicitud);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}

	public boolean aceptarSolicitud(String receptor,String emisor) {
		boolean aux = true;
		cargarDriver(dbDriver);
		Connection conn = getConnection();
		String sql = "UPDATE chatlearn.solicitudes set aceptada = 1 where receptor =? and emisor =?";
		String sql2 = "INSERT INTO chatlearn.contactos(usuario, contacto) values(?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, receptor);
			ps.setString(2, emisor);
			ps.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			aux = false;
		}
		try{
			PreparedStatement ps = conn.prepareStatement(sql2);
			ps.setString(1,receptor);
			ps.setString(2,emisor);
			ps.executeUpdate();

			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1,emisor);
			ps2.setString(2,receptor);
			ps2.executeUpdate();

		} catch (SQLException throwables) {
		throwables.printStackTrace();
		aux = false;
	}
		return aux;
	}

	public boolean enviarSolicitud(String emisor,String receptor) {
		boolean aux = true;
		cargarDriver(dbDriver);
		Connection conn = getConnection();

		String escontacto = "SELECT * FROM chatlearn.contactos WHERE usuario=? and contacto=?";
		String yaexiste = "SELECT * FROM chatlearn.solicitudes WHERE receptor=? and emisor=?";
		String sql = "INSERT INTO chatlearn.solicitudes(emisor, receptor) values(?,?)";


		try {
			//es contacto?
			PreparedStatement ps = conn.prepareStatement(escontacto);
			ps.setString(1, emisor);
			ps.setString(2, receptor);

			ResultSet resultadoConsulta = ps.executeQuery();
			if (resultadoConsulta.next()) return false;


			//comprobacion si existe solicitud ya
			PreparedStatement ps2 = conn.prepareStatement(yaexiste);
			ps2.setString(1, receptor);
			ps2.setString(2, emisor);
			ResultSet resultadoConsulta2 = ps2.executeQuery();

			if (resultadoConsulta2.next()) return false;

			PreparedStatement ps3 = conn.prepareStatement(sql);
			ps3.setString(1, emisor);
			ps3.setString(2, receptor);
			ps3.executeUpdate();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
			aux = false;
		}
		return aux;
	}

}
