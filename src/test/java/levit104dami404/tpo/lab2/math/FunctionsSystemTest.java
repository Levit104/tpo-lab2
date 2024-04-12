package levit104dami404.tpo.lab2.math;

import levit104dami404.tpo.lab2.math.general.Function;
import levit104dami404.tpo.lab2.math.trig.*;
import levit104dami404.tpo.lab2.math.log.*;
import levit104dami404.tpo.lab2.io.CsvHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.util.List;

public class FunctionsSystemTest {
    static final double eps = 1e-10;

    static Sin mockSin;
    static Cos mockCos;
    static Cot mockCot;
    static Ln mockLn;
    static Log mockLog2;
    static Log mockLog3;
    static Log mockLog10;

    static String dirName = "src/test/resources/";
    static String separator = ",";

    private static void assignValues(CsvHandler csvHandler, String filename, Function function) {
        List<String> data = csvHandler.readData(filename, true);
        data.forEach(row -> {
            String[] values = row.split(separator);
            Mockito.when(function.calculate(Double.parseDouble(values[0]))).thenReturn(Double.parseDouble(values[1]));
        });
    }

    @BeforeAll
    static void init() {
        mockSin = Mockito.mock(Sin.class);
        mockCos = Mockito.mock(Cos.class);
        mockCot = Mockito.mock(Cot.class);
        mockLn = Mockito.mock(Ln.class);
        mockLog2 = Mockito.mock(Log.class);
        mockLog3 = Mockito.mock(Log.class);
        mockLog10 = Mockito.mock(Log.class);

        CsvHandler csvHandler = new CsvHandler(separator);
        assignValues(csvHandler, dirName + "sin.csv", mockSin);
        assignValues(csvHandler, dirName + "cos.csv", mockCos);
        assignValues(csvHandler, dirName + "cot.csv", mockCot);
        assignValues(csvHandler, dirName + "ln.csv", mockLn);
        assignValues(csvHandler, dirName + "log2.csv", mockLog2);
        assignValues(csvHandler, dirName + "log3.csv", mockLog3);
        assignValues(csvHandler, dirName + "log10.csv", mockLog10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateAllMocks(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, mockLn, mockLog2, mockLog3, mockLog10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCot(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(mockSin, mockCos), mockLn, mockLog2, mockLog3, mockLog10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCotAndCos(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(mockSin, new Cos(mockSin)), mockLn, mockLog2, mockLog3, mockLog10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCotAndCosAndSin(double x, double y) {
        Sin originalSin = new Sin(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(originalSin, new Cos(originalSin)), mockLn, mockLog2, mockLog3, mockLog10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, mockLn, new Log(mockLn, 2), new Log(mockLn, 3), new Log(mockLn, 10));
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLogAndLn(double x, double y) {
        Ln originalLn = new Ln(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(mockCot, originalLn, new Log(originalLn, 2), new Log(originalLn, 3), new Log(originalLn, 10));
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalAll(double x, double y) {
        Sin originalSin = new Sin(1e-20);
        Ln originalLn = new Ln(1e-20);
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(originalSin, new Cos(originalSin)), originalLn, new Log(originalLn, 2), new Log(originalLn, 3), new Log(originalLn, 10));
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }
}
