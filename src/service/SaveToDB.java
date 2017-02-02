package service;

import domain.DataObject;

import java.sql.*;

/**
 * Created by admin on 02.02.2017.
 */
public class SaveToDB implements SaveDataObject {

    public boolean isConnected() {
        return connected;
    }

    private boolean connected;
    private static final String url = "jdbc:mysql://localhost:3306/testdb";
    private static final String user = "root";
    private static final String password = "root";

    private Connection con;

    @Override
   public void openConnect() {
        connected=false;

        try {

            con =    con = DriverManager.getConnection(url, user, password);
        connected = true;


        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void closeConnect() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connected=false;
    }

    @Override
    public boolean SaveObject(DataObject ddo) {
       boolean r;
       r = false;
       if (connected) {
           PreparedStatement pstmt = null;
           try {
               Clob myClob = this.con.createClob();
               myClob.setString(1, ddo.getXMLBody());
               String Query = "INSERT INTO TABLE_TEST VALUES(?,?)";
               pstmt = this.con.prepareStatement(Query);
               pstmt.setString(1, ddo.getRegNumber());
               pstmt.setClob(2, myClob);
               pstmt.executeUpdate();
               pstmt.close();
               r=true;
           } catch (SQLException sqlex) {
               System.out.println(sqlex.getMessage());
           } catch (Exception ex) {
               System.out.println("неожиданная ошибка: " + ex.toString());
           } finally {
               if (pstmt != null) try {
                   pstmt.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
        return r;
    }
}
