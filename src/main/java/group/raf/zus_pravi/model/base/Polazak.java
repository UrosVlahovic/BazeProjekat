package group.raf.zus_pravi.model.base;

import lombok.Data;

import java.sql.Date;

@Data
public class Polazak {

    private int id;
    private String vozilo;
    private Date datum;
    private String sediste;
    private int idKorisnika;
    private int idKarte;

    public Polazak(int id, String vozilo, Date datum, String sediste, int idKorisnika, int idKarte) {
        this.id = id;
        this.vozilo = vozilo;
        this.datum = datum;
        this.sediste = sediste;
        this.idKorisnika = idKorisnika;
        this.idKarte = idKarte;
    }

    public Polazak(int id, Date datum, int idKorisnika, int idKarte) {
        this.id = id;
        this.datum = datum;
        this.idKorisnika = idKorisnika;
        this.idKarte = idKarte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVozilo() {
        return vozilo;
    }

    public void setVozilo(String vozilo) {
        this.vozilo = vozilo;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getSediste() {
        return sediste;
    }

    public void setSediste(String sediste) {
        this.sediste = sediste;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public int getIdKarte() {
        return idKarte;
    }

    public void setIdKarte(int idKarte) {
        this.idKarte = idKarte;
    }
}
