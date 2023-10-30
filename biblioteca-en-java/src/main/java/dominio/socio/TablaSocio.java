package dominio.socio;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;
import dominio.clasesGenericas.Tabla;

import java.util.*;

public class TablaSocio extends Tabla {

    public TablaSocio(Configuracion conf) {
        this.tabla = new HashMap<>();
        this.conf = conf;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Socios:");
        String espaciador = String.format("%%n%%%ss%%%ss%%%ss%%%ss",
                conf.configTablaSocio[0],
                conf.configTablaSocio[1],
                conf.configTablaSocio[2],
                conf.configTablaSocio[3]);
        output.append(String.format(espaciador,
                conf.etiquetasTablaSocio[0],
                conf.etiquetasTablaSocio[1],
                conf.etiquetasTablaSocio[2],
                conf.etiquetasTablaSocio[3]));
        this.tabla.forEach((key, value) -> output.append(value.toString()));
        return output.append("\n").toString();
    }

    public void socioExiste(int numeroSocio) throws Exception {
        if (!this.tabla.containsKey(numeroSocio)) throw new Exception("Error. Socio no existe.");
    }
    public void agregarSocio() {  // Este metodo agrega nuevos socios por consola
        String[] lista = ControladorSocio.inputSocioNuevo();
        Socio socio = new Socio(this.siguienteNumeroSocio(), lista[0], lista[1], conf);
        this.put(socio.getNumeroSocio(), socio);
    }
    public void agregarSocio(List<Object> argumentos) {  // Este metodo carga socios de Archivo
        Socio socio = new Socio((Integer) argumentos.get(0),
                (String) argumentos.get(1),
                (String) argumentos.get(2),
                (Integer) argumentos.get(3),
                conf);
        this.put(socio.getNumeroSocio(),socio);
    }

    public Integer siguienteNumeroSocio() {
        if (tabla.isEmpty()) {
            return 1;
        } else {
            int ultimoNumeroSocioUsado = Collections.max(this.tabla.keySet());
            return ultimoNumeroSocioUsado + 1;
        }
    }

    public void prestar(int numeroSocio) {
        Socio socio = (Socio) this.get(numeroSocio);
        socio.incrementarNumeroDeLibrosPrestados();
    }
    public void devolver(int numeroSocio) {
        Socio socio = (Socio) this.get(numeroSocio);
        socio.decrementarNumeroDeLibrosPrestados();
    }

    public void verSociosNoFiables(Configuracion conf) {
        List<Socio> noFiables = new ArrayList<>();
        for (Elemento s : this.tabla.values()) {
            Socio socio = (Socio) s;
            if (socio.getNumeroDeLibrosPrestados() >= conf.tope) noFiables.add(socio);
        }
        StringBuilder output = new StringBuilder("Socios no Fiables:");

        String espaciador = String.format("%%n%%%ss%%%ss%%%ss%%%ss",
                conf.configTablaSocio[0],
                conf.configTablaSocio[1],
                conf.configTablaSocio[2],
                conf.configTablaSocio[3]);
        output.append(String.format(espaciador,
                conf.etiquetasTablaSocio[0],
                conf.etiquetasTablaSocio[1],
                conf.etiquetasTablaSocio[2],
                conf.etiquetasTablaSocio[3]));
        noFiables.forEach((value) -> output.append(value.toString()));
        output.append("\n");
        System.out.println(output);
    }
}
