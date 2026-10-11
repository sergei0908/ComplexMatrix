public class Complex {
    double re; // действительная часть
    double im; // мнимая часть

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // сложение: (a+bi) + (c+di) = (a+c) + (b+d)i
    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    // вычитание
    public Complex sub(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    // умножение: (a+bi)(c+di) = (ac - bd) + (ad + bc)i
    public Complex mul(Complex other) {
        double newRe = this.re * other.re - this.im * other.im;
        double newIm = this.re * other.im + this.im * other.re;
        return new Complex(newRe, newIm);
    }

    // деление: (a+bi)/(c+di) = ((ac + bd) + (bc - ad)i) / (c^2 + d^2)
    public Complex div(Complex other) {
        double denom = other.re * other.re + other.im * other.im;
        if (denom == 0) {
            throw new ArithmeticException("Деление на ноль!!!");
        }
        double newRe = (this.re * other.re + this.im * other.im) / denom;
        double newIm = (this.im * other.re - this.re * other.im) / denom;
        return new Complex(newRe, newIm);
    }

    // проверка на ноль (с небольшой погрешностью)
    public boolean isZero() {
        return Math.abs(re) < 1e-9 && Math.abs(im) < 1e-9;
    }

    // вывод
    public String toString() {
        if (im >= 0) {
            return re + "+" + im + "i";
        } else {
            return re + "" + im + "i";
        }
    }
}
