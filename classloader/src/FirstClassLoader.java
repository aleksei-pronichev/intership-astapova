import java.io.*;
import java.util.Map;


public class FirstClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String dir = "C:" +File.separatorChar + "User" + File.separatorChar + "333" +
                File.separatorChar + "Desktop";
        String classPath = dir + File.separatorChar +
                name.replace('.', File.separatorChar) +
                ".class";

        File file = new File(classPath);
        byte[] buffer = null;
        InputStream in;
        try{
            in=new FileInputStream(file);
            buffer=new byte[in.available()];
            in.read(buffer);
            in.close();
            return defineClass(name,buffer,0,buffer.length);
        } catch (IOException e){
            e.printStackTrace();
        }
        return super.findClass(classPath);
    }

//    private File deCrypter(String name) {
//
//        return new File();
//    }

}
