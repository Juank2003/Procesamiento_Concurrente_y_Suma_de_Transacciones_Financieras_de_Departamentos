
import java.io.*;

public class UtilidadesFicheros {
    public static long obtenerSumaTransacciones(String[] archivos) {
        long sumaTotal = 0;
        for (String archivo : archivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    long transaccion = Long.parseLong(linea);
                    sumaTotal += transaccion;
                }
            } catch (IOException | NumberFormatException e) {
                // Manejar excepciones de lectura y formato aquí
                e.printStackTrace();
            }
        }
        return sumaTotal;
    }
}