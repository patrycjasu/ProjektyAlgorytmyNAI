import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Wykres{

    public Wykres(){

        ObslugaPlikow op = new ObslugaPlikow("iris.data", "iris.test.data");

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("wykres.txt"))){
                for (int i = 1; i<op.getTrening().size(); i++){
                    Algorytm algorytm = new Algorytm(op.getOdpowiedziTrening(), op.getOdpowiedziTest(), op.getTrening(), op.getTest(), i);

                    String linia = i+" "+ algorytm.accuracy()+"\n";
                    bw.write(linia);
            }

        } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

}
