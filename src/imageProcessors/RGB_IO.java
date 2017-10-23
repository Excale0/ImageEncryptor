package imageProcessors;

import encryptionModel.EncryptionModel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Raymond Wang on 29/09/17.
 */
public class RGB_IO {

    /**
     * Returns the values of the RGB pixel modulo 4 for each pixel in a string format.
     * @param pic
     * @param x
     * @param y
     * @return
     */
    public static int getIntFromRGB(BufferedImage pic, int x, int y){
        int rgb = pic.getRGB(x,y);
        int red =   (rgb >> 16) & 0xFF;
        int green = (rgb >>  8) & 0xFF;
        int blue =  (rgb      ) & 0xFF;

        int hundreds = red%4;
        int tens = green%4;
        int ones = blue%4;

        String numberString = "" +hundreds+tens+ones;
        Integer number = Integer.parseInt(numberString);
        return number;
    }

    public static void setRGBFromInt(BufferedImage pic, int value, int x, int y){

        int rgb = pic.getRGB(x,y);
        int red =   (rgb >> 16) & 0xFF;
        int green = (rgb >>  8) & 0xFF;
        int blue =  (rgb      ) & 0xFF;

        int[] colorArray = {red,green,blue};

        int[] valueArray = {EncryptionModel.getHundreds(value), EncryptionModel.getTens(value),EncryptionModel.getOnes(value)};

        int[] currentValueArray = {red%4, green%4, blue%4};

        for (int i = 0; i<3; i++){
            if (valueArray[i]!=currentValueArray[i]){
                int difference = valueArray[i]-currentValueArray[i];
                colorArray[i] = alterRGB(difference,colorArray[i]);
            }
        }

        Color color = new Color(colorArray[0],colorArray[1],colorArray[2]);
        pic.setRGB(x,y,color.getRGB());
    }

    public static int alterRGB(int difference, int colorIndex){
        if (difference<0){

            if (colorIndex + difference <0){
                difference = 4+difference;
            }
            colorIndex = colorIndex+difference;

        } else {

            if (colorIndex + difference <= 255) {
                colorIndex = colorIndex + difference;
            } else {
                difference = 4 - difference;
                colorIndex = colorIndex - difference;
            }
        }
        return colorIndex;

    }
}
