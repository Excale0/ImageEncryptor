package encryptionModel;

import java.util.HashMap;

/**
 * Created by Raymond Wang on 28/09/17.
 */
public class EncodingModel extends EncryptionModel {

    private static EncodingModel instance;

    @Override
    protected void putStopInMap(){
        encryptionModel.put(0,"`");
    }

    @Override
    protected void putInKeymap(int number, char c) {
        encryptionModel.put(number,Character.toString(c));
    }


    public static EncodingModel getInstance(){
        if (instance == null){
            instance = new EncodingModel();
        }
        return instance;
    }

    private EncodingModel(){
        constructMap();
    }

    @Override
    public HashMap getMap() {
        return encryptionModel;
    }
}
