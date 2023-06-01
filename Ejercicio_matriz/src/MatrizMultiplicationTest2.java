import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MatrizMultiplicationTest2 {
    @Test
    public void testMultiplicarMatrices() throws IOException {
        String matriz1Archivo = "matriz1.txt";
        String matriz2Archivo = "matriz2.txt";
        String resultadoEsperadoArchivo = "resultadoEsperado.txt";

        double[][] matriz1 = MatrizMultiplication.leerMatrizDesdeArchivo(matriz1Archivo);
        double[][] matriz2 = MatrizMultiplication.leerMatrizDesdeArchivo(matriz2Archivo);
        double[][] resultadoEsperado = MatrizMultiplication.leerMatrizDesdeArchivo(resultadoEsperadoArchivo);

        double[][] resultado = MatrizMultiplication.multiplicarMatrices(matriz1, matriz2);

        verificarMatrices(resultadoEsperado, resultado);
    }

    public void verificarMatrices(double[][] matrizEsperada, double[][] matrizResultado) {
        int filas = matrizEsperada.length;
        int columnas = matrizEsperada[0].length;

        Assert.assertEquals("Número de filas incorrecto", filas, matrizResultado.length);
        Assert.assertEquals("Número de columnas incorrecto", columnas, matrizResultado[0].length);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Assert.assertEquals("Valor incorrecto en la posición (" + i + ", " + j + ")",
                        matrizEsperada[i][j], matrizResultado[i][j], 0.001);
            }
        }
    }
}
