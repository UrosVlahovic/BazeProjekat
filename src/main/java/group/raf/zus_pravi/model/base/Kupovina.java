package group.raf.zus_pravi.model.base;

import lombok.Data;

import java.sql.Date;

@Data
public class Kupovina {

    private int id;
    private Date datum;
    private int idKorisnika;
    private int idObjekta;

    public Kupovina(int id, Date datum, int idKorisnika, int idObjekta) {
        this.id = id;
        this.datum = datum;
        this.idKorisnika = idKorisnika;
        this.idObjekta = idObjekta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public int getIdObjekta() {
        return idObjekta;
    }

    public void setIdObjekta(int idObjekta) {
        this.idObjekta = idObjekta;
    }
}
