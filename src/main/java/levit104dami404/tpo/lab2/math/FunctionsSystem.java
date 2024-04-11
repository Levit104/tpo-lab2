package levit104dami404.tpo.lab2.math;

import levit104dami404.tpo.lab2.math.general.Function;
import levit104dami404.tpo.lab2.math.log.Ln;
import levit104dami404.tpo.lab2.math.log.Log;
import levit104dami404.tpo.lab2.math.trig.Cot;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FunctionsSystem implements Function {
    private final Cot cot;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log10;

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
}
