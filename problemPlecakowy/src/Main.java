public class Main {
    public static void main(String[] args) {

        int[] weights = {3, 1, 6, 10, 1, 4, 9, 1, 7, 2, 6, 1, 6, 2, 2, 4, 8, 1, 7, 3, 6, 2, 9, 5, 3, 3, 4, 7, 3, 5, 30, 50};
        int[] values= {7, 4, 9, 18, 9, 15, 4, 2, 6, 13, 18, 12, 12, 16, 19, 19, 10, 16, 14, 3, 14, 4, 15, 7, 5, 10, 10, 13, 19, 9, 8, 5};
        int size = 32;
        int W_max = 75;

        long wielkosc = 1L << size;

        int maxiWartosc = 0;
        int maxiWaga = 0;
        long maxiPodciag = 0;


        for (long podciag = 0; podciag < wielkosc; podciag++) {
            int obecnaWaga = 0;
            int obecnaWartosc = 0;
            for(int j = 0; j < size; j++) {
                if ( ((podciag >> j ) & 1L) == 1 ) {
                    obecnaWaga+=weights[j];
                    obecnaWartosc+=values[j];
                }
            }
            if (obecnaWaga<= W_max && obecnaWartosc >maxiWartosc){
                maxiWartosc = obecnaWartosc;
                maxiWaga = obecnaWaga;
                maxiPodciag = podciag;
            }
        }
        System.out.println(maxiWartosc+" "+maxiWaga+" "+maxiPodciag);

    }
}
