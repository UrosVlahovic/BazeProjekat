package group.raf.zus_pravi.model.base;

import lombok.Data;

@Data
public class Objekat {

    private int idObjekta;
    private int kvadratura;
    private int cena;

    public Objekat(int idObjekta, int kvadratura, int cena) {
        this.idObjekta = idObjekta;
        this.kvadratura = kvadratura;
        this.cena = cena;
    }

    public int getIdObjekta() {
        return idObjekta;
    }

    public void setIdObjekta(int idObjekta) {
        this.idObjekta = idObjekta;
    }

    public int getKvadratura() {
        return kvadratura;
    }

    public void setKvadratura(int kvadratura) {
        this.kvadratura = kvadratura;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
