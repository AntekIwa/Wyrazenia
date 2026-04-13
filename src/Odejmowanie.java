public class Odejmowanie extends Dwuargumentowe{
    public Odejmowanie(Wyrazenie lewe, Wyrazenie prawe){
        super(lewe, prawe);
    }

    @Override
    public double policz(double x){
        return lewe.policz(x) - prawe.policz(x);
    }
    @Override
    public int priorytet(){
        return 1;
    }
    @Override
    public String operator(){
        return "-";
    }
    @Override
    public Wyrazenie pochodna(){
        return new Odejmowanie(lewe.pochodna(), prawe.pochodna());
    }

}
