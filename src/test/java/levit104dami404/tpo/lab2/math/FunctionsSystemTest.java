package levit104dami404.tpo.lab2.math;

import levit104dami404.tpo.lab2.math.log.*;
import levit104dami404.tpo.lab2.math.trig.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static levit104dami404.tpo.lab2.math.TestUtils.assignValues;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class FunctionsSystemTest {
    static final double eps = 1e-10;

    static Sin mockSin;
    static Cos mockCos;
    static Cot mockCot;
    static Ln mockLn;
    static Log mockLog2;
    static Log mockLog3;
    static Log mockLog10;

    @BeforeAll
    static void init() {
        mockSin = mock(Sin.class);
        mockCos = mock(Cos.class);
        mockCot = mock(Cot.class);
        mockLn = mock(Ln.class);
        mockLog2 = mock(Log.class);
        mockLog3 = mock(Log.class);
        mockLog10 = mock(Log.class);

        String dirName = "src/test/resources/";
        String separator = ",";

        assignValues(separator, dirName + "sin.csv", mockSin);
        assignValues(separator, dirName + "cos.csv", mockCos);
        assignValues(separator, dirName + "cot.csv", mockCot);
        assignValues(separator, dirName + "ln.csv", mockLn);
        assignValues(separator, dirName + "log2.csv", mockLog2);
        assignValues(separator, dirName + "log3.csv", mockLog3);
        assignValues(separator, dirName + "log10.csv", mockLog10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateAllMocks(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, mockLn, mockLog2, mockLog3, mockLog10);
        assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCot(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(mockSin, mockCos), mockLn, mockLog2, mockLog3, mockLog10);
        assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCotAndCos(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(mockSin, new Cos(mockSin)), mockLn, mockLog2, mockLog3, mockLog10);
        assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCotAndCosAndSin(double x, double y) {
        Sin originalSin = new Sin(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(originalSin, new Cos(originalSin)), mockLn, mockLog2, mockLog3, mockLog10);
        assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, mockLn, new Log(mockLn, 2), new Log(mockLn, 3), new Log(mockLn, 10));
        assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLogAndLn(double x, double y) {
        Ln originalLn = new Ln(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, originalLn, new Log(originalLn, 2), new Log(originalLn, 3), new Log(originalLn, 10));
        assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalAll(double x, double y) {
        Sin originalSin = new Sin(1e-20);
        Ln originalLn = new Ln(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(originalSin, new Cos(originalSin)), originalLn, new Log(originalLn, 2), new Log(originalLn, 3), new Log(originalLn, 10));
        assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI})
    void checkCotExceptionForOriginalCot(double x) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(mockSin, mockCos), mockLn, mockLog2, mockLog3, mockLog10);
        assertThrows(ArithmeticException.class, () -> functionsSystem.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI})
    void checkCotExceptionForOriginalCotAndCos(double x) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(mockSin, new Cos(mockSin)), mockLn, mockLog2, mockLog3, mockLog10);
        assertThrows(ArithmeticException.class, () -> functionsSystem.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI})
    void checkCotExceptionForOriginalCotAndCosAndSin(double x) {
        Sin originalSin = new Sin(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(originalSin, new Cos(originalSin)), mockLn, mockLog2, mockLog3, mockLog10);
        assertThrows(ArithmeticException.class, () -> functionsSystem.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI})
    void checkCotExceptionForOriginalAll(double x) {
        Sin originalSin = new Sin(1e-20);
        Ln originalLn = new Ln(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(originalSin, new Cos(originalSin)), originalLn, new Log(originalLn, 2), new Log(originalLn, 3), new Log(originalLn, 10));
        assertThrows(ArithmeticException.class, () -> functionsSystem.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1})
    void checkDenominatorExceptionForOriginalLog(double x) {
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, mockLn, new Log(mockLn, 2), new Log(mockLn, 3), new Log(mockLn, 10));
        assertThrows(ArithmeticException.class, () -> functionsSystem.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1})
    void checkDenominatorExceptionForOriginalLogAndLn(double x) {
        Ln originalLn = new Ln(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, originalLn, new Log(originalLn, 2), new Log(originalLn, 3), new Log(originalLn, 10));
        assertThrows(ArithmeticException.class, () -> functionsSystem.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1})
    void checkDenominatorExceptionForOriginalAll(double x) {
        Sin originalSin = new Sin(1e-20);
        Ln originalLn = new Ln(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(originalSin, new Cos(originalSin)), originalLn, new Log(originalLn, 2), new Log(originalLn, 3), new Log(originalLn, 10));
        assertThrows(ArithmeticException.class, () -> functionsSystem.calculate(x));
    }
}
