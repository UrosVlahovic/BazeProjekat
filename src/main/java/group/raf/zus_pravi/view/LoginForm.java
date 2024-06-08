package group.raf.zus_pravi.view;

import group.raf.zus_pravi.Main;
import group.raf.zus_pravi.model.base.Korisnik;
import group.raf.zus_pravi.repository.KorisnikRepo;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class LoginForm extends Scene {

    private GridPane root;

    private Button loginBtn;

    private Button registerBtn;

    private TextField userField;

    private PasswordField passField;

    private Korisnik korisnik;

    public LoginForm() {
        super(new GridPane(), 300, 200); // Initialize Scene with GridPane
        root = (GridPane) getRoot(); // Get the root from Scene
        initialize(); // Method to set up the layout and form elements
        actions();
    }

    private void initialize() {
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Label userLabel = new Label("Username:");
        userField = new TextField();
        Label passLabel = new Label("Password:");
        passField = new PasswordField();
        loginBtn = new Button("Login");
        registerBtn = new Button("Register");

        root.add(userLabel, 0, 0);
        root.add(userField, 1, 0);
        root.add(passLabel, 0, 1);
        root.add(passField, 1, 1);
        root.add(loginBtn, 1, 2);
        root.add(registerBtn, 0, 2);

    }

    private void actions(){
        loginBtn.setOnAction(e -> {
            if(validUser()){
                Main.primaryStage.setScene(new MainScene(korisnik));
                Main.primaryStage.show();
            } else {
                DialogPane pane = new DialogPane();
                pane.setContentText("Username or Password is incorrect!");
            }

        });
        registerBtn.setOnAction(e -> {
            Main.primaryStage.setScene(new RegisterForm());
            Main.primaryStage.show();
        });
    }

    private boolean validUser(){
        if(userField.getText().isEmpty() || passField.getText().isEmpty()){
            return false;
        }
        korisnik = KorisnikRepo.getInstance().getKorisnikByNameAndPass(
                userField.getText(), passField.getText());

        return korisnik != null;
    }
}

