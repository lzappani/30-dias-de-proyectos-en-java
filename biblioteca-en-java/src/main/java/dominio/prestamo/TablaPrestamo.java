package dominio.prestamo;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;
import dominio.clasesGenericas.Tabla;
import dominio.libro.TablaLibro;
import dominio.socio.TablaSocio;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

public class TablaPrestamo extends Tabla {
    public TablaPrestamo(Configuracion conf) {
        this.tabla = new LinkedHashMap<>();
        this.conf = conf;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Préstamos:");
        String espaciador = String.format("%%n%%%ss%%%ss%%%ss%%%ss",
                conf.configTablaPrestamo[0],
                conf.configTablaPrestamo[1],
                conf.configTablaPrestamo[2],
                conf.configTablaPrestamo[3]);
        output.append(String.format(espaciador,
                conf.etiquetasTablaPrestamo[0],
                conf.etiquetasTablaPrestamo[1],
                conf.etiquetasTablaPrestamo[2],
                conf.etiquetasTablaPrestamo[3]));
        this.tabla.forEach((key, value) -> output.append(value.toString()));
        return output.append("\n").toString();
    }

    public void devolucion(TablaLibro tablaLibro, TablaSocio tablaSocio) {
        Integer numeroInventario = ControladorPrestamo.inputDevolucionNuevo();
        try{
            this.estaPrestadoException(numeroInventario);
            Prestamo prestamo = this.buscarPrestamo(numeroInventario);
            prestamo.devolver();
            int numeroSocio = prestamo.getNumeroSocio();
            tablaLibro.devolver(numeroInventario);
            tablaSocio.devolver(numeroSocio);
        } catch (Exception e) {
            System.out.println("El número de inventario no es válido.");
        }
    }

    private void estaPrestadoException(int numeroInventario) throws Exception {
        boolean estaPrestado = false;
        for (Elemento p : this.tabla.values()) {
            Prestamo prestamo = (Prestamo) p;
            if (prestamo.getNumeroInventario() == numeroInventario) {
                estaPrestado = true;
                break;
            }
        }
        if (!estaPrestado) throw new Exception("No esta prestado");
    }

    private Prestamo buscarPrestamo(int numeroInventario) {
        Prestamo prestamoEncontrado = null;
        for (Elemento p : this.tabla.values()) {
            Prestamo prestamo = (Prestamo) p;
            if (prestamo.getNumeroInventario() == numeroInventario && !prestamo.isDevuelto()) {
                prestamoEncontrado = prestamo;
            }
        }
        return prestamoEncontrado;
    }

    public void agregarPrestamo(TablaLibro tablaLibro, TablaSocio tablaSocio) {
        try {
            int[] linea = ControladorPrestamo.inputPrestamoNuevo();
            tablaSocio.socioExiste(linea[0]);
            tablaLibro.libroDisponible(linea[1]);
            Prestamo prestamo = new Prestamo(linea[0], linea[1], conf);
            this.put(prestamo.getFechaYHora().hashCode(), prestamo);
            tablaSocio.prestar(linea[0]);
            tablaLibro.prestar(linea[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void agregarPrestamo(List<Object> argumentos) {
        Prestamo prestamo = new Prestamo((LocalDateTime) argumentos.get(0),
                (Integer) argumentos.get(1),
                (Integer) argumentos.get(2),
                (Boolean) argumentos.get(3),
                this.conf);
        this.put(prestamo.getFechaYHora().hashCode(), prestamo);
    }
}
