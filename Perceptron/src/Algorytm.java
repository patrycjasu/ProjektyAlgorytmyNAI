
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Algorytm {

    private final List<String> odpowiedziTreningowe;
    private final List<String> odpowiedziTestowe;
    private final List<List<Double>> zbiorTreningowy;
    private final List<List<Double>> zbiorTestowy;
    private final double alfa;
    private List<Double> wagi;
    private double odchylenie;
    private final int epoki = 1000;
    private List<String> typy;



    public Algorytm(List<String> odpowiedziTreningowe, List<String> odpowiedziTestowe, List<List<Double>> zbiorTreningowy, List<List<Double>> zbiorTestowy, HashSet<String> typy, double alfa) {
        this.odpowiedziTreningowe = odpowiedziTreningowe;
        this.odpowiedziTestowe = odpowiedziTestowe;
        this.zbiorTreningowy = zbiorTreningowy;
        this.zbiorTestowy = zbiorTestowy;
        this.alfa = alfa;
        this.odchylenie = 0;
        losowanieWag();
        this.typy = typy.stream().sorted().collect(Collectors.toList());
        uczenie();
    }


    private void losowanieWag(){
        wagi = new ArrayList<>();
        for (int i = 0; i < zbiorTreningowy.getFirst().size(); i++) {
            wagi.add(Math.random());
        }
        wagi.add(odchylenie);
    }

    private double wartoscWyjsciowa(List<Double> x){
        double net = 0;
        for (int i = 0; i < x.size(); i++) {
            net += wagi.get(i) * x.get(i);
        }
        return net;
    }

    private void modyfikacjaWag(int d, int y, List<Double> x){
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            tmp.add((x.get(i) * alfa * (d-y)) + wagi.get(i));
        }
        wagi = tmp;
    }

    public void uczenie(){
        //dla 1000 epok lub poki blad E > 0.01
        int counter = 0;
        double E = 0.0;
        do {
            for (int i = 0; i < zbiorTreningowy.size()-1; i++) {

                List<Double> x = zbiorTreningowy.get(i);
                int d;

                if (odpowiedziTreningowe.get(i).equals(typy.getFirst())) d = 0;
                else d = 1;

                double net = wartoscWyjsciowa(x);
                if (net>= 0) {
                    modyfikacjaWag(d, 1, x);
                    E += (d-1)*(d-1);
                }
                 else {
                     modyfikacjaWag(d, 0, x);
                     E += d*d;
                }
            }
            E *= (1.0/odpowiedziTestowe.size());
            counter ++;
            //System.out.println(E + " "+counter);
        }
        while (counter < epoki && E > 0.01);
    }

    public void sprawdzenie(){
        int counter = 0;
        for (int i = 0; i < odpowiedziTestowe.size(); i++) {
            double net = wartoscWyjsciowa(zbiorTestowy.get(i));
            if (net >= 0) {
                System.out.println(typy.getLast() +" = "+odpowiedziTestowe.get(i));
                if (typy.getLast().equals(odpowiedziTestowe.get(i))) counter ++;
            } else{ System.out.println(typy.getFirst() + " = "+odpowiedziTestowe.get(i));
                if (typy.getFirst().equals(odpowiedziTestowe.get(i))) counter ++;
            }
        }
        accuracy(counter);
    }

    public void dlaOsobnegoWektora(List<Double> lista){
        double net = wartoscWyjsciowa(lista);
        if (net >= 0)
            System.out.println(typy.getLast());
         else System.out.println(typy.getFirst());
        System.out.println();
    }


    public void accuracy(int counter){
        double ile = odpowiedziTestowe.size();
        ile*=1.0;
        System.out.println(counter/ile);
    }

    public void getwagi(){
        for (int i = 0; i < wagi.size(); i++) {
            System.out.print(wagi.get(i)+" ");
        }
        System.out.println();
    }
}
