public class Pair implements Comparable<Pair> {
    private Double el1;
    private String el2;
    public Pair(Double el1, String el2) {
        this.el1 = el1;
        this.el2 = el2;
    }
    public Double getWynik() {
        return el1;
    }
    public String getOdpowiedz() {
        return el2;
    }

    @Override
    public int compareTo(Pair o) {
        return Double.compare(el1, o.el1);
    }

    @Override
    public java.lang.String toString() {
        return "Różnica: "+el1+" dla odpowiedzi "+el2;
    }
}
