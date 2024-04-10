package levit104dami404.tpo.lab2.math;

public class LogFunctions {
    private LogFunctions() {
    }

    public static double ln(double x, double eps) {
        if (x <= 0) {
            throw new ArithmeticException("ln определен только для положительных чисел");
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

    public static double log(double x, double a, double eps) {
        return ln(x, eps) / ln(a, eps);
    }
}
