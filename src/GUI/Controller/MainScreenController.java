package GUI.Controller;

import encryptionModel.DecodingModel;
import encryptionModel.EncodingModel;
import imageProcessors.ImageDecoder;
import imageProcessors.ImageEncoder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Raymond Wang on 28/09/17.
 */
public class MainScreenController {

    private EncodingModel encodingModel = EncodingModel.getInstance();

    private DecodingModel decodingModel = DecodingModel.getInstance();

    private ImageDecoder decoder;

    private ImageEncoder encoder;

    private BufferedImage image;

    private BufferedImage outputImage;

    private String fileName;

    @FXML
    private Button encodeButton, decodeButton;

    @FXML
    private ImageView pictureView;

    @FXML
    private TextArea textArea;

    @FXML
    private Label fileLabel;

    @FXML
    public void encodePicture() throws IOException{
        encoder = new ImageEncoder(decodingModel,outputImage);
        String text= textArea.getText().trim() + "`";
        try {
            encoder.encode(text, fileName);
        } catch (Exception e) {
            System.out.println("Invalid character.");
        }
    }

    @FXML
    public void decodePicture(){
        decoder = new ImageDecoder(encodingModel,image);
        String s = decoder.decode();
        textArea.setText(s);
    }

    @FXML
    public void setImage(ActionEvent e) {

        clear();
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog((Stage)((Node)e.getSource()).getScene().getWindow());

        try {
            fileName = file.getName().replaceFirst("[.][^.]+$", "");
            fileLabel.setText(fileName);
            String url = file.toURI().toURL().toString();
            image = ImageIO.read(file);
            outputImage = ImageIO.read(file);
            encodeButton.setDisable(false);
            decodeButton.setDisable(false);
            pictureView.setImage(new Image(url));

        } catch (Exception E){

        }

    }

    @FXML
    public void initialize() {
        encodeButton.setDisable(true);
        decodeButton.setDisable(true);
        textArea.setWrapText(true);
    }

    @FXML
    public void clear(){
        textArea.setText(null);
    }


}
