package levit104dami404.tpo.lab2;

import levit104dami404.tpo.lab2.io.CsvHandler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String dirName = "src/test/resources/";
        String separator = ",";
        CsvHandler csvHandler = new CsvHandler(separator);

        List<Double> vals = List.of(2.0, 5.0, 7.0, -2.0, -5.0, -7.0);

        for (Double x : vals) {
            csvHandler.writeData(dirName + "sin", "sin", x, Math.sin(x));
            csvHandler.writeData(dirName + "sin", "sin", Math.PI / 2 + x, Math.sin(Math.PI / 2 + x));
            csvHandler.writeData(dirName + "cos", "cos", x, Math.cos(x));
            csvHandler.writeData(dirName + "cot", "cot", x, Math.cos(x) / Math.sin(x));
            csvHandler.writeData(dirName + "ln", "ln", x, Math.log(x));
            csvHandler.writeData(dirName + "log2", "log2", x, Math.log(x) / Math.log(2));
            csvHandler.writeData(dirName + "log3", "log3", x, Math.log(x) / Math.log(3));
            csvHandler.writeData(dirName + "log10", "log10", x, Math.log(x) / Math.log(10));

            csvHandler.writeData(dirName + "function", "function", x, calculateExpected(x));
        }

        csvHandler.writeData(dirName + "ln", "ln", 3, Math.log(3));
        csvHandler.writeData(dirName + "ln", "ln", 10, Math.log(10));
    }

    public static double calculateExpected(double x) {
        if (x <= 0) {
            return 1 / Math.tan(x);
        } else {
            double log10x = Math.log10(x);
            double log3x = Math.log(x) / Math.log(3);
            double log2x = Math.log(x) / Math.log(2);
            double lnx = Math.log(x);
            return Math.pow(Math.pow(((log10x / log3x) / log2x), 2) - (Math.pow(log2x, 3) + lnx), 3);
        }
    }
}
