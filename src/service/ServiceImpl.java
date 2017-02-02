package service;

import domain.ServResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Created by admin on 01.02.2017.
 */

public class ServiceImpl implements Service {

    @Override
    public ServResponse downloadFTP(String from, String to, String login, String pass, String directory) {
        ServResponse result;
        result = new ServResponse();


        try {
        FTPClient ftp = new FTPClient();

        ftp.connect(from);

        if(!ftp.login(login, pass))
        {
            ftp.logout();
            result.setSuccess(false);
            result.setComment("ошибка авторизации на ftp");
        }
        int reply = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply))
        {
            ftp.disconnect();
            result.setSuccess(false);
            result.setComment("ошибка авторизации на ftp");
        }

            File Dir = new File(to);
            if (!Dir.exists()) {
                result.setSuccess(false);
                result.setComment("целевой директории не существует");
            }

            if (result.getSuccess()){

        ftp.enterLocalPassiveMode();
        ftp.changeWorkingDirectory(directory);

        FTPFile[] ftpFiles = ftp.listFiles();

        if (ftpFiles != null && ftpFiles.length > 0) {

            for (FTPFile file : ftpFiles) {
                if (!file.isFile()) {
                    continue;
                }
                System.out.println("Скачиваю файл " + file.getName());

                OutputStream output;
                output = new FileOutputStream( to+ "/" + file.getName());

                ftp.retrieveFile(file.getName(), output);
                output.close();



            }
        }

        ftp.logout();
        ftp.disconnect();
        result.setSuccess(true);
        result.setComment("Файлы успешно загружены..");
            }
    }
        catch (Exception ex)
    {
        ex.printStackTrace();
        return result;
    }


        return result;
    }





    @Override
    public ServResponse unzipFiles(String sourceFolder) {
        int listSize;
        int countS;
        ServResponse result;
        result = new ServResponse();

        File Dir = new File(sourceFolder);
        if (!Dir.exists()) {
            result.setSuccess(false);
            result.setComment("исходной директории не существует");
        }
        else {
            File[] listF;
            countS = 0;
            for (File s : listF = Dir.listFiles()) {
                if (s.isFile())
                {
                 //   System.out.println(s);
                    if (UnzipFile.UnzipFileFunc(sourceFolder+"\\"+s.getName())) {
                        countS+=1;
                    }
                }
            }
            listSize = listF.length;
            if (countS > 0) {
                result.setSuccess(true);
                result.setComment("Успешно распакованы файлы, "+countS+" из "+listSize);
            }
            else
            {
                result.setSuccess(false);
                result.setComment("Распаковать файлы не удалось...");
            }


        }


        return result;
    }

    @Override
    public ServResponse XMLParseAndSave(String sourceFolder) {
        int listSize;
        int countS;
        ServResponse result;
        result = new ServResponse();


        File Dir = new File(sourceFolder);
        if (!Dir.exists()) {
            result.setSuccess(false);
            result.setComment("исходной директории c xml-файлами не существует");
        }
        else {
            File[] listF;
            countS=0;
            for (File s : listF = Dir.listFiles()) {
                if (s.isFile())
                {
                       System.out.println("обрабатывается файл "+s.getName());
                    if (SAXWrap.ProcessXMLFile(sourceFolder+"\\"+s.getName()))
                    {countS+=1;}
                }
            }
            listSize = listF.length;
            if (countS > 0) {
                result.setSuccess(true);
                result.setComment("Успешно разобрано xml файлов "+countS+" из "+listSize);
            }
            else
            {
                result.setSuccess(false);
                result.setComment("Не удалось разобрать ни одного файла xml");
            }

        }

        return result;
    }
}
