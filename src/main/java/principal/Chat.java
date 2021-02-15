package principal;

public class Chat {
	private int id;
	private String idioma, profesor;

	
	public Chat() {
		super();
	}
	public Chat(int id, String idioma, String profesor) {
		this.id=id;
		this.idioma=idioma;
		this.profesor=profesor;
	}
	public void setId(int aux) {
		this.id=aux;
	}
	public void setIdioma(String idioma) {
		this.idioma=idioma;
	}
	public void setProfesor(String profesor) {
		this.profesor=profesor;
	}
	public int getId() {
		return this.id;
	}
	public String getIdioma() {
		return this.idioma;
	}
	public String getProfesor() {
		return this.profesor;
	}
}
