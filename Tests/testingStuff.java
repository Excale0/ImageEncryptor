import encryptionModel.DecodingModel;
import encryptionModel.EncodingModel;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.fail;

/**
 * Created by Raymond Wang on 28/09/17.
 */
public class testingStuff {
    @Test
    public void testLoadImage(){
        BufferedImage image;
        try {
            image = ImageIO.read(new File("1.png"));
            int rgb = image.getRGB(0,0);
            int red =   (rgb >> 16) & 0xFF;
            int green = (rgb >>  8) & 0xFF;
            int blue =  (rgb      ) & 0xFF;
            System.out.println(blue);
        } catch (IOException e){
            fail();
        }
    }

    @Test
    public void testLoad(){
        EncodingModel s = EncodingModel.getInstance();
        DecodingModel g = DecodingModel.getInstance();
        int counter = 0;
        for (Object i : g.getMap().keySet()){
            System.out.println(i);
            counter++;
        }
        System.out.println(counter);
    }
}
