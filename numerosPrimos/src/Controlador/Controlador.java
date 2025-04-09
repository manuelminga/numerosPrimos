package Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Controlador {
    private int[] numeros;
    private int[] primos;

    public Controlador() {
        numeros = new int[0];
        primos = new int[0];
    }

    public void leerArchivo(String nombreArchivo) throws IOException {
        File file = new File(nombreArchivo);
        BufferedReader leemos = new BufferedReader(new FileReader(file));

        // Contamos líneas
        int contador_primos = 0;
        while (leemos.readLine() != null) contador_primos++;
        leemos.close();

        // Creamos un arreglo con el tamaño de las líneas, donde se le reserva el espacio en memoria
        numeros = new int[contador_primos];

        // volvemos a leer nuevamente, para la lectura y almacenamiento de los datos
        leemos = new BufferedReader(new FileReader(file));
        for (int i = 0; i < contador_primos; i++) {
            String linea = leemos.readLine();
            try {
                numeros[i] = Integer.parseInt(linea.trim()); // Aquí almacenamos cada número en el arreglo
            } catch (NumberFormatException e) {
                System.err.println("No hay ningun valor numérico en línea " + (i + 1));
                numeros[i] = 0;   // Le asignamos un valor por defecto en caso de que ocurra algun error
            }
        }
        leemos.close();
    }

    public void identificarPrimos() {
        primos = new int[numeros.length];
        int contador = 0;
        for (int numero : numeros) {
            if (esPrimo(numero)) {
                primos[contador++] = numero;
            }
        }

        // Redimensionar el arreglo de primos
        int[] primosTemp = new int[contador];
        System.arraycopy(primos, 0, primosTemp, 0, contador);
        primos = primosTemp;
    }

    private boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public int getCantidadPrimos() {
        return primos.length;
    }

    public int[] getPrimos() {
        return primos;
    }

    // Método para procesar todo y mostrar los resultados
    public void presentarse(String nombreArchivo) {
        long tiempoInicio = System.currentTimeMillis(); // Medición de tiempo

        try {
            leerArchivo(nombreArchivo);     // Paso 1: Leer archivo
            identificarPrimos();            // Paso 2: Identificar primos

            // Paso 3: Mostrar total
            System.out.println("Total de números primos encontrados: " + getCantidadPrimos());

            // Paso 4: Mostrar los primos uno por uno
            System.out.println("Primos encontrados:");
            for (int primo : primos) {
                System.out.println(primo);
            }

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }

        // Paso 5: Mostrar tiempo de ejecución
        long tiempoFin = System.currentTimeMillis();
        long totalTiempo = tiempoFin - tiempoInicio;
        System.out.println("El tiempo de demora es: " + totalTiempo + " milisegundos");
        System.out.println("El tiempo de demora es: " + (totalTiempo / 1000.0) + " segundos");
    }
}
