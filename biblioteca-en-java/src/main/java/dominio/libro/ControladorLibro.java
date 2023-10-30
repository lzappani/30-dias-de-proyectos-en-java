package dominio.libro;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ControladorLibro {
    private static final File libroArchivo = new File ("src/main/resources/libros.csv");
    public static TablaLibro leer(Configuracion conf) {
        TablaLibro tablaLibro = new TablaLibro(conf);
        try {
            Scanner lectorLibroArchivo = new Scanner(libroArchivo);
            while (lectorLibroArchivo.hasNextLine()) {
                String[] linea = lectorLibroArchivo.nextLine().split("\t");
                List<Object> argumentos = new ArrayList<>();
                argumentos.add(Integer.parseInt(linea[0]));
                argumentos.add(linea[1]);
                argumentos.add(linea[2]);
                argumentos.add(linea[3]);
                argumentos.add(linea[4]);
                argumentos.add(Boolean.parseBoolean(linea[5]));

                tablaLibro.agregarLibro(argumentos);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró archivo de libros.");
        } catch (NoSuchElementException ne) {
            System.out.println(ne.getMessage());
        }
        return tablaLibro;
    }

    public static void escribir(TablaLibro tablaLibro) {
        try {
            libroArchivo.delete();
            libroArchivo.createNewFile();
            FileWriter escritorLibroArchivo = new FileWriter(libroArchivo);

            for(Elemento l: tablaLibro.getTabla().values()) {
                Libro libro = (Libro) l;
                escritorLibroArchivo.write(libro.getNumeroInventario() + "\t" +
                        libro.getTitulo() + "\t" +
                        libro.getAutor() + "\t" +
                        libro.getLocalizacion() + "\t" +
                        libro.getSignatura() + "\t" +
                        libro.estaDisponible() + "\n");
            }
            escritorLibroArchivo.flush();
            escritorLibroArchivo.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo de libros.");
        }
    }

    public static String[] inputLibroNuevo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el título: ");
        String titulo = scanner.nextLine();
        System.out.print("\nIngrese el Autor: ");
        String autor = scanner.nextLine();
        System.out.print("\nIngrese la Localización: ");
        String localizacion = scanner.nextLine();
        System.out.print("\nIngrese la Signatura: ");
        String signatura = scanner.nextLine();
        return new String[]{titulo, autor, localizacion, signatura};
    }
    public static int inputModificarSignatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el Número de Inventario: ");
        return Integer.parseInt(scanner.nextLine());
    }
    public static String[] inputNuevaLocSignatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese la Nueva Localización: ");
        String loc = scanner.nextLine();
        System.out.print("\nIngrese la Nueva Signatura: ");
        String sig = scanner.nextLine();
        return new String[]{loc, sig};
    }

}
