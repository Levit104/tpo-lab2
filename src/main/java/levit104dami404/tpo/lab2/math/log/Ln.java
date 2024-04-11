package levit104dami404.tpo.lab2.math.log;

import levit104dami404.tpo.lab2.math.general.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Ln implements Function {
    private final double eps;

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            throw new ArithmeticException("Логарифм не определён при x=%s".formatted(x));
        }

        double ln = 0;
        double term = (x - 1) / (x + 1);
        double tmp = term;

        int i = 1;
        while (Math.abs(tmp) > eps) {
            ln += tmp;
            tmp *= term * term * (2 * i - 1) / (2 * i + 1);
            i++;
        }

        ln *= 2;

        return ln;
    }
}
