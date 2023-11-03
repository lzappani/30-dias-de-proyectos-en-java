import dominio.clasesGenericas.Configuracion;
import dominio.libro.ControladorLibro;
import dominio.libro.TablaLibro;
import dominio.prestamo.ControladorPrestamo;
import dominio.prestamo.TablaPrestamo;
import dominio.socio.ControladorSocio;
import dominio.socio.TablaSocio;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuracion conf = new Configuracion();
        TablaLibro tablaLibro = ControladorLibro.leer(conf);
        TablaSocio tablaSocio = ControladorSocio.leer(conf);
        TablaPrestamo tablaPrestamo = ControladorPrestamo.leer(conf);
        Scanner scanner = new Scanner(System.in);

        //Menu
        String option = "";
        while (!"0".equals(option)) {
            System.out.println("\nMENU");
            System.out.println("0. SALIR\t\t\t\t\t\t\t1. Registrar Socio\t\t2. Registrar Libro");
            System.out.println("3. Registrar Préstamo\t\t\t\t4. Registrar Devolución\t5. Ver Socio");
            System.out.println("6. Ver Libros\t\t\t\t\t\t7. Ver Préstamos\t\t8. Ver Socios No Fiable");
            System.out.println("9. Cambiar Localización de un Libro\t\t\t\t\t\t\tX. GUARDAR datos");
            System.out.print("\nIngresa una opción y presione enter: ");
            try {
                option = scanner.next();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }

            switch (option) {
                case "0":
                    System.out.println("Gracias por utilizar el sistema.");
                    break;
                case "1":
                    tablaSocio.agregarSocio();
                    break;
                case "2":
                    tablaLibro.agregarLibro();
                    break;
                case "3":
                    tablaPrestamo.agregarPrestamo(tablaLibro, tablaSocio);
                    break;
                case "4":
                    tablaPrestamo.devolucion(tablaLibro, tablaSocio);
                    break;
                case "5":
                    System.out.println(tablaSocio);
                    presioneCualquierTecla();
                    break;
                case "6":
                    System.out.println(tablaLibro);
                    presioneCualquierTecla();
                    break;
                case "7":
                    System.out.println(tablaPrestamo);
                    presioneCualquierTecla();
                    break;
                case "8":
                    tablaSocio.verSociosNoFiables(conf);
                    presioneCualquierTecla();
                    break;
                case "9":
                    tablaLibro.cambiarUbicacion();
                    break;
                case "X":
                    ControladorSocio.escribir(tablaSocio);
                    ControladorLibro.escribir(tablaLibro);
                    ControladorPrestamo.escribir(tablaPrestamo);
                    System.out.println("Guardando...");
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
            }
        }
        scanner.close();
        // Guardar datos
        ControladorSocio.escribir(tablaSocio);
        ControladorLibro.escribir(tablaLibro);
        ControladorPrestamo.escribir(tablaPrestamo);
    }
    private static void presioneCualquierTecla()
    {
        System.out.print("Presione Cualquier Tecla para continuar...");
        try {
            System.in.read();
        } catch(Exception ignored) {}
    }
}
