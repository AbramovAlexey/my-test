package service;

import domain.ServResponse;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by admin on 01.02.2017.
 */
public interface Service {
    ServResponse downloadFTP(String from, String to, String login, String pass, String directory) throws MalformedURLException, IOException;
    ServResponse unzipFiles(String sourcetFolder);
    ServResponse XMLParseAndSave(String sourceFolder);

}
