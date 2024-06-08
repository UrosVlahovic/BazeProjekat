package group.raf.zus_pravi.view;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.view.all.*;
import group.raf.zus_pravi.view.choose.IzaberiObjekat;
import group.raf.zus_pravi.view.choose.IzaberiPolazak;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainScene extends Scene {

      private GridPane root;
      private Korisnik korisnik;

      private Button odaberiStan;
      private Button datum;
      private Button allPlanete;
      private Button allKorisnici;
      private Button allObjekti;
      private Button allKupovine;
      private Button allPolasci;


      public MainScene(Korisnik korisnik) {
            super(new GridPane(), 400, 400);
            this.korisnik = korisnik;
            root = (GridPane) getRoot();
            initialize();
            actions();
      }

      private void initialize() {
            root.setPadding(new Insets(10));
            root.setHgap(10);
            root.setVgap(10);

            Label userLbl = new Label("Zdravo " + korisnik.getUsername());
            root.add(userLbl, 0, 0);

            allPlanete = new Button("Pregled planeta");
            allKorisnici = new Button("Pregled korisnika");
            allObjekti = new Button("Pregled objekata");
            allKupovine = new Button("Pregled kupovina");
            allPolasci = new Button("Pregled polazaka");

            root.add(allPlanete, 0, 1);
            root.add(allKorisnici, 1, 1);
            root.add(allObjekti, 0, 2);
            root.add(allKupovine, 1, 2);
            root.add(allPolasci, 0, 3);

            odaberiStan = new Button("Odaberi stambeni objekat");
            datum = new Button("Odaberi datum i vreme polaska");

            root.add(odaberiStan, 1, 3);
            root.add(datum, 0, 5);
      }

      private void actions(){
            allPlanete.setOnAction(e -> {
                  Main.primaryStage.setScene(new AllPlaneteScene(korisnik));
                  Main.primaryStage.show();
            });
            allKorisnici.setOnAction(e -> {
                  Main.primaryStage.setScene(new AllKorisnici(korisnik));
                  Main.primaryStage.show();
            });
            allObjekti.setOnAction(e -> {
                  Main.primaryStage.setScene(new AllObjekti(korisnik));
                  Main.primaryStage.show();
            });
            allKupovine.setOnAction(e -> {
                  Main.primaryStage.setScene(new AllKupovine(korisnik));
                  Main.primaryStage.show();
            });
            allPolasci.setOnAction(e -> {
                  Main.primaryStage.setScene(new AllPolasci(korisnik));
                  Main.primaryStage.show();
            });
            datum.setOnAction(e -> {
                  Main.primaryStage.setScene(new IzaberiPolazak(korisnik));
                  Main.primaryStage.show();
            });
            odaberiStan.setOnAction(e -> {
                  Main.primaryStage.setScene(new IzaberiObjekat(korisnik));
                  Main.primaryStage.show();
            });
      }

}
