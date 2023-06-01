import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatrizMultiplication {
    public static void main(String[] args) {
        String matriz1Archivo = "matriz1.txt";
        String matriz2Archivo = "matriz2.txt";
        String resultadoArchivo = "resultado.txt";

        try {
            double[][] matriz1 = leerMatrizDesdeArchivo(matriz1Archivo);
            double[][] matriz2 = leerMatrizDesdeArchivo(matriz2Archivo);

            if (matriz1 == null || matriz2 == null) {
                throw new IllegalArgumentException("Error al leer las matrices desde los archivos.");
            }

            double[][] resultado = multiplicarMatrices(matriz1, matriz2);

            if (resultado == null) {
                throw new IllegalArgumentException("Error al multiplicar las matrices.");
            }

            escribirMatrizEnArchivo(resultado, resultadoArchivo);

            System.out.println("Multiplicación de matrices completada. El resultado se ha guardado en el archivo " + resultadoArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer/escribir el archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la multiplicación de matrices: " + e.getMessage());
        }
    }

    public static double[][] leerMatrizDesdeArchivo(String archivo) throws IOException {
        double[][] matriz = null;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int filas = 0;
            int columnas = -1; // Inicializar con un valor negativo para detectar inconsistencias en el formato

            while ((linea = br.readLine()) != null) {
                String[] elementos = linea.split(",");

                if (columnas == -1) {
                    columnas = elementos.length;
                } else if (elementos.length != columnas) {
                    throw new IllegalArgumentException("Error en el formato de la matriz. Las filas tienen diferentes números de columnas.");
                }

                double[] fila = new double[columnas];

                for (int i = 0; i < columnas; i++) {
                    try {
                        fila[i] = Double.parseDouble(elementos[i]);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Error en el formato de la matriz. Se esperan valores numéricos.");
                    }
                }

                if (matriz == null) {
                    matriz = new double[1][columnas];
                    matriz[0] = fila;
                } else {
                    double[][] nuevaMatriz = new double[filas + 1][columnas];
                    System.arraycopy(matriz, 0, nuevaMatriz, 0, filas);
                    nuevaMatriz[filas] = fila;
                    matriz = nuevaMatriz;
                }

                filas++;
            }
        }

        return matriz;
    }

    public static void escribirMatrizEnArchivo(double[][] matriz, String archivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (int fila = 0; fila < matriz.length; fila++) {
                for (int columna = 0; columna < matriz[0].length; columna++) {
                    bw.write(String.valueOf(matriz[fila][columna]));

                    if (columna != matriz[0].length - 1) {
                        bw.write(",");
                    }
                }

                bw.newLine();
            }
        }
    }

    public static double[][] multiplicarMatrices(double[][] matriz1, double[][] matriz2) {
        int filasMatriz1 = matriz1.length;
        int columnasMatriz1 = matriz1[0].length;
        int filasMatriz2 = matriz2.length;
        int columnasMatriz2 = matriz2[0].length;

        if (columnasMatriz1 != filasMatriz2) {
            throw new IllegalArgumentException("Las matrices no son compatibles para la multiplicación.");
        }

        double[][] resultado = new double[filasMatriz1][columnasMatriz2];

        for (int i = 0; i < filasMatriz1; i++) {
            for (int j = 0; j < columnasMatriz2; j++) {
                double suma = 0.0;

                for (int k = 0; k < columnasMatriz1; k++) {
                    suma += matriz1[i][k] * matriz2[k][j];

                    // Corregir el desbordamiento si la suma supera Double.MAX_VALUE
                    if (Double.isInfinite(suma)) {
                        throw new ArithmeticException("Desbordamiento en la multiplicación de matrices.");
                    }
                }

                resultado[i][j] = suma;
            }
        }

        return resultado;
    }
}
