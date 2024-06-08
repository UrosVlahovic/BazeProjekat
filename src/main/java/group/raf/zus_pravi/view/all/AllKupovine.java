package group.raf.zus_pravi.view.all;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.model.base.Kupovina;
import group.raf.zus_pravi.repository.KorisnikRepo;
import group.raf.zus_pravi.repository.KupovinaRepo;
import group.raf.zus_pravi.repository.ObjekatRepo;
import group.raf.zus_pravi.view.MainScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AllKupovine extends Scene {

    private VBox root;

    private Button back;

    private Korisnik korisnik;

    public AllKupovine(Korisnik korisnik) {
        super(new VBox(), 400, 400);
        root = (VBox) getRoot();
        this.korisnik = korisnik;
        initialize();
        actions();
    }


    private void initialize() {
        for (Kupovina kupovina : KupovinaRepo.getInstance().listAllKupovina()){
            System.out.println("Current korisnik: " + kupovina.getId());
            Label l1 = new Label("Kupovina" + kupovina.getId() + ": ");
            l1.setFont(Font.font("Serif", 22));
            Label l2 = new Label("Korisnik: " +
                    KorisnikRepo.getInstance().getKorisnikById(kupovina.getIdKorisnika()).getIme());
            Label l3 = new Label("je dana: " + kupovina.getDatum());
            Label l4 = new Label("kupio objekat: " + ObjekatRepo.getInstance().getObjekatById(kupovina.getIdObjekta()));

            root.getChildren().addAll(l1, l2, l3, l4);
        }
        back = new Button("Nazad");
        root.getChildren().add(back);
    }

    private void actions() {
        back.setOnAction(e -> {
            Main.primaryStage.setScene(new MainScene(korisnik));
            Main.primaryStage.show();
        });
    }
}
