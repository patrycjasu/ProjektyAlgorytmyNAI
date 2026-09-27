import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Algorytm {
    private final List<String> odpowiedziTreningowe;
    private final List<String> odpowiedziTestowe;
    private final List<String> zbiorTreningowy;
    private final List<String> zbiorTestowy;;
    private List<String> typy;
    private List<Perceptron> perceptrony;



    public Algorytm(List<String> odpowiedziTreningowe, List<String> odpowiedziTestowe, List<String> zbiorTreningowy, List<String> zbiorTestowy, HashSet<String> typy) {
        this.odpowiedziTreningowe = odpowiedziTreningowe;
        this.odpowiedziTestowe = odpowiedziTestowe;
        this.zbiorTreningowy = zbiorTreningowy;
        this.zbiorTestowy = zbiorTestowy;
        this.typy = typy.stream().sorted().collect(Collectors.toList());

        createPerceptrons();

    }

    private double [] vectorize (String text){
        double [] wektor = new double[26];
        text = text.toLowerCase();
        char[] t = text.toCharArray();

        for (int i = 0;i<text.length();i++){
            if ((int) t[i] >= (int)'a' && (int)t[i] <= (int)'z' )
                wektor[(int)t[i] - (int)'a']++;
        }
        return wektor;
    }

    private double getVectorLength (double [] wektor){

        double sum = 0;
        for (int i = 0;i<wektor.length;i++){
            sum += wektor[i]*wektor[i];
        }
        return Math.sqrt(sum);
    }

    public double[]  normalizeVector(String text){
        double [] tmp = new double [27];
        double [] curr= vectorize(text);
        double len = getVectorLength(curr);

        for (int i = 0; i< curr.length;i++){
            tmp[i] = curr[i]/len;
        }

        tmp[26] = -1.0;
        return tmp;
    }

    private List<double[]> getAllNormalizedVectots(List<String> trening){
        List<double[]> normalizedVectors = new ArrayList<>();

        for(String s : trening){
            normalizedVectors.add(normalizeVector(s.trim()));
        }

        return normalizedVectors;

    }

    public void createPerceptrons(){
        perceptrony = new ArrayList<>();
        for(int i = 0; i<typy.size(); i++){
            perceptrony.add(new Perceptron(typy.get(i)));
        }

        for(int i = 0; i<perceptrony.size();i++){
            perceptrony.get(i).teach(getAllNormalizedVectots(zbiorTreningowy), odpowiedziTreningowe);
        }
    }

    public String predict(String text){
        double maxi = -1000;
        String maxiType = "";
        for (Perceptron perceptron : perceptrony){
            double net = perceptron.getNet(normalizeVector(text));
            if (net > maxi){
                maxi = net;
                maxiType = perceptron.getType();
            }
        }
        return maxiType;
    }

    public void testowanie(){
        double ile = zbiorTestowy.size() * 1.0;
        int count = 0;
        System.out.println("Błędne: ");
        for (int i = 0; i< zbiorTestowy.size(); i++){
            if (odpowiedziTestowe.get(i).equals(predict(zbiorTestowy.get(i)))) count++;
            else
                System.out.println(zbiorTestowy.get(i) + "\nBłędnie przewidziano:  " + predict(zbiorTestowy.get(i)));
        }
        System.out.println("Accuracy: "+count/ile);
    }


}
