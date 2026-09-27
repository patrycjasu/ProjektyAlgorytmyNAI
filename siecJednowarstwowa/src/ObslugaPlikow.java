import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ObslugaPlikow {

    private final String trening;
    private final String test;


    private final List<String> odpowiedziTest = new ArrayList<>();
    private final List<String> odpowiedziTrening = new ArrayList<>();

    private final List<String> daneTrening = new ArrayList<>();
    private final List<String> daneTest = new ArrayList<>();

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
                String [] tmp = line.split(",", 2);
                String jezyk = tmp[0];
                String tekst = tmp[1];
                if (n==0) {
                    odpowiedziTrening.add(jezyk);
                    daneTrening.add(tekst.substring(1, tekst.length()-1));
                } else {
                    odpowiedziTest.add(jezyk);
                    daneTest.add(tekst.substring(1, tekst.length()-1));
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public HashSet<String> typyDanych(){
        return new HashSet<>(odpowiedziTrening);
    }

    public List<String> getTest() {
        return daneTest;
    }
    public List<String> getTrening() {
        return daneTrening;
    }
    public List<String> getOdpowiedziTest() {
        return odpowiedziTest;
    }
    public List<String> getOdpowiedziTrening() {
        return odpowiedziTrening;
    }
}
