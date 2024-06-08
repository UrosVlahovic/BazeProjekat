package group.raf.zus_pravi.model.base;

import lombok.Data;

@Data
public class Misija {

    private int id;
    private String naziv;
    private int idPlanete;

    public Misija(int id, String naziv, int idPlanete) {
        this.id = id;
        this.naziv = naziv;
        this.idPlanete = idPlanete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getIdPlanete() {
        return idPlanete;
    }

    public void setIdPlanete(int idPlanete) {
        this.idPlanete = idPlanete;
    }
}
