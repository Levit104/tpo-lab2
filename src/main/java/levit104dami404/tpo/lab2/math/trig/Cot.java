package levit104dami404.tpo.lab2.math.trig;

import levit104dami404.tpo.lab2.math.general.Function;

public class Cot implements Function {
    private final Sin sin;
    private final Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double calculate(double x) {
        if (Math.abs(x) % Math.PI == 0)
            throw new ArithmeticException("Котангенс не определён при x=%s".formatted(x));

        return cos.calculate(x) / sin.calculate(x);
    }
}
