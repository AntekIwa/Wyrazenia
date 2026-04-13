public class Plus extends Dwuargumentowe {
    public Plus(Wyrazenie lewe, Wyrazenie prawe){
        super(lewe, prawe);
    }

    @Override
    public double policz(double x){
        return lewe.policz(x) + prawe.policz(x);
    }

    @Override
    public int priorytet(){
        return 1;
    }

    @Override
    public String operator(){
        return "+";
    }

    @Override
    public Wyrazenie pochodna(){
        return new Plus(lewe.pochodna(), prawe.pochodna());
    }
}
