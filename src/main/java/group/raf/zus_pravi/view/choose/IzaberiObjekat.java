package group.raf.zus_pravi.view.choose;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.model.base.Kupovina;
import group.raf.zus_pravi.model.base.Objekat;
import group.raf.zus_pravi.repository.KorisnikRepo;
import group.raf.zus_pravi.repository.KupovinaRepo;
import group.raf.zus_pravi.repository.ObjekatRepo;
import group.raf.zus_pravi.view.MainScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.sql.Date;
import java.time.LocalDate;

public class IzaberiObjekat extends Scene {

    private VBox root;

    private Button back;

    private Korisnik korisnik;

    public IzaberiObjekat(Korisnik korisnik) {
        super(new VBox(), 400, 400);
        root = (VBox) getRoot();
        this.korisnik = korisnik;
        initialize();
        actions();
    }


    private void initialize() {
        for (Objekat objekat : ObjekatRepo.getInstance().listAllObjekti()){
            Label l1 = new Label("Objekat" + objekat.getIdObjekta() + ": ");
            l1.setFont(Font.font("Serif", 22));
            Label l2 = new Label("kvadratura: " + objekat.getKvadratura());
            Label l3 = new Label("cena: " + objekat.getCena());
            Button btn = new Button("Izaberi");

            btn.setOnAction(e -> {
                Kupovina kupovina = new Kupovina(
                        KupovinaRepo.getInstance().listAllKupovina().size()+1,
                        Date.valueOf(LocalDate.now()),
                        korisnik.getId(),
                        objekat.getIdObjekta()
                );
                KupovinaRepo.getInstance().addKupovina(kupovina);
                Main.primaryStage.setScene(new MainScene(korisnik));
                Main.primaryStage.show();
            });

            root.getChildren().addAll(l1, l2, l3, btn);
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
