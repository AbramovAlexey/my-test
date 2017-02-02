package service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

/**
 * Created by admin on 02.02.2017.
 */
public class SAXWrap {



    public static boolean ProcessXMLFile(String FileName) {
        SAXMyHandler saxMH;
        SaveDataObject sdo;
        saxMH = new SAXMyHandler();
        SAXParserFactory saxPF = SAXParserFactory.newInstance();
        saxPF.setValidating(true);
        saxPF.setNamespaceAware(false);
        SAXParser parser;
        File f;
        f = new File(FileName);
        if (!f.exists()) {
            return false;
        }
        else
        {

            InputStream xmlData;
            try {
                sdo = new SaveToDB();
                sdo.openConnect();
                saxMH.setSdo(sdo);
                 xmlData = new FileInputStream(FileName);

                parser = saxPF.newSAXParser();

                parser.parse(xmlData, saxMH);
                sdo.closeConnect();
                return saxMH.isAllOk();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
          return false;
        }
    }

}
