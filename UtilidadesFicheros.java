
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

public class UtilidadesFicheros {
    public static long obtenerSumaTransacciones(String[] archivos) {
        AtomicLong sumaTotal = new AtomicLong(0); // Usar AtomicLong para suma concurrente
        for (String archivo : archivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    try {
                        long transaccion = Long.parseLong(linea);
                        sumaTotal.addAndGet(transaccion); // Suma concurrente
                    } catch (NumberFormatException e) {
                        // Manejar excepciones de formato de manera adecuada aquí
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                // Manejar excepciones de lectura de manera adecuada aquí
                e.printStackTrace();
            }
        }
        return sumaTotal.get(); // Obtener el valor de sumaTotal
    }
}