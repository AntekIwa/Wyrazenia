public class Main {
    public static void main(String[] args){
        // Przygotowujemy nasze podstawowe klocki
        Wyrazenie trzy = new Stala(3);
        Wyrazenie x = new Zmienna();

        System.out.println("--- TEST 1: Nawiasowanie (wymuszone) ---");
        // Tworzymy: (3 + 3) * 3
        Wyrazenie dodawanie1 = new Plus(trzy, trzy);
        Wyrazenie mnozenie1 = new Mnozenie(dodawanie1, trzy);

        System.out.println("Wzór: " + mnozenie1.toString()); // Powinno dodać nawiasy wokół dodawania!
        System.out.println("Wynik dla x=0: " + mnozenie1.policz(0));
        System.out.println();


        System.out.println("--- TEST 2: Nawiasowanie (naturalne) ---");
        // Tworzymy: 3 + 3 * 3
        Wyrazenie mnozenie2 = new Mnozenie(trzy, trzy);
        Wyrazenie dodawanie2 = new Plus(trzy, mnozenie2);

        System.out.println("Wzór: " + dodawanie2.toString()); // Tutaj nawiasów nie będzie
        System.out.println("Wynik dla x=0: " + dodawanie2.policz(0));
        System.out.println();


        System.out.println("--- TEST 3: Funkcje, zmienne i Twoja całka ---");
        // Tworzymy: x * sin(x + 3)
        Wyrazenie dodawanie3 = new Plus(x, trzy);
        Wyrazenie sinus = new Sin(dodawanie3);
        Wyrazenie rownanie = new Mnozenie(x, sinus);

        System.out.println("Wzór funkcji: f(x) = " + rownanie.toString());

        // Sprawdzamy wartość dla x = 2
        // f(2) = 2 * sin(2 + 3) = 2 * sin(5)
        System.out.println("Wartość f(2) = " + rownanie.policz(2));

        // Odpalamy naszą autorską całkę z metody trapezów!
        // Liczymy pole pod wykresem od x=0 do x=5, dzieląc to na 1000 małych trapezów
        double pole = rownanie.calka(0, 5, 1000);
        System.out.println("Całka oznaczona (pole pod wykresem na przedziale [0, 5]): " + pole);
    }
}
