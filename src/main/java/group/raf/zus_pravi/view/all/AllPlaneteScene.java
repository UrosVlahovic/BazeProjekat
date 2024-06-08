package group.raf.zus_pravi.view.all;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.model.base.Planeta;
import group.raf.zus_pravi.repository.PlanetaRepo;
import group.raf.zus_pravi.view.MainScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AllPlaneteScene extends Scene {

    private VBox root;

    private Button back;

    private Korisnik korisnik;

    public AllPlaneteScene(Korisnik korisnik) {
        super(new VBox(), 400, 400);
        root = (VBox) getRoot();
        this.korisnik = korisnik;
        initialize();
        actions();
    }


    private void initialize() {
        for (Planeta planeta : PlanetaRepo.getInstance().listAllPlanete()){
            System.out.println("Current planeta: " + planeta.getNaziv());
            Label l1 = new Label("naziv: " + planeta.getNaziv() + " je nastanjiva: " + planeta.jeNastanjiva());
            l1.setFont(Font.font("Serif", 22));
            Label l2 = new Label("srednja udaljenost: " + planeta.getSredanja_udaljenost());
            Label l3 = new Label("najniza temperatura: " + planeta.getNajniza_temperatura());
            Label l4 = new Label("najvisa temperatura: " + planeta.getNajvisa_temperatura());
            Label l5 = new Label("razlika temperatura: " + planeta.getRazlika_temperatura());
            Label l6 = new Label("procenat kiseonika: " + planeta.getProcenat_kiseonika());
            Label l7 = new Label("procenat drugog gasa: " + planeta.getProcenat_drugog_gasa());
            Label l8 = new Label("naziv drugog gasa: " + planeta.getNaziv_drugog_gasa());
            Label l9 = new Label("zbir gasova: " + planeta.getZbir_gasova());
            Label l10 = new Label("max visina gravitacije: " + planeta.getMax_visina_gravitacije());
            Label l11 = new Label("brzina orbitiranja: " + planeta.getBrzina_orbitiranja());
            Label l12 = new Label("broj umrlih: " + planeta.getBroj_umrlih());

            Button button = new Button("Misije za: " + planeta.getNaziv());
            button.setOnAction(e -> {
                Main.primaryStage.setScene(new AllMisije(korisnik, planeta));
                Main.primaryStage.show();
            });
            root.getChildren().addAll(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, button);

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
