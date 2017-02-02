package service;

import domain.DataObject;

/**
 * Created by admin on 02.02.2017.
 */
public interface SaveDataObject {
    public void openConnect();
    public boolean SaveObject(DataObject ddo);
    public void closeConnect();

}
