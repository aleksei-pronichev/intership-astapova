import java.io.File;

public class Crypter {
    public static byte[] encode(File file, String key) {
        byte[] btxt;
        byte[] bkey;


        btxt = secret.getBytes();
        bkey = key.getBytes();

        byte[] result = new byte[secret.length()];

        for (int i = 0; i < btxt.length; i++) {
            result[i] = (byte) (btxt[i] ^ bkey[i % bkey.length]);
        }
        return result;
    }
}
