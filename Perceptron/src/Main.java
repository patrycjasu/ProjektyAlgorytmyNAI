import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Brakuje plików lub stałej uczenia");
            return;
        }

        ObslugaPlikow obslugaPlikow;
        Algorytm algorytm;

        try{
            String sciezkaTrening = args[0];
            String sciezkaTest = args[1];
            double alfa = Double.parseDouble(args[2]);

            obslugaPlikow = new ObslugaPlikow(sciezkaTrening, sciezkaTest);

            algorytm = new Algorytm(
                    obslugaPlikow.getOdpowiedziTrening(),
                    obslugaPlikow.getOdpowiedziTest(),
                    obslugaPlikow.getTrening(),
                    obslugaPlikow.getTest(),
                    obslugaPlikow.typyDanych(),
                    alfa
            );

           algorytm.sprawdzenie();


        } catch (Exception e ){
            e.printStackTrace();
            return;
        }

        boolean flaga = false;
        Scanner sc = new Scanner(System.in);

        while(!flaga){
            List<Double> lista = new ArrayList<>();
            System.out.println("Dodaj własny wektor wpisując kolejne wymiary po enterze. Aby przerwać wpisz x.");
            while(lista.size()<obslugaPlikow.getTest().getFirst().size()-1){
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
            lista.add(-1.0);
            algorytm.dlaOsobnegoWektora(lista);

        }


    }
}
