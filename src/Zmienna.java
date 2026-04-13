public class Zmienna extends Wyrazenie{

    @Override
    public double policz(double x){
        return x;
    }

    @Override
    public int priorytet(){
        return 10;
    }

    @Override
    public String toString(){
        return "x";
    }

    @Override
    public Wyrazenie pochodna(){
        return new Stala(1);
    }
}
