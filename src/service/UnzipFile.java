package service;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by admin on 01.02.2017.
 */
public class UnzipFile {

    private static final int BUFFER_SIZE = 1024;

    private static void StreamWriteToStream(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        int length;
        while ((length = in.read(buf)) >= 0)
            out.write(buf, 0, length);
        out.close();
        in.close();
    }

    public static boolean UnzipFileFunc(String pathNameF){
        File file = new File(pathNameF);
        if (!file.exists() || !file.canRead()) {
            System.out.println("Ошибка чтения файла "+pathNameF);
            return false;
        }

        try {
            ZipFile zip = new ZipFile(pathNameF);
            Enumeration entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println(entry.getName());

                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    StreamWriteToStream(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))
                                                    )
                    );
                }
            }

            zip.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
