package group.raf.zus_pravi.view.all;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.model.base.Objekat;
import group.raf.zus_pravi.repository.ObjekatRepo;
import group.raf.zus_pravi.view.MainScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AllObjekti extends Scene {

    private VBox root;

    private Button back;

    private Korisnik korisnik;

    public AllObjekti(Korisnik korisnik) {
        super(new VBox(), 400, 400);
        root = (VBox) getRoot();
        this.korisnik = korisnik;
        initialize();
        actions();
    }


    private void initialize() {
        for (Objekat objekat : ObjekatRepo.getInstance().listAllObjekti()){
            System.out.println("Current korisnik: " + objekat.getIdObjekta());
            Label l1 = new Label("Objekat" + objekat.getIdObjekta() + ": ");
            l1.setFont(Font.font("Serif", 22));
            Label l2 = new Label("kvadratura: " + objekat.getKvadratura());
            Label l3 = new Label("cena: " + objekat.getCena());

            root.getChildren().addAll(l1, l2, l3);
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
