import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // lang.train.csv lang.test.csv

        if (args.length != 2) {
            System.out.println("Brakuje plików lub stałej uczenia");
            return;
        }

        ObslugaPlikow obslugaPlikow;
        Algorytm algorytm;

        try{
            String sciezkaTrening = args[0];
            String sciezkaTest = args[1];

            obslugaPlikow = new ObslugaPlikow(sciezkaTrening, sciezkaTest);

            System.out.println(obslugaPlikow.typyDanych());

            algorytm = new Algorytm(
                    obslugaPlikow.getOdpowiedziTrening(),
                    obslugaPlikow.getOdpowiedziTest(),
                    obslugaPlikow.getTrening(),
                    obslugaPlikow.getTest(),
                    obslugaPlikow.typyDanych()
            );
            algorytm.testowanie();


        } catch (Exception e ){
            e.printStackTrace();
            return;
        }

        boolean flaga = false;
        Scanner sc = new Scanner(System.in);

        while(!flaga){
            System.out.println("Dodaj własny tekst. Aby przerwać wpisz x.");
            String odp = sc.nextLine().trim();
            if(odp.equals("x")){
                flaga = true;
                break;
            } else{
                try{

                    System.out.println(algorytm.predict(odp));

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            if (flaga) break;
        }


    }
}
