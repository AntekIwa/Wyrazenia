public class Dzielenie extends Dwuargumentowe{
    public Dzielenie(Wyrazenie lewe, Wyrazenie prawe){
        super(lewe, prawe);
    }

    @Override
    public double policz(double x){
        return lewe.policz(x) / prawe.policz(x);
    }

    @Override
    public int priorytet(){
        return 2;
    }

    @Override
    public String operator(){
        return "/";
    }

    @Override
    public Wyrazenie pochodna(){
        return new Dzielenie(new Odejmowanie(new Mnozenie(lewe.pochodna(), prawe), new Mnozenie(lewe, prawe.pochodna())), new Mnozenie(prawe, prawe));
    }
}
