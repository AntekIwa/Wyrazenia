public class Stala extends Wyrazenie {
    private double wartosc;

    public Stala(double wartosc){
        this.wartosc = wartosc;
    }

    @Override
    public double policz(double x){
        return wartosc;
    }

    @Override
    public int priorytet(){
        return 10;
    }

    @Override
    public String toString(){
        if (wartosc == (long) wartosc) { // 3.0 -> 3
            return String.format("%d", (long) wartosc);
        }
        return String.valueOf(wartosc);
    }
}
