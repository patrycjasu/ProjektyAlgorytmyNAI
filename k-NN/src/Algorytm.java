import java.util.*;

public class Algorytm {

    private List<String> listaWynikow = new ArrayList<>();

    private final List<String> odpowiedziTreningowe;
    private final List<String> odpowiedziTestowe;
    private final List<List<Double>> zbiorTreningowy;
    private final List<List<Double>> zbiorTestowy;
    private final int k;


    public Algorytm(List<String> odpowiedziTreningowe, List<String> odpowiedziTestowe, List<List<Double>> zbiorTreningowy, List<List<Double>> zbiorTestowy, int k) {
        this.odpowiedziTreningowe = odpowiedziTreningowe;
        this.odpowiedziTestowe = odpowiedziTestowe;
        this.zbiorTreningowy = zbiorTreningowy;
        this.zbiorTestowy = zbiorTestowy;
        this.k = k;

    }


    private List<Pair> odlegloscEuklidesowaLista(List<Double> punkt) {

        List<Pair> lista = new ArrayList<>();

        for(int j = 0; j < zbiorTreningowy.size(); j++) {
            double wynik = 0;
            double tmp;
            for (int i = 0; i < zbiorTreningowy.get(j).size(); i++) {
                tmp = Math.pow(zbiorTreningowy.get(j).get(i) - punkt.get(i), 2);
                wynik += tmp;
            }
            wynik = Math.sqrt(wynik);
            lista.add(new Pair(wynik, odpowiedziTreningowe.get(j)));
        }
        return lista;
    }

    private String predykcja(List<Pair>lista){
        int noweK = k;
        Collections.sort(lista);
        boolean res= false;
        String wynik = "";

        do{
            List<Pair> kandydaci = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                if (i < noweK) {
                    kandydaci.add(lista.get(i));
                } else {
                    if (kandydaci.get(kandydaci.size()-1).getWynik() == lista.get(i).getWynik()) { //uwzglednienie ze moga byc takie same wartosci
                        kandydaci.add(lista.get(i));
                    }
                }
            }

            Map<String, Integer> mapa = new HashMap<>();
            for (int i = 0; i < kandydaci.size(); i++) {
                if (mapa.containsKey(kandydaci.get(i).getOdpowiedz())) {
                    mapa.put(kandydaci.get(i).getOdpowiedz(), mapa.get(kandydaci.get(i).getOdpowiedz()) + 1);
                } else {
                    mapa.put(kandydaci.get(i).getOdpowiedz(), 1);
                }
            }

            //zliczenie maksymalnej ile razy wystepuje aka czy jest tylko jedna
            int maksi = 0;
            int count = 1;
            for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
                if (entry.getValue() > maksi){
                    maksi = entry.getValue();
                    count = 1;
                    wynik = entry.getKey();
                } else if (entry.getValue() == maksi){
                    count++;
                }
            }

            if (count==1)
                res = true;
            else
                noweK--;
        } while (!res);
        return wynik;
    }

    public void dlaOsobnegoWektora(List<Double> lista){
        System.out.println(predykcja(odlegloscEuklidesowaLista(lista)));
    }

    public double accuracy(){
        int ile = odpowiedziTestowe.size();
        double count = 0.0;

        for (int i = 0; i<zbiorTestowy.size(); i++){
            List< Pair> tmp = odlegloscEuklidesowaLista(zbiorTestowy.get(i));
            String wynik = predykcja(tmp);
            listaWynikow.add(wynik);

            if (wynik.equals(odpowiedziTestowe.get(i))) {
                count++;
            }

        }

        return count/ile;
    }

}
