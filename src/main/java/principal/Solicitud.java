package principal;

public class Solicitud {
    private String emisor, receptor, contenido;

    public Solicitud(){ super();}

    public String getEmisor() {
        return this.emisor;
    }

    public void setEmisor(String emisor) { this.emisor = emisor; }

    public String getReceptor() {
        return this.receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }
    }
