public class Sin extends Jednoargumentowe{
    public Sin(Wyrazenie arg){
        super(arg);
    }

    @Override
    public int priorytet(){
        return 10;
    }

    @Override
    public double policz(double x){
        return Math.sin(arg.policz(x));
    }

    @Override
    public String toString(){
        return "sin(" + arg.toString() + ")";
    }
}
