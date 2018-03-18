package imageProcessors;

import encryptionModel.EncodingModel;

import java.awt.image.BufferedImage;

/**
 * This class is responsible for decoding an image.
 * Created by Raymond Wang on 29/09/17.
 */
public class ImageDecoder {

    private EncodingModel _model;
    private BufferedImage _image;

    public ImageDecoder(EncodingModel model, BufferedImage image){
        _model = model;
        _image = image;
    }

    public String decode() {
        int maxWidth = _image.getWidth()-1;
        int height = 0;
        int width = 0;
        String output="";
        int value;

        while(height < maxWidth){
            value = RGB_IO.getIntFromRGB(_image,width,height);
            String character = _model.getCharacter(value);

            if (value == 0){
                break;
            }

            output = output+character;

            if (width < maxWidth){
                width++;
            } else {
                height = height+1;
                width = 0;
            }
        }

        return output;
    }
}
