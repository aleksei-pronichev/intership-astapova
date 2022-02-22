import java.io.*;


public class DecryptClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte key = 5;
        byte[] buffer = getArrayBytes(name, key);
        return defineClass(name, buffer, 0, buffer.length);
    }

    // тут мы принимаем файл, прогоняем через расшифровщик, отдаем массив байт
    private byte[] getArrayBytes(String name, byte key) {
        File file = new File(getClassPath(name));
        return toDecrypt(file, key);
    }

    // расшифровщик
    private byte[] toDecrypt(File file, byte key) {
        byte[] sourceArray = null;
        try (InputStream sourceStream = new FileInputStream(file)) {
            sourceArray = new byte[sourceStream.available()];

            for (int i = 0; i < sourceArray.length; i++) {
                sourceArray[i] = (byte) (sourceArray[i] ^ key);
            }
            return sourceArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceArray;
    }

    // отсюда мы берем classPath
    private String getClassPath(String name) {

        return "C:\\Users\\333\\Desktop\\" + name + ".class";

    }

}
