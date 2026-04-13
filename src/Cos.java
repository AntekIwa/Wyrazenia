public class Cos extends Jednoargumentowe{
    public Cos(Wyrazenie arg){
        super(arg);
    }

    @Override
    public int priorytet(){
        return 10;
    }

    @Override
    public double policz(double x){
        return Math.cos(arg.policz(x));
    }

    @Override
    public String toString(){
        return "cos(" + arg.toString() + ")";
    }

    @Override
    public Wyrazenie pochodna(){
        return new Mnozenie(new Mnozenie(new Stala(-1), new Sin(arg)), arg.pochodna());
    }
}
