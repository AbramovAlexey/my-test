package controller;

import domain.ServResponse;
import service.Service;
import service.ServiceImpl;

import java.io.IOException;

/**
 * Created by admin on 01.02.2017.
 */
public class Controller {
private final String FTPpart1 = "ftp.zakupki.gov.ru";
private final String FTPpart2 = "fcs_nsi/nsiOrganization";
private String WorkDir = "C:\\ftptest";
private final String login = "free";
private final String pass = "free";

private Service myservice;



    public Controller() {
        this.myservice = new ServiceImpl();

    }
    public void setWorkDir(String workDir) {
        WorkDir = workDir;
    }

    public ServResponse downloadFTP1() throws IOException {

        return myservice.downloadFTP(FTPpart1, WorkDir,login,pass, FTPpart2);
            }


        public ServResponse unzipFiles(){
            return   myservice.unzipFiles(WorkDir);

        }


        public ServResponse XMLParseAndSave(){
            return   myservice.XMLParseAndSave(WorkDir);

        }



}
