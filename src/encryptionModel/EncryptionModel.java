package encryptionModel;

import java.util.HashMap;

/**
 * Created by Raymond Wang on 29/09/17.
 */
public abstract class EncryptionModel {
    protected HashMap<Integer, String> encryptionModel = new HashMap<>();
    protected HashMap<String, Integer> decryptionModel = new HashMap<>();

    protected void constructMap() {
        int number = 1;
        for (char character = 'a'; character <= 'z'; character++) {
            number = incrementAndPutNumber(number, character);
        }
        for (char character = 'A'; character <= 'Z'; character++) {
            if (character != 'Q') {
                number = incrementAndPutNumber(number, character);
            }
        }
        for (char character = '0'; character <= '9'; character++) {
            number = incrementAndPutNumber(number, character);
        }
        number = incrementAndPutNumber(number, ' ');
        number = incrementAndPutNumber(number, '.');
        putStopInMap();

    }

    public static int getTens(int number) {
        return (number / 10) % 10;
    }

    public static int getOnes(int number) {
        return number % 10;
    }

    public static int getHundreds(int number) {
        return (number / 100);
    }

    /**
     * Resolves overflow for quaternary counting
     * @param number the number
     * @return a quaternary number.
     */
    public static int resolveOverflow(int number) {
        int ones = getOnes(number);
        int tens = getTens(number);
        int hundreds = getHundreds(number);

        boolean overflow = ones > 3 || tens > 3 || hundreds > 3;
        if (overflow) {
            if (ones > 3) {
                tens = tens + 1;
                ones = 0;
            } else if (tens > 3) {
                hundreds = hundreds + 1;
                tens = 0;
            } else {
                hundreds = 0;
                ones = ones + 1;
            }

            String numberString = "" + hundreds + tens + ones;
            int result = Integer.parseInt(numberString);
            result = resolveOverflow(result);
            return result;
        }
        return number;
    }

    abstract protected void putInKeymap(int number, char c);

    abstract protected void putStopInMap();

    abstract public HashMap getMap();

    public String getCharacter(int key){
        return encryptionModel.get(key);
    }

    public int getRGBNumber(String c) throws Exception{
        try {
            return decryptionModel.get(c);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public int incrementAndPutNumber(int number, char c) {
        number = resolveOverflow(number);
        putInKeymap(number, c);
        number = number + 1;
        return number;
    }
}
