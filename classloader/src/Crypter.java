import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

//Это тестовый класс для простой шифровки. Используем симметричное шифрование.

public class Crypter {
    public static File encode(File file, byte key, File targetFile) {
        try (InputStream sourceStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
            byte[] sourceArray = new byte[sourceStream.available()];

            for (int i = 0; i < sourceArray.length; i++) {
                sourceArray[i] = (byte) (sourceArray[i] ^ key);
            }

            fileOutputStream.write(sourceArray, 0, sourceArray.length);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }
}