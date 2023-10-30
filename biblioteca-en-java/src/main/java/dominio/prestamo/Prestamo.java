package dominio.prestamo;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;

import java.time.LocalDateTime;

public class Prestamo extends Elemento {
    private final LocalDateTime fechaYHora;
    private final Integer numeroSocio;
    private final Integer numeroInventario;
    private boolean devuelto;
    private final Configuracion conf;

    public Prestamo(LocalDateTime fechaYHora,
                    Integer numeroSocio, Integer numeroInventario,
                    boolean devuelto, Configuracion conf) {
        this.fechaYHora = fechaYHora;
        this.numeroSocio = numeroSocio;
        this.numeroInventario = numeroInventario;
        this.devuelto = devuelto;
        this.conf = conf;
    }

    public Prestamo(Integer numeroSocio, Integer numeroInventario, Configuracion conf) {
        this.fechaYHora = LocalDateTime.now();
        this.numeroSocio = numeroSocio;
        this.numeroInventario = numeroInventario;
        this.devuelto = false;
        this.conf = conf;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public Integer getNumeroSocio() {
        return numeroSocio;
    }

    public Integer getNumeroInventario() {
        return numeroInventario;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void devolver() {
        this.devuelto = true;
    }

    @Override
    public String toString() {
        String espaciador = String.format("%%n%%%ss%%%ss%%%ss%%%ss",
                conf.configTablaPrestamo[0],
                conf.configTablaPrestamo[1],
                conf.configTablaPrestamo[2],
                conf.configTablaPrestamo[3]);
        return String.format(espaciador,
                fechaYHora,
                numeroSocio,
                numeroInventario,
                devuelto? "Si" : "No");
    }
}
