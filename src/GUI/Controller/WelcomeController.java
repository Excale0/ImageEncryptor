package GUI.Controller;

import GUI.Sceneloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class WelcomeController {

    private MediaPlayer player;

    @FXML
    private PasswordField passwordBox;

    @FXML
    private Button continueButton;

    @FXML
    private Label failureLabel;


    private static final String PASSWORD = "Genifer";

    @FXML
    void initialize(){
        failureLabel.setVisible(false);
        continueButton.setFocusTraversable(true);
        bindPasswordFieldToEnter();

    }

    public void proceedAction(ActionEvent e) throws IOException{
        String password = passwordBox.getText();
        if (password.equals(PASSWORD)){
            Sceneloader loader = Sceneloader.getInstance();
            String FXML = "GUI/Controller/MainScreen.fxml";
            loader.loadScene(FXML,(Node)e.getSource(),720,480 );
            File audioFile = new File("audio/ceroc.mp3");
            Media ha = new Media(audioFile.toURI().toString());
            player = new MediaPlayer(ha);
        } else {
            failureLabel.setVisible(true);
            File audioFile = new File("audio/ha.mp3");
            Media ceroc = new Media(audioFile.toURI().toString());
            player = new MediaPlayer(ceroc);
        }
        player.play();
     }

    void bindPasswordFieldToEnter(){
        passwordBox.addEventFilter(KeyEvent.KEY_PRESSED,event -> {
            if (event.getCode() == KeyCode.ENTER){
                continueButton.fire();
                event.consume();
            }
        });
    }

}
