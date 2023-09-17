// El lanzador controlara el flujo de la aplicación, lanzando un proceso Procesador Contabilidad para cada archivo de entrada y luego agregando los resultados en un archivo de salida.


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Lanzador {
    public static void main(String[] args) {
        String[] archivos = { "informatica.txt", "gerencia.txt", "contabilidad.txt", "comercio.txt", "recursos_humanos.txt" };

        // Crear hilos para procesar cada archivo
        Thread[] threads = new Thread[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            threads[i] = new Thread(new ProcesadorContabilidad(archivos[i]));
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // Manejar interrupciones de hilos de manera adecuada aquí
                e.printStackTrace();
            }
        }

        // Sumar todas las sumas de departamentos
        long sumaGlobal = UtilidadesFicheros.obtenerSumaTransacciones(archivos);

        // Guardar resultado global
        try (PrintWriter pw = new PrintWriter(new FileWriter("Resultado_global.txt"))) {
            pw.println(sumaGlobal);
        } catch (IOException e) {
            // Manejar excepciones de escritura de manera adecuada aquí
            e.printStackTrace();
        }
    }
}
