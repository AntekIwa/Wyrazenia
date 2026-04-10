public class Mnozenie extends Dwuargumentowe{
    public Mnozenie(Wyrazenie lewe, Wyrazenie prawe){
        super(lewe, prawe);
    }

    @Override
    public double policz(double x){
        return lewe.policz(x) * prawe.policz(x);
    }

    @Override
    public int priorytet(){
        return 2;
    }

    @Override
    public String operator(){
        return "*";
    }

}
