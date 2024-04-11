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

    static Sin sin;
    static Cos cos;
    static Cot cot;
    static Ln ln;
    static Log log2;
    static Log log3;
    static Log log10;

    static String dirName = "src/test/resources/";
    static String separator = ",";

    private static void readValues(CsvHandler csvHandler, String filename, Function function) {
        List<String> data = csvHandler.readData(dirName + filename, true);
        data.forEach(row -> {
            String[] values = row.split(separator);
            Mockito.when(function.calculate(Double.parseDouble(values[0]))).thenReturn(Double.parseDouble(values[1]));
        });
    }

    @BeforeAll
    static void init() {
        sin = Mockito.mock(Sin.class);
        cos = Mockito.mock(Cos.class);
        cot = Mockito.mock(Cot.class);
        ln = Mockito.mock(Ln.class);
        log2 = Mockito.mock(Log.class);
        log3 = Mockito.mock(Log.class);
        log10 = Mockito.mock(Log.class);

        CsvHandler csvHandler = new CsvHandler(separator);
        readValues(csvHandler, "sin.csv", sin);
        readValues(csvHandler, "cos.csv", cos);
        readValues(csvHandler, "cot.csv", cot);
        readValues(csvHandler, "ln.csv", ln);
        readValues(csvHandler, "log2.csv", log2);
        readValues(csvHandler, "log3.csv", log3);
        readValues(csvHandler, "log10.csv", log10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateAllMocks(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, ln, log2, log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCot(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(sin, cos), ln, log2, log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCotAndSin(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(new Sin(1e-20), cos), ln, log2, log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCotAndCos(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(sin, new Cos(sin)), ln, log2, log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalCotAndSinAndCos(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(new Cot(new Sin(1e-20), new Cos(sin)), ln, log2, log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLn(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, new Ln(1e-20), log2, log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog2(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, ln, new Log(ln, 2), log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog3(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, ln, log2, new Log(ln, 3), log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog10(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, ln, log2, log3, new Log(ln, 10));
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog2AndLn(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, ln, new Log(new Ln(1e-20), 2), log3, log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog3AndLn(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, ln, log2, new Log(new Ln(1e-20), 3), log10);
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv", numLinesToSkip = 1)
    void calculateOriginalLog10AndLn(double x, double y) {
        FunctionsSystem functionsSystem = new FunctionsSystem(cot, ln, log2, log3, new Log(new Ln(1e-20), 10));
        Assertions.assertEquals(y, functionsSystem.calculate(x), eps);
    }
}
