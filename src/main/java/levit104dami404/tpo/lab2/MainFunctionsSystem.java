package levit104dami404.tpo.lab2;

import levit104dami404.tpo.lab2.math.LogFunctions;
import levit104dami404.tpo.lab2.math.TrigFunctions;

public class MainFunctionsSystem {
    private MainFunctionsSystem() {
    }

    public static double calculate(double x, double eps) {
        if (x <= 0) {
            return TrigFunctions.cot(x, eps);
        } else {
            double log10x = LogFunctions.log(x, 10, eps);
            double log3x = LogFunctions.log(x, 3, eps);
            double log2x = LogFunctions.log(x, 2, eps);
            double lnx = LogFunctions.ln(x, eps);

            return Math.pow(Math.pow(((log10x / log3x) / log2x), 2) - (Math.pow(log2x, 3) + lnx), 3);
        }
    }
}
