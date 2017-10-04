package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Raymond Wang on 28/09/17.
 */
public class Sceneloader {
    private static Sceneloader instance;

    public static Sceneloader getInstance(){
        if (instance == null){
            instance = new Sceneloader();
        }
        return instance;
    }

    /**
     * This method is called by the controller of the current scene to switch to another scene
     * @param nameOfFXML The location of the FXML file for the new scene
     * @param node This is the current node
     * @param width The specified width of the new scene
     * @param height The specified height of the new scene
     * @throws IOException
     */
    public void loadScene(String nameOfFXML, Node node, int width, int height) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(nameOfFXML));
        Scene scene = new Scene(parent,width,height);
        Stage window = (Stage)node.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}

