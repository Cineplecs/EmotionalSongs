package Util;

public class Canzone {
    String titolo, autore;
    int anno;
    String album, genere, durata;
    public Canzone() {}

    public Canzone(String titolo, String autore, int anno, String album, String genere, String durata) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.album = album;
        this.genere = genere;
        this.durata = durata;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }
}
