package levit104dami404.tpo.lab2.math.trig;

import levit104dami404.tpo.lab2.math.general.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Cot implements Function {
    private final Sin sin;
    private final Cos cos;

    @Override
    public double calculate(double x) {
        if (Math.abs(x) % Math.PI == 0)
            throw new ArithmeticException("Котангенс не определён при x=%s".formatted(x));

        return cos.calculate(x) / sin.calculate(x);
    }
}
