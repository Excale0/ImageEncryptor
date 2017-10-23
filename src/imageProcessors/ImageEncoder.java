package imageProcessors;

import encryptionModel.DecodingModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for encoding a message in an image.
 *
 * Created by Raymond Wang on 29/09/17.
 */
public class ImageEncoder {

    private DecodingModel _model;
    private BufferedImage _image;

    public ImageEncoder(DecodingModel model, BufferedImage image){
        _model=model;
        _image=image;
    }

    public void encode(String s,String output) throws IOException{

        char[] characters = s.toCharArray();
        int value;
        int x = 0;
        int y = 0;

        for (char c: characters){
            String cString = Character.toString(c);
            value = _model.getRGBNumber(cString);
            RGB_IO.setRGBFromInt(_image,value,x,y);

            if (y<_image.getWidth()-1){
                y++;
            } else {
                x++;
            }

            if (x > _image.getHeight()-1){
                System.out.println("Not enough space in image.");
                break;
            }
        }
        outputPicture(output);

    }

    public void outputPicture(String fileName) throws IOException{
        fileName = "./"+fileName+"_encoded.png";
        File outputImage = new File(fileName);
        ImageIO.write(_image,"png",outputImage);
    }
}
