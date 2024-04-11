package levit104dami404.tpo.lab2.math;

import levit104dami404.tpo.lab2.math.general.Function;
import levit104dami404.tpo.lab2.math.log.Ln;
import levit104dami404.tpo.lab2.math.log.Log;
import levit104dami404.tpo.lab2.math.trig.Cot;

public class FunctionsSystem implements Function {
    private final Cot cot;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log10;

    public FunctionsSystem(Cot cot, Ln ln, Log log2, Log log3, Log log10) {
        this.cot = cot;
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
        this.log10 = log10;
    }

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            return cot.calculate(x);
        } else {
            double log10x = log10.calculate(x);
            double log3x = log3.calculate(x);
            double log2x = log2.calculate(x);
            double lnx = ln.calculate(x);
            return Math.pow(Math.pow(((log10x / log3x) / log2x), 2) - (Math.pow(log2x, 3) + lnx), 3);
        }
    }

    public double calculateExpected(double x) {
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
