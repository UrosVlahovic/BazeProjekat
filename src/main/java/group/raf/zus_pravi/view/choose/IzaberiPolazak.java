package group.raf.zus_pravi.view.choose;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.*;
import group.raf.zus_pravi.repository.*;
import group.raf.zus_pravi.view.MainScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class IzaberiPolazak extends Scene {

    private GridPane root;

    private Button back;
    private Button submit;
    private DatePicker datePicker;
    private ComboBox<Planeta> planeteCb;

    private Korisnik korisnik;
    private Karta karta;
    private Polazak polazak;

    public IzaberiPolazak(Korisnik korisnik) {
        super(new GridPane(), 400, 400);
        root = (GridPane) getRoot();
        this.korisnik = korisnik;
        initialize();
        actions();
    }


    private void initialize() {

        Label planetaLbl = new Label("Choose destination:");
        ObservableList<Planeta> planete = FXCollections.observableList(PlanetaRepo.getInstance().listAllPlanete());
        planeteCb = new ComboBox<>();
        planeteCb.setItems(planete);
        Label passLabel = new Label("Chose date:");
        datePicker = new DatePicker(LocalDate.now());

        root.add(planetaLbl, 0, 0);
        root.add(planeteCb, 1, 0);
        root.add(passLabel, 0, 1);
        root.add(datePicker, 1, 1);

        submit = new Button("Potvrda");
        back = new Button("Nazad");
        root.add(submit, 1, 2);
        root.add(back, 0, 2);
    }

    private void actions() {
        submit.setOnAction(e -> {
            Random rand = new Random();
            karta = new Karta(KartaRepo.getInstance().listAllKarte().size()+1, rand.nextInt()%1000, planeteCb.getSelectionModel().getSelectedItem().getNaziv());
            polazak = new Polazak(
                    PolazakRepo.getInstance().listAllPolazak().size()+1,
                    Date.valueOf(datePicker.getValue()),
                    korisnik.getId(),
                    karta.getId()
            );

            KartaRepo.getInstance().addKarta(karta);
            PolazakRepo.getInstance().addPolazak(polazak);
            Main.primaryStage.setScene(new MainScene(korisnik));
            Main.primaryStage.show();

        });
        back.setOnAction(e -> {
            Main.primaryStage.setScene(new MainScene(korisnik));
            Main.primaryStage.show();
        });
    }
}
