//procesara un archivo, sumara las trasnsacciones y alamacenara el resultado en el archivo correspondiente

import java.io.*;

public class ProcesadorContabilidad implements Runnable {
    private String archivo;

    public ProcesadorContabilidad(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void run() {
        long sumaDepartamento = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    long transaccion = Long.parseLong(linea);
                    sumaDepartamento += transaccion;
                } catch (NumberFormatException e) {
                    // Manejar excepciones de formato de manera adecuada aquí
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // Manejar excepciones de lectura de manera adecuada aquí
            e.printStackTrace();
        }

        // Guardar resultado en el archivo correspondiente
        String resultadoArchivo = archivo + ".res";
        try (PrintWriter pw = new PrintWriter(new FileWriter(resultadoArchivo))) {
            pw.println(sumaDepartamento);
        } catch (IOException e) {
            // Manejar excepciones de escritura de manera adecuada aquí
            e.printStackTrace();
        }
    }
}