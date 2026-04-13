import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WyrazenieTest {
    @Test
    void testEwaluacjiProstychWyrazen() {
        // Przygotowujemy: x + 5
        Wyrazenie x = new Zmienna();
        Wyrazenie wzor = new Plus(x, new Stala(5));

        // Sprawdzamy dla x = 3, powinno wyjść 8
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

        // Pochodna z 'x' to zawsze 1
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

        assertEquals(10.0, pochodna.policz(5), 0.0001);
        assertEquals(20.0, pochodna.policz(10), 0.0001);
    }

    @Test
    void testPochodnejZlozonejZFunkcja() {
        // Funkcja: f(x) = x * sin(x)
        Wyrazenie x = new Zmienna();
        Wyrazenie f = new Mnozenie(x, new Sin(x));

        Wyrazenie pochodna = f.pochodna();

        // f'(x) = 1*sin(x) + x*cos(x)*1
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
        Wyrazenie mnozenie = new Mnozenie(new Plus(trzy, trzy), trzy);
        assertEquals("(3 + 3) * 3", mnozenie.toString());
    }

    // --- NOWE TESTY DLA ODEJMOWANIA I DZIELENIA ---

    @Test
    void testEwaluacjiOdejmowaniaIDzielenia() {
        Wyrazenie x = new Zmienna();

        // Funkcja: f(x) = x - 5. Dla x = 15 wynik to 10.
        Wyrazenie odejmowanie = new Odejmowanie(x, new Stala(5));
        assertEquals(10.0, odejmowanie.policz(15), 0.0001);

        // Funkcja: f(x) = x / 2. Dla x = 10 wynik to 5.
        Wyrazenie dzielenie = new Dzielenie(x, new Stala(2));
        assertEquals(5.0, dzielenie.policz(10), 0.0001);
    }

    @Test
    void testPochodnejOdejmowaniaIDzielenia() {
        Wyrazenie x = new Zmienna();

        // 1. Pochodna z f(x) = x - 5 wynosi f'(x) = 1
        Wyrazenie fMinus = new Odejmowanie(x, new Stala(5));
        Wyrazenie fMinusPochodna = fMinus.pochodna();
        assertEquals(1.0, fMinusPochodna.policz(100), 0.0001);

        // 2. Pochodna z f(x) = x / 2 wynosi f'(x) = 1/2 = 0.5
        Wyrazenie fDzielenie = new Dzielenie(x, new Stala(2));
        Wyrazenie fDzieleniePochodna = fDzielenie.pochodna();
        assertEquals(0.5, fDzieleniePochodna.policz(10), 0.0001);
        assertEquals(0.5, fDzieleniePochodna.policz(999), 0.0001); // Pochodna jest stała i wynosi 0.5
    }

    @Test
    void testPriorytetowDlaOdejmowaniaIDzielenia() {
        Wyrazenie dziesiec = new Stala(10);
        Wyrazenie dwa = new Stala(2);

        // Budujemy: 10 - 10 / 2 (dzielenie jest ważniejsze, nie ma nawiasów)
        Wyrazenie bezNawiasow = new Odejmowanie(dziesiec, new Dzielenie(dziesiec, dwa));
        assertEquals("10 - 10 / 2", bezNawiasow.toString());
        assertEquals(5.0, bezNawiasow.policz(0), 0.0001); // 10 - 5 = 5

        // Budujemy: (10 - 10) / 2 (wymuszamy nawiasy wokół odejmowania)
        Wyrazenie zNawiasami = new Dzielenie(new Odejmowanie(dziesiec, dziesiec), dwa);
        assertEquals("(10 - 10) / 2", zNawiasami.toString());
        assertEquals(0.0, zNawiasami.policz(0), 0.0001); // 0 / 2 = 0
    }
}