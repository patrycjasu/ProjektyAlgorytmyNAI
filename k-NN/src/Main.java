import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Wykres wykres = new Wykres();

        if (args.length != 3) {
            System.out.println("Brakuje plików lub liczby k");
            return;
        }

        ObslugaPlikow obslugaPlikow;
        Algorytm algorytm;

        try{
            int k = Integer.parseInt(args[0]);
            String sciezkaTrening = args[1];
            String sciezkaTest = args[2];

            obslugaPlikow = new ObslugaPlikow(sciezkaTrening, sciezkaTest);

            algorytm = new Algorytm(
                    obslugaPlikow.getOdpowiedziTrening(),
                    obslugaPlikow.getOdpowiedziTest(),
                    obslugaPlikow.getTrening(),
                    obslugaPlikow.getTest(),
                    k);

            algorytm.accuracy();


        } catch (Exception e ){
            System.out.println(e.getMessage());
            return;
        }


        boolean flaga = false;
        Scanner sc = new Scanner(System.in);

        while(!flaga){
            List<Double> lista = new ArrayList<>();
            System.out.println("Dodaj własny wektor wpisując kolejne wymiary po enterze. Aby przerwać wpisz x.");
            while(lista.size()<obslugaPlikow.getTest().getFirst().size()){
                String odp = sc.nextLine().trim();
                if(odp.equals("x")){
                    flaga = true;
                    break;
                } else{
                    try{
                        lista.add(Double.parseDouble(odp));
                    } catch(NumberFormatException e){
                        System.out.println(e.getMessage()+" nie jest to liczba");
                    }
                }

            }
            if (flaga) break;
            algorytm.dlaOsobnegoWektora(lista);

        }


    }
}
