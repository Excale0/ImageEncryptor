package imageProcessors;

import encryptionModel.EncodingModel;

import java.awt.image.BufferedImage;

/**
 * Created by Raymond Wang on 29/09/17.
 */
public class ImageDecoder {
    private EncodingModel _model;
    private BufferedImage _image;

    public ImageDecoder(EncodingModel model, BufferedImage image){
        _model = model;
        _image = image;
    }

    public String decode(){
        int x = _image.getWidth();
        int y = _image.getHeight();
        int height = 0;
        int width = 0;
        String output="";
        int value;
        while(height < x-1){
            value = RGB_IO.getIntFromRGB(_image,height,width);
            String character = _model.getCharacter(value);
            if (value == 0){
                break;
            }
            output = output+character;
            if (height == x-2){
                System.out.println("Hi");
            }
            if (width<y-1){
                width++;
            } else {
                height = height+1;
                width = 0;
            }
        }
        return output;
    }
}
