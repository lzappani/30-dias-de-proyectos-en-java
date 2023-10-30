package dominio.socio;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;

public class Socio extends Elemento {
    private final int numeroSocio;
    private final String nombre;
    private final String direccion;
    private int numeroDeLibrosPrestados;
    private final Configuracion conf;

    public Socio(Integer numeroSocio, String nombre, String direccion, Configuracion c) {
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroDeLibrosPrestados = 0;
        this.conf = c;
    }
    public Socio(Integer numeroSocio, String nombre,
                 String direccion, Integer numeroDeLibrosPrestados, Configuracion c) {
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroDeLibrosPrestados = numeroDeLibrosPrestados;
        this.conf = c;
    }

    public Integer getNumeroSocio() {
        return numeroSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getNumeroDeLibrosPrestados() {
        return numeroDeLibrosPrestados;
    }

    @Override
    public String toString() {
        String espaciador = String.format("%%n%%%ss%%%ss%%%ss%%%ss",
                conf.configTablaSocio[0],
                conf.configTablaSocio[1],
                conf.configTablaSocio[2],
                conf.configTablaSocio[3]);
        return String.format(espaciador,
                numeroSocio,
                nombre,
                direccion,
                numeroDeLibrosPrestados);
    }
    public void incrementarNumeroDeLibrosPrestados() {
        this.numeroDeLibrosPrestados = this.numeroDeLibrosPrestados + 1;
    }
    public void decrementarNumeroDeLibrosPrestados() {
        this.numeroDeLibrosPrestados = this.numeroDeLibrosPrestados - 1;
    }
}