import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WyrazenieTest {
    @Test
    void testEwaluacjiProstychWyrazen() {
        // Przygotowujemy: x + 5
        Wyrazenie x = new Zmienna();
        Wyrazenie wzor = new Plus(x, new Stala(5));

        // Sprawdzamy dla x = 3, powinno wyjść 8
        // Ostatni parametr (0.0001) to margines błędu dla typów double
        assertEquals(8.0, wzor.policz(3), 0.0001);
    }

    @Test
    void testEwaluacjiZFunkcjaSinus() {
        Wyrazenie x = new Zmienna();
        Wyrazenie wzor = new Sin(x);

        // sin(0) = 0
        assertEquals(0.0, wzor.policz(0), 0.0001);

        // sin(PI/2) = 1
        assertEquals(1.0, wzor.policz(Math.PI / 2), 0.0001);
    }

    @Test
    void testPochodnejZmiennejIStalej() {
        Wyrazenie x = new Zmienna();
        Wyrazenie stala = new Stala(42);

        // Pochodna z 'x' to zawsze 1 (wartość argumentu policz() nie ma znaczenia)
        assertEquals(1.0, x.pochodna().policz(100), 0.0001);

        // Pochodna ze stałej to zawsze 0
        assertEquals(0.0, stala.pochodna().policz(100), 0.0001);
    }

    @Test
    void testPochodnejMnozenia() {
        // Funkcja: f(x) = x * x
        Wyrazenie x = new Zmienna();
        Wyrazenie f = new Mnozenie(x, x);

        // Z matematyki wiemy, że f'(x) = 2x
        Wyrazenie pochodna = f.pochodna();

        // Sprawdzamy, czy dla x=5 wynik to 10
        assertEquals(10.0, pochodna.policz(5), 0.0001);

        // Dla x=10 powinno wyjść 20
        assertEquals(20.0, pochodna.policz(10), 0.0001);
    }

    @Test
    void testPochodnejZlozonejZFunkcja() {
        // Funkcja: f(x) = x * sin(x)
        Wyrazenie x = new Zmienna();
        Wyrazenie f = new Mnozenie(x, new Sin(x));

        Wyrazenie pochodna = f.pochodna();

        // f'(x) = 1*sin(x) + x*cos(x)*1
        // Obliczmy spodziewany wynik w czystej Javie dla x = PI
        double xWartosc = Math.PI;
        double spodziewanyWynik = Math.sin(xWartosc) + xWartosc * Math.cos(xWartosc);

        assertEquals(spodziewanyWynik, pochodna.policz(xWartosc), 0.0001);
    }

    @Test
    void testPoprawnegoWstawianiaNawiasowString() {
        Wyrazenie trzy = new Stala(3);

        // Budujemy: 3 + 3 * 3
        Wyrazenie dodawanie = new Plus(trzy, new Mnozenie(trzy, trzy));
        assertEquals("3 + 3 * 3", dodawanie.toString());

        // Budujemy: (3 + 3) * 3
        // Ponieważ dodawanie ma niższy priorytet niż mnożenie, nawiasy muszą się pojawić!
        Wyrazenie mnozenie = new Mnozenie(new Plus(trzy, trzy), trzy);
        assertEquals("(3 + 3) * 3", mnozenie.toString());
    }

}
