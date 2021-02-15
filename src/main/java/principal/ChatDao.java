package principal;


import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatDao {
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
    public boolean insertChat(String idioma, String userName) throws SQLException {
    	boolean aux=true;
        cargarDriver(dbDriver);
        Connection conn = getConnection();
        String sql = "SELECT id from chatlearn.usuario where userName= ?";
        String sql2 = "INSERT INTO chatlearn.chat(idioma, profesor) VALUES(?,?)";
        try {
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet resultadoConsulta = ps.executeQuery();
            resultadoConsulta.next();
            int profesor = resultadoConsulta.getInt("id");
            
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, idioma);
            ps2.setInt(2, profesor);
            ps2.executeUpdate();
            
         }catch(SQLException e){
             e.printStackTrace();
             aux=false;
         }
        
        conn.close();
        return aux;
    }

    public ArrayList<Chat> getChat(String idioma) throws SQLException {
        ArrayList<Chat> array = new ArrayList<Chat>();
        cargarDriver(dbDriver);
        Connection conn = getConnection();
        String sql = "SELECT chat.id, chat.idioma, usuario.userName FROM chatlearn.chat join chatlearn.usuario on chat.profesor=usuario.id where idioma=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idioma);
            ResultSet resultadoConsulta = ps.executeQuery();
            while(resultadoConsulta.next()) {
                Chat chat = new Chat();
                chat.setId(resultadoConsulta.getInt("id"));
                chat.setIdioma(resultadoConsulta.getString("idioma"));
                chat.setProfesor(resultadoConsulta.getString("userName"));
                array.add(chat);
            }


        }catch(SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return array;
    }
}
