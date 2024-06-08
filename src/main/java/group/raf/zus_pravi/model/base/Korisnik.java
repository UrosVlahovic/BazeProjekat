package group.raf.zus_pravi.model.base;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Korisnik {

    private int id;
    private String username;
    private String password;
    private String ime;
    private String prezime;
    private int godine;

    public Korisnik() {

    }


    public Korisnik(int id, String username, String password, String ime, String prezime, int godine) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.godine = godine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Korisnik)) return false;
//        Korisnik korisnik = (Korisnik) o;
//        return Objects.equals(username, korisnik.username) && Objects.equals(password, korisnik.password);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(username, password);
//    }
}
