import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] weights = {3, 1, 6, 10, 1, 4, 9, 1, 7, 2, 6, 1, 6, 2, 2, 4, 8, 1, 7, 3, 6, 2, 9, 5, 3, 3, 4, 7, 3, 5, 30, 50};
        int[] values= {7, 4, 9, 18, 9, 15, 4, 2, 6, 13, 18, 12, 12, 16, 19, 19, 10, 16, 14, 3, 14, 4, 15, 7, 5, 10, 10, 13, 19, 9, 8, 5};
        int size = 32;
        int W_max = 75;

        Random rand = new Random();
        int[] obecne = new int[size];

        for (int i = 0; i < obecne.length; i++) {
            obecne[i] = rand.nextInt(2);
        }

        int obecneRozw = policz(values, weights, obecne, W_max);

        int najlepszeRozw = obecneRozw;
        int [] najlepsze = obecne.clone();

        boolean flaga = true;

        while(flaga){

            flaga = false;

            for (int i = 0; i < obecne.length; i++) {

                int[] sasiad = obecne.clone();
                if (sasiad[i] == 0) sasiad[i] = 1; else sasiad[i] = 0;

                int sasiadRozw = policz(values, weights, sasiad, W_max);

                if (sasiadRozw > najlepszeRozw) {
                    najlepszeRozw = sasiadRozw;
                    najlepsze = sasiad;
                    flaga = true;
                }
            }
            obecneRozw = najlepszeRozw;
            obecne = najlepsze;

        }
        System.out.println(obecneRozw);
    }

    public static int policz(int[] wartosci, int[] wagi, int[] kombinacja, int w_max){
        int sumaWartosci = 0;
        int sumaWag = 0;
        for (int i = 0; i < kombinacja.length; i++) {
            if (kombinacja[i] == 1){
                sumaWartosci += wartosci[i];
                sumaWag += wagi[i];
            }
        }
        if (sumaWag>w_max) return 0;
        return sumaWartosci;
    }

}
