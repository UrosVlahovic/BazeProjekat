package group.raf.zus_pravi.view.all;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.model.base.Misija;
import group.raf.zus_pravi.model.base.Planeta;
import group.raf.zus_pravi.repository.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AllMisije extends Scene {

    private VBox root;

    private Button back;

    private Planeta planeta;

    private Korisnik korisnik;

    public AllMisije(Korisnik korisnik, Planeta planeta) {
        super(new VBox(), 400, 400);
        root = (VBox) getRoot();
        this.planeta = planeta;
        this.korisnik = korisnik;
        initialize();
        actions();
    }


    private void initialize() {
        Label planetaLl = new Label("Misije na planeti: " + planeta.getNaziv());
        root.getChildren().add(planetaLl);
        for (Misija misija : MisijaRepo.getInstance().listAllMisije()){
            System.out.println("Current misija: " + misija.getId());
            if(misija.getIdPlanete() == planeta.getId()){
                Label l1 = new Label("Naziv misije " + misija.getNaziv());
                l1.setFont(Font.font("Serif", 22));
                root.getChildren().addAll(l1);
            }

        }
        back = new Button("Nazad");
        root.getChildren().add(back);
    }

    private void actions() {
        back.setOnAction(e -> {
            Main.primaryStage.setScene(new AllPlaneteScene(korisnik));
            Main.primaryStage.show();
        });
    }
}
