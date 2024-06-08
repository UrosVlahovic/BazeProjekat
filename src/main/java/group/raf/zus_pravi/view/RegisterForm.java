package group.raf.zus_pravi.view;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.repository.KorisnikRepo;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegisterForm extends Scene {

    private GridPane root;
    private Button registerBtn;

    private TextField name;
    private TextField surname;
    private TextField age;
    private TextField userField;
    private PasswordField passField;
    private PasswordField confirmPassField;

    public RegisterForm() {
        super(new GridPane(), 300, 250);
        root = (GridPane) getRoot();
        initialize();
        registerAction();
    }

    private void initialize() {
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label nameLbl = new Label("Name: ");
        name = new TextField();
        Label surnameLbl = new Label("Last Name: ");
        surname = new TextField();
        Label ageLbl= new Label("Age: ");
        age = new TextField();

        root.add(nameLbl, 0, 0);
        root.add(name, 1, 0);
        root.add(surnameLbl, 0, 1);
        root.add(surname, 1, 1);
        root.add(ageLbl, 0, 2);
        root.add(age, 1, 2);

        Label userLabel = new Label("Username:");
        userField = new TextField();
        Label passLabel = new Label("Password:");
        passField = new PasswordField();
        Label confirmPassLabel = new Label("Confirm Password:");
        confirmPassField = new PasswordField();
        registerBtn = new Button("Register");

        root.add(userLabel, 0, 3);
        root.add(userField, 1, 3);
        root.add(passLabel, 0, 4);
        root.add(passField, 1, 4);
        root.add(confirmPassLabel, 0, 5);
        root.add(confirmPassField, 1, 5);
        root.add(registerBtn, 0, 6);

    }

    public void registerAction(){
        registerBtn.setOnAction(e -> {
            Korisnik korisnik = new Korisnik(KorisnikRepo.getInstance().listAllKorisnici().size(), userField.getText(), passField.getText(), name.getText(), surname.getText(), Integer.parseInt(age.getText()));
            KorisnikRepo.getInstance().addKorisnik(korisnik);
            System.out.println("Korisnik: " + korisnik);
            Main.primaryStage.setScene(new MainScene(korisnik));
            Main.primaryStage.show();
        });
    }
}

