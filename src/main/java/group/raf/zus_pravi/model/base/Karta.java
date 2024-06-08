package group.raf.zus_pravi.model.base;

import lombok.Data;

@Data
public class Karta {

    private int id;
    private int cena;
    private String odrediste;

    public Karta(int id, int cena, String odrediste) {
        this.id = id;
        this.cena = cena;
        this.odrediste = odrediste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }
}
