package dominio.socio;

import dominio.clasesGenericas.Configuracion;
import dominio.clasesGenericas.Elemento;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ControladorSocio {
    private static final File socioArchivo = new File ("src/main/resources/socios.csv");
    public static TablaSocio leer(Configuracion conf) {
        TablaSocio tablaSocio = new TablaSocio(conf);
        try {
            Scanner lectorSocioArchivo = new Scanner(socioArchivo);
            while (lectorSocioArchivo.hasNextLine()) {
                String linea = lectorSocioArchivo.nextLine();
                List<Object> argumentos = new ArrayList<>();

                if (!linea.isEmpty()) {
                    String[] lineas = linea.split("\t");
                    argumentos.add(Integer.parseInt(lineas[0].trim()));
                    argumentos.add(lineas[1].trim());
                    argumentos.add(lineas[2].trim());
                    argumentos.add(Integer.parseInt(lineas[3].trim()));

                    tablaSocio.agregarSocio(argumentos);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró archivo de socios.");
        } catch (NoSuchElementException ne) {
            System.out.println(ne.getMessage());
        }
        return tablaSocio;
    }

    public static void escribir(TablaSocio tablaSocio) {
        try {
            socioArchivo.delete();
            socioArchivo.createNewFile();
            FileWriter escritorSocioArchivo = new FileWriter(socioArchivo);
            for(Elemento s: tablaSocio.getTabla().values()) {
                Socio socio = (Socio) s;
                escritorSocioArchivo.write(socio.getNumeroSocio() + "\t" +
                        socio.getNombre() + "\t" +
                        socio.getDireccion() + "\t" +
                        socio.getNumeroDeLibrosPrestados() + "\n");
            }
            escritorSocioArchivo.flush();
            escritorSocioArchivo.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo de socios.");
        }
    }

    public static String[] inputSocioNuevo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("\nIngrese la Dirección: ");
        String direccion = scanner.nextLine();
        return new String[]{nombre, direccion};
    }
}
