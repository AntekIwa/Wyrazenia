public abstract class Dwuargumentowe extends Wyrazenie{
    protected Wyrazenie lewe;
    protected Wyrazenie prawe;

    public Dwuargumentowe(Wyrazenie lewe, Wyrazenie prawe){
        this.lewe = lewe;
        this.prawe = prawe;
    }

    public abstract String operator();

    @Override
    public String toString(){
        String wyrLewe = lewe.toString();
        String wyrPrawe = prawe.toString();

        if(lewe.priorytet() < this.priorytet()){
            wyrLewe = "(" + wyrLewe + ")";
        }
        if(prawe.priorytet() <= this.priorytet()){
            wyrPrawe = "(" + wyrPrawe + ")";
        }
        return wyrLewe + " " + operator() + " " + wyrPrawe;
    }

}
