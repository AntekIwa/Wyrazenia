public abstract class Wyrazenie {

    public abstract int priorytet();
    public abstract double policz(double wartosc);
    public abstract String toString();

    public double calka(double poczatek, double koniec, int n){
        double dx = (koniec - poczatek)/n;
        double pole = 0;
        for(int i = 0; i < n; i++){
            double x1 = poczatek + i*dx;
            double x2 = x1 + dx;
            pole += (policz(x1) + policz(x2))*dx/2.0;
        }
        return pole;
    }
}
