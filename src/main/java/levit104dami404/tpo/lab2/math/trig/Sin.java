package levit104dami404.tpo.lab2.math.trig;

import levit104dami404.tpo.lab2.math.general.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Sin implements Function {
    private final double eps;

    @Override
    public double calculate(double x) {
        double sin = x;
        double tmp = x;

        int i = 1;
        while (Math.abs(tmp) > eps) {
            tmp *= -x * x / (2 * i * (2 * i + 1));
            sin += tmp;
            i++;
        }

        return sin;
    }
}
