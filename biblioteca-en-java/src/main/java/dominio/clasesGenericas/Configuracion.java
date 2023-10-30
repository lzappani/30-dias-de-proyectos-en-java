package dominio.clasesGenericas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Configuracion {
    private static final File archivoConfiguracion = new File("src/main/resources/config.txt");
    public String[] configTablaLibro;
    public String[] etiquetasTablaLibro;
    public String[] configTablaSocio;
    public String[] etiquetasTablaSocio;
    public String[] configTablaPrestamo;
    public String[] etiquetasTablaPrestamo;
    public int tope;

    public Configuracion() {
        try {
            Scanner lector = new Scanner(archivoConfiguracion);

            configTablaLibro = lector.nextLine().split("\t");
            etiquetasTablaLibro = lector.nextLine().split("\t");


            configTablaSocio = lector.nextLine().split("\t");
            etiquetasTablaSocio = lector.nextLine().split("\t");

            configTablaPrestamo = lector.nextLine().split("\t");
            etiquetasTablaPrestamo = lector.nextLine().split("\t");

            tope = Integer.parseInt(lector.nextLine());

        } catch (FileNotFoundException e) {
            System.out.println("Archivo de configuracion no encontrado.");
        } catch (NoSuchElementException ne) {
            System.out.println(ne.getMessage());
        }
    }
}
