package service;

import domain.DataObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by admin on 01.02.2017.
 */
public class SAXMyHandler extends DefaultHandler {

    private boolean insideNSIElem;
    private String curElem;
    private DataObject dataObject;

    public void setSdo(SaveDataObject sdo) {
        this.sdo = sdo;
    }

    private  SaveDataObject sdo;

    public boolean isAllOk() {
        return AllOk;
    }

    public void setAllOk(boolean allOk) {
        AllOk = allOk;
    }

    private boolean AllOk;


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        //System.out.println("Тег: "+qName);
        curElem = qName;
        if(qName.equals("nsiOrganization"))
        {
      //      System.out.println("нашли новую организацию,"+uri+","+localName);
        insideNSIElem=true;
        }
        if (insideNSIElem)
        {
            dataObject.append("<"+qName+">");
        }

        super.startElement(uri, localName, qName, attributes);


    }

    @Override
    public void characters(char[] c, int start, int length)
            throws SAXException {
        if (insideNSIElem) {
            String value = new String(c, start, length);
            dataObject.append(value);
            if (curElem.equals("oos:regNumber")) {dataObject.setRegNumber(value);}
     //       System.out.println("строка <" + value + "> внутри тэга - "+curElem);

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (insideNSIElem)
        {
            dataObject.append("</"+qName+">");
        }
        if(qName.equals("nsiOrganization"))
        {
       //     System.out.println("новая организация закончилась");
            insideNSIElem=false;
            //сохранить dataObject;
            sdo.SaveObject(dataObject);
            dataObject.Clear();
        }
      //  System.out.println("Тег разобран: "+qName);
        super.endElement(uri,localName, qName);
    }

    @Override
    public void startDocument() throws SAXException {
        setAllOk(false);
        insideNSIElem=false;
        curElem="";
        dataObject = new DataObject();

     //   System.out.println("Начало разбора документа!");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
       // System.out.println("Разбор документа окончен!");
            setAllOk(true);

    }

}
