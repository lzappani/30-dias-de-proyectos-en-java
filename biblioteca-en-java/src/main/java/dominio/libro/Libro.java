package dominio.libro;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;

public class Libro extends Elemento {
    private final Integer numeroInventario;
    private final String titulo;
    private final String autor;
    private String localizacion;
    private String signatura;
    private boolean estaDisponible;
    private final Configuracion conf;

    public Libro(Integer numeroInventario,
                 String titulo, String autor, String localizacion,
                 String signatura, boolean estaDisponible, Configuracion conf) {
        this.numeroInventario = numeroInventario;
        this.titulo = titulo;
        this.autor = autor;
        this.localizacion = localizacion;
        this.signatura = signatura;
        this.estaDisponible = estaDisponible;
        this.conf = conf;
    }

    public Integer getNumeroInventario() {
        return numeroInventario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getSignatura() {
        return signatura;
    }

    public boolean estaDisponible() {
        return estaDisponible;
    }

    @Override
    public String toString() {
        String espaciador = String.format("%%n%%%ss%%%ss%%%ss%%%ss%%%ss%%%ss",
                conf.configTablaLibro[0],
                conf.configTablaLibro[1],
                conf.configTablaLibro[2],
                conf.configTablaLibro[3],
                conf.configTablaLibro[4],
                conf.configTablaLibro[5]);
        return String.format(espaciador,
                numeroInventario,
                titulo,
                autor,
                localizacion,
                signatura,
                estaDisponible? "Si" : "No");
    }

    public void prestar() {
        this.estaDisponible = false;
    }

    public void devolver() {
        this.estaDisponible = true;
    }

    public void cambiarUbicacion(String localizacion, String signatura) {
        this.localizacion = localizacion;
        this.signatura = signatura;
    }

    public void estaDisponibleException() throws Exception {
        if (!this.estaDisponible) throw new Exception("Libro no disponible.");
    }
}
