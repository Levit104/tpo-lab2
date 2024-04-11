package levit104dami404.tpo.lab2.math.trig;

import levit104dami404.tpo.lab2.math.general.Function;

public class Cos implements Function {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    @Override
    public double calculate(double x) {
        return sin.calculate(Math.PI / 2 + x);
    }
}
