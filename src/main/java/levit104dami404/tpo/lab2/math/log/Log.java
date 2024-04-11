package levit104dami404.tpo.lab2.math.log;

import levit104dami404.tpo.lab2.math.general.Function;

public class Log implements Function {
    private final Ln ln;
    private final int a;

    public Log(Ln ln, int a) {
        this.ln = ln;
        this.a = a;

        if (a <= 0 || a == 1)
            throw new ArithmeticException("Логарифм не определён при a=%s".formatted(a));
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(a);
    }
}
