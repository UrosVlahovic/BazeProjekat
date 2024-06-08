package group.raf.zus_pravi.view.all;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.model.base.Polazak;
import group.raf.zus_pravi.repository.*;
import group.raf.zus_pravi.view.MainScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AllPolasci extends Scene {

    private VBox root;

    private Button back;

    private Korisnik korisnik;

    public AllPolasci(Korisnik korisnik) {
        super(new VBox(), 400, 400);
        root = (VBox) getRoot();
        this.korisnik = korisnik;
        initialize();
        actions();
    }


    private void initialize() {
        for (Polazak polazak : PolazakRepo.getInstance().listAllPolazak()){
            System.out.println("Current polazak: " + polazak.getId());
            Label l1 = new Label("Vozilo: " + polazak.getVozilo() + " (sediste " + polazak.getSediste() +")" +
                    " polazi " + polazak.getDatum() + " na " + KartaRepo.getInstance().getKartaById(polazak.getIdKarte()).getOdrediste());
            l1.setFont(Font.font("Serif", 22));
            root.getChildren().addAll(l1);
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
