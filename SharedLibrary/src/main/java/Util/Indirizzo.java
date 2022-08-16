package Util;

public class Indirizzo {
    String identificatore, nomeVia, comune, provincia;
    int numeroCiv, cap;

    public Indirizzo(){}

    public Indirizzo(String identificatore, String nomeVia,
                     String comune, String provincia,
                     int numeroCiv, int cap) {
        this.identificatore = identificatore;
        this.nomeVia = nomeVia;
        this.comune = comune;
        this.provincia = provincia;
        this.numeroCiv = numeroCiv;
        this.cap = cap;
    }

    public String getIdentificatore() {
        return identificatore;
    }

    public void setIdentificatore(String identificatore) {
        this.identificatore = identificatore;
    }

    public String getNomeVia() {
        return nomeVia;
    }

    public void setNomeVia(String nomeVia) {
        this.nomeVia = nomeVia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getNumeroCiv() {
        return numeroCiv;
    }

    public void setNumeroCiv(int numeroCiv) {
        this.numeroCiv = numeroCiv;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }
}
