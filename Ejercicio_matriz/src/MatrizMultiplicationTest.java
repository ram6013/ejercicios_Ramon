import org.junit.Assert;
import org.junit.Test;

public class MatrizMultiplicationTest {
    @Test
    public void testMultiplicarMatrices() {
        double[][] matriz1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
        };

        double[][] matriz2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        double[][] resultadoEsperado = {
            {38, 44, 50, 56},
            {83, 98, 113, 128},
            {128, 152, 176, 200}
        };

        double[][] resultado = MatrizMultiplication.multiplicarMatrices(matriz1, matriz2);

        Assert.assertArrayEquals(resultadoEsperado, resultado);
    }
}
