import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObslugaPlikow {

    private final String trening;
    private final String test;

    private final List<String> odpowiedziTest = new ArrayList<>();
    private final List<String> odpowiedziTrening = new ArrayList<>();

    private final List<List<Double>>daneTrening = new ArrayList<>();
    private final List<List<Double>>daneTest = new ArrayList<>();

    public ObslugaPlikow(String trening, String test) {

        this.trening = trening;
        this.test = test;

        pobranieDanych(trening, 0); //0 lub 1 w zaleznosci czy testowy czy treningowy
        pobranieDanych(test, 1);

    }

    private void pobranieDanych(String dane, int n){
        try(BufferedReader br = new BufferedReader(new FileReader(dane))) {
            String line;
            while ((line=br.readLine())!=null){
                String [] tmp = line.split(",");
                if (n==0) {
                    odpowiedziTrening.add(tmp[tmp.length-1]);
                    daneTrening.add(przygotowanieLinii(line));
                } else {
                    odpowiedziTest.add(tmp[tmp.length-1]);
                    daneTest.add(przygotowanieLinii(line));
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        //if (n==0) System.out.println(odpowiedzi);
    }

    private List<Double> przygotowanieLinii(String line){
        String [] tmp = line.split(",");
        List<Double> linia = Arrays.stream(tmp)
                .limit(tmp.length-1)
                .map(Double::parseDouble)
                .toList();
        //System.out.println(linia+" wynik: "+ tmp[tmp.length-1]);
        return linia;
    }

    public List<List<Double>> getTest() {
        return daneTest;
    }
    public List<List<Double>> getTrening() {
        return daneTrening;
    }
    public List<String> getOdpowiedziTest() {
        return odpowiedziTest;
    }

    public List<String> getOdpowiedziTrening() {
        return odpowiedziTrening;
    }
}
