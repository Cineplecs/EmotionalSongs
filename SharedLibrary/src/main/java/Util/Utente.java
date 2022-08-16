package Util;

public class Utente {
    String nome, cognome, codFisc, mail, userId, password;
    Indirizzo ind;

    public Utente(){}

    public Utente(String nome, String cognome,
                  String codFisc, String mail,
                  String userId, String password,
                  Indirizzo ind) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.mail = mail;
        this.userId = userId;
        this.password = password;
        this.ind = ind;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Indirizzo getInd() {
        return ind;
    }

    public void setInd(Indirizzo ind) {
        this.ind = ind;
    }
}
