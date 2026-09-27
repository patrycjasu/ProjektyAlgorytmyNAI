import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    private final String type;
    private List<String> odpowiedziTreningowe;
    private List<double[]> zbiorTreningowy;

    private List<Double> wagi;
    private final int epoki = 1000;
    private final double alfa = 0.01;

    public Perceptron(String type) {

        this.type = type;

    }

    public void teach(List<double[]> trening, List<String> results){
        zbiorTreningowy = trening;
        odpowiedziTreningowe = results;
        losowanieWag();
        uczenie();

    }

    private void losowanieWag(){
        wagi = new ArrayList<>();
        for (int i = 0; i < zbiorTreningowy.getFirst().length; i++) {
            wagi.add(Math.random());
        }
    }
    private double wartoscWyjsciowa(double[] x){
        double net = 0;
        for (int i = 0; i < x.length; i++) {
            net += wagi.get(i) * x[i];
        }
        return net;
    }

    private void modyfikacjaWag(int d, int y, double[] x){
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            tmp.add((x[i] * alfa * (d-y)) + wagi.get(i));
        }
        wagi = tmp;
    }

    public void uczenie(){
        //dla 1000 epok lub poki blad E > 0.01
        int counter = 0;
        double E = 0.0;
        do {
            E = 0.0;
            for (int i = 0; i < zbiorTreningowy.size()-1; i++) {

                double[] x = zbiorTreningowy.get(i);
                int d;

                if (odpowiedziTreningowe.get(i).equals(type)) d = 1;
                else d = 0;

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
            E *= (1.0/odpowiedziTreningowe.size());
            counter ++;
            //System.out.println(E + " "+counter);
        }
        while (counter < epoki && E > 0.01);
//        System.out.println(counter);
    }

    public double getNet(double [] x){
        return wartoscWyjsciowa(x);
    }

    public String getType() {
        return type;
    }

}
