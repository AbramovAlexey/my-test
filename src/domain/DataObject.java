package domain;

/**
 * Created by admin on 02.02.2017.
 */
public class DataObject {

    public DataObject() {
        Clear();
    }

    public void Clear(){
        this.regNumber = "";
        this.XMLBody = "";
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    private String regNumber;

    public String getXMLBody() {
        return XMLBody;
    }

    public void setXMLBody(String XMLBody) {
        this.XMLBody = XMLBody;
    }

    public void append(String postfix) {
        this.XMLBody += postfix;
    }

    private String XMLBody;
}
