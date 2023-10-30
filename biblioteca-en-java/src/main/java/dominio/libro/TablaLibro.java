package dominio.libro;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Tabla;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TablaLibro extends Tabla {

    public TablaLibro(Configuracion conf) {
        this.tabla = new HashMap<>();
        this.conf = conf;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Libros:");
        String espaciador = String.format("%%n%%%ss%%%ss%%%ss%%%ss%%%ss%%%ss",
                conf.configTablaLibro[0],
                conf.configTablaLibro[1],
                conf.configTablaLibro[2],
                conf.configTablaLibro[3],
                conf.configTablaLibro[4],
                conf.configTablaLibro[5]);

        output.append(String.format(espaciador,
                conf.etiquetasTablaLibro[0],
                conf.etiquetasTablaLibro[1],
                conf.etiquetasTablaLibro[2],
                conf.etiquetasTablaLibro[3],
                conf.etiquetasTablaLibro[4],
                conf.etiquetasTablaLibro[5]));
        this.tabla.forEach((key, value) -> output.append(value.toString()));
        return output.append("\n").toString();
    }

    public Integer siguienteNumeroInventario() {
        if (tabla.isEmpty()) {
            return 1;
        } else {
            int ultimoNumeroInventarioUsado = Collections.max(this.tabla.keySet());
            return ultimoNumeroInventarioUsado + 1;
        }
    }

    public void agregarLibro() {
        String[] linea = ControladorLibro.inputLibroNuevo();
        Libro libro = new Libro(this.siguienteNumeroInventario(),
                linea[0], linea[1], linea[2], linea[3],
                true, this.conf);
        this.put(libro.getNumeroInventario(), libro);
    }
    public void agregarLibro(List<Object> argumentos) {
        Libro libro = new Libro((Integer) argumentos.get(0),
                (String) argumentos.get(1),
                (String) argumentos.get(2),
                (String) argumentos.get(3),
                (String) argumentos.get(4),
                (Boolean) argumentos.get(5),
                this.conf);
        this.put(libro.getNumeroInventario(), libro);
    }

    public void libroDisponible(int numeroInventario) throws Exception {
        if (!this.tabla.containsKey(numeroInventario)) throw new Exception("Error: El Libro no existe.");
        Libro libro = (Libro) this.tabla.get(numeroInventario);
        libro.estaDisponibleException();
    }

    public void prestar(int numeroInventario) {
        Libro libro = (Libro) this.tabla.get(numeroInventario);
        libro.prestar();
    }

    public void devolver(int numeroInventario) {
        Libro libro = (Libro) this.get(numeroInventario);
        libro.devolver();
    }

    public void cambiarUbicacion() {
        int numeroInventario = ControladorLibro.inputModificarSignatura();
        try{
            this.libroDisponible(numeroInventario);
            Libro libro = (Libro) this.get(numeroInventario);
            String[] locSig = ControladorLibro.inputNuevaLocSignatura();
            libro.cambiarUbicacion(locSig[0], locSig[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
