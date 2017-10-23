package encryptionModel;

import java.util.HashMap;

/**
 * Created by Raymond Wang on 29/09/17.
 */
public class DecodingModel extends EncryptionModel {
    
    private static DecodingModel instance;

    @Override
    protected void putInKeymap(int number, char c) {
        decryptionModel.put(Character.toString(c),number);
    }

    @Override
    protected void putStopInMap(){
        decryptionModel.put("`",0);
    }

    public static DecodingModel getInstance(){
        if (instance == null){
            instance = new DecodingModel();
        }
        return instance;
    }

    private DecodingModel(){
        constructMap();
    }

    @Override
    public HashMap getMap() {
        return decryptionModel;
    }
}
