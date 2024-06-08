package group.raf.zus_pravi.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
public class Planeta {

    private int id;
    private String naziv;
    private int sredanja_udaljenost;
    private int najniza_temperatura;
    private int najvisa_temperatura;
    private int razlika_temperatura;
    private int procenat_kiseonika;
    private int procenat_drugog_gasa;
    private String naziv_drugog_gasa;
    private int zbir_gasova;
    private int max_visina_gravitacije;
    private int brzina_orbitiranja;
    private int broj_umrlih;
    private boolean nastanjiva;

    public Planeta(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Planeta(int id, String naziv, int sredanja_udaljenost, int najniza_temperatura, int najvisa_temperatura, int razlika_temperatura, int procenat_kiseonika,
                   int procenat_drugog_gasa, String naziv_drugog_gasa, int zbir_gasova, int max_visina_gravitacije, int brzina_orbitiranja, int broj_umrlih) {

        this.id = id;
        this.naziv = naziv;
        this.sredanja_udaljenost = sredanja_udaljenost;
        this.najniza_temperatura = najniza_temperatura;
        this.najvisa_temperatura = najvisa_temperatura;
        this.razlika_temperatura = razlika_temperatura;
        this.procenat_kiseonika = procenat_kiseonika;
        this.procenat_drugog_gasa = procenat_drugog_gasa;
        this.naziv_drugog_gasa = naziv_drugog_gasa;
        this.zbir_gasova = zbir_gasova;
        this.max_visina_gravitacije = max_visina_gravitacije;
        this.brzina_orbitiranja = brzina_orbitiranja;
        this.broj_umrlih = broj_umrlih;
        this.nastanjiva = jeNastanjiva();
    }

    public boolean jeNastanjiva(){
        if(sredanja_udaljenost < 100 || sredanja_udaljenost > 200)
            return false;
        if(najniza_temperatura < 150 || najniza_temperatura > 250)
            return false;
        if(najvisa_temperatura < 250 || najvisa_temperatura > 350)
            return false;
        if (razlika_temperatura > 120)
            return false;
        if(procenat_kiseonika < 15 || procenat_kiseonika > 25)
            return false;
        if(zbir_gasova > 99 || zbir_gasova < 90)
            return false;
        if(max_visina_gravitacije > 1000)
            return false;
        if (brzina_orbitiranja < 25 || brzina_orbitiranja > 35)
            return false;
        return broj_umrlih <= 20;
    }

    @Override
    public String toString() {
        return "Planeta{" +
                "naziv='" + naziv + '\'' +
                '}';
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

    public int getSredanja_udaljenost() {
        return sredanja_udaljenost;
    }

    public void setSredanja_udaljenost(int sredanja_udaljenost) {
        this.sredanja_udaljenost = sredanja_udaljenost;
    }

    public int getNajniza_temperatura() {
        return najniza_temperatura;
    }

    public void setNajniza_temperatura(int najniza_temperatura) {
        this.najniza_temperatura = najniza_temperatura;
    }

    public int getNajvisa_temperatura() {
        return najvisa_temperatura;
    }

    public void setNajvisa_temperatura(int najvisa_temperatura) {
        this.najvisa_temperatura = najvisa_temperatura;
    }

    public int getRazlika_temperatura() {
        return razlika_temperatura;
    }

    public void setRazlika_temperatura(int razlika_temperatura) {
        this.razlika_temperatura = razlika_temperatura;
    }

    public int getProcenat_kiseonika() {
        return procenat_kiseonika;
    }

    public void setProcenat_kiseonika(int procenat_kiseonika) {
        this.procenat_kiseonika = procenat_kiseonika;
    }

    public int getProcenat_drugog_gasa() {
        return procenat_drugog_gasa;
    }

    public void setProcenat_drugog_gasa(int procenat_drugog_gasa) {
        this.procenat_drugog_gasa = procenat_drugog_gasa;
    }

    public String getNaziv_drugog_gasa() {
        return naziv_drugog_gasa;
    }

    public void setNaziv_drugog_gasa(String naziv_drugog_gasa) {
        this.naziv_drugog_gasa = naziv_drugog_gasa;
    }

    public int getZbir_gasova() {
        return zbir_gasova;
    }

    public void setZbir_gasova(int zbir_gasova) {
        this.zbir_gasova = zbir_gasova;
    }

    public int getMax_visina_gravitacije() {
        return max_visina_gravitacije;
    }

    public void setMax_visina_gravitacije(int max_visina_gravitacije) {
        this.max_visina_gravitacije = max_visina_gravitacije;
    }

    public int getBrzina_orbitiranja() {
        return brzina_orbitiranja;
    }

    public void setBrzina_orbitiranja(int brzina_orbitiranja) {
        this.brzina_orbitiranja = brzina_orbitiranja;
    }

    public int getBroj_umrlih() {
        return broj_umrlih;
    }

    public void setBroj_umrlih(int broj_umrlih) {
        this.broj_umrlih = broj_umrlih;
    }

    public boolean isNastanjiva() {
        return nastanjiva;
    }

    public void setNastanjiva(boolean nastanjiva) {
        this.nastanjiva = nastanjiva;
    }
}
