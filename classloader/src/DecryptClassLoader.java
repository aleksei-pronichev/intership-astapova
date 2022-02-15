import java.io.*;

/*
    Даже скомпилированные классы можно расшифровать.
    Допустим, мы не хотим, чтобы наш уникальный код кто-то скопировал
    и использовал в своих корыстных целях.
    Поэтому весь байт-код мы шифруем и сохраняем в зашифрованном виде - в файлах.
    Чтобы приложение работало, мы создаем CryptClassLoader, который загружает
    классы из файлов и расшифровывает их с помощью ключа.
 */

public class DecryptClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] cryptClassFromFile = loadClassFromFile(name);
        //вызываем декрипт-метод ---
        String key = name.concat(getKey()); // в качестве секретного ключа используем
        // имя + ключ, таким образом, каждый класс имеет свой уникальный ключ шифрования
        byte[] decryptClassFromFile = toDecryptBytesArray(cryptClassFromFile, key);
        return defineClass(name, decryptClassFromFile, 0, decryptClassFromFile.length);
    }

    // Метод для загрузки байт-кода из файла
    private byte[] loadClassFromFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }

    private byte[] toDecryptBytesArray(byte[] cryptClassFromFile, String key) {

        byte[] decryptClass = new byte[cryptClassFromFile.length];
        byte[] bkey = key.getBytes();

        for (int i = 0; i < cryptClassFromFile.length; i++) {
            decryptClass[i] = (byte) (cryptClassFromFile[i] ^ bkey[i % bkey.length]);
        }
        return decryptClass;
    }

    // наш ключ хранится в файловой системе сервера (пока - на нашем "родном" компьютере),
    // в простом текстовом файле, который состоит из одной строки
    private String getKey() {
        String key = null;
        try (BufferedReader reader = new BufferedReader(new FileReader
                ("/Desktop/1.txt"))) {
            key = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }
}
