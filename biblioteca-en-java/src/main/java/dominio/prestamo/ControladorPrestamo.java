package dominio.prestamo;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class ControladorPrestamo {
    private static final File prestamoArchivo = new File ("src/main/resources/prestamos.csv");
    public static TablaPrestamo leer(Configuracion conf) {
        TablaPrestamo tablaPrestamo = new TablaPrestamo(conf);
        try {
            Scanner lectorPrestamoArchivo = new Scanner(prestamoArchivo);
            while (lectorPrestamoArchivo.hasNextLine()) {
                String[] linea = lectorPrestamoArchivo.nextLine().split("\t");
                List<Object> argumentos = new ArrayList<>();
                argumentos.add(LocalDateTime.parse(linea[0]));
                argumentos.add(Integer.parseInt(linea[1]));
                argumentos.add(Integer.parseInt(linea[2]));
                argumentos.add(Boolean.parseBoolean(linea[3]));
                tablaPrestamo.agregarPrestamo(argumentos);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró archivo de prestamos.");
        } catch (NoSuchElementException ne) {
            System.out.println(ne.getMessage());
        }
        return tablaPrestamo;
    }

    public static void escribir(TablaPrestamo tablaPrestamo) {
        try {
            prestamoArchivo.delete();
            prestamoArchivo.createNewFile();
            FileWriter escritorPrestamoArchivo = new FileWriter(prestamoArchivo);
            for(Elemento p: tablaPrestamo.getTabla().values()) {
                Prestamo prestamo = (Prestamo) p;
                escritorPrestamoArchivo.write(prestamo.getFechaYHora() + "\t" +
                        prestamo.getNumeroSocio() + "\t" +
                        prestamo.getNumeroInventario() + "\t" +
                        prestamo.isDevuelto() + "\n");
            }
            escritorPrestamoArchivo.flush();
            escritorPrestamoArchivo.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo de prestamos.");
        }
    }

    public static int[] inputPrestamoNuevo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngrese el Número de Socio: ");
        int numeroSocio = Integer.parseInt(scanner.nextLine());
        System.out.print("\nIngrese el Número de Inventario del Libro: ");
        int numeroInventario = Integer.parseInt(scanner.nextLine());
        return new int[]{numeroSocio, numeroInventario};
    }
    public static Integer inputDevolucionNuevo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngrese el Número de Inventario del Libro: ");
        String valor = scanner.nextLine();
        return !Objects.equals(valor, "") ? Integer.parseInt(valor) : null;
    }
}
