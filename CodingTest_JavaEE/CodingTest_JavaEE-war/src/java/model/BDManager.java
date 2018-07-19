/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BDManager {
    
    private static Connection conn;
    private static boolean initialized = false;
    
    private static Connection getConnection(String propertiesFileName) throws IOException, ClassNotFoundException, SQLException, NullPointerException {
        Properties props = new Properties();
        URL propertiesFileUrl = BDManager.class.getResource(propertiesFileName);
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(propertiesFileUrl.openStream());
            props.load(bis);
            String driver = "org.postgresql.Driver";
            String url = props.getProperty("url");
            String user = props.getProperty("username");
            String password = props.getProperty("password");
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);

        } finally {
            if (bis != null) {
                bis.close();
            }
        }
    }
    
    public static void initialize() throws IOException, ClassNotFoundException, SQLException{
        conn = getConnection("conn.properties");
        conn.setAutoCommit(true);
        initialized = true;
    }
    
    public static ResultSet executeSelectQuery(String query) throws SQLException, ClassNotFoundException, IOException{
        if(!initialized) initialize();
        
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
        ResultSet rset = stmt.executeQuery(query);
        
        return rset;
    }
    
    public static void executeUpdateQuery(String query) throws SQLException, ClassNotFoundException, IOException{
        if(!initialized) initialize();
        
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
        
    }
}
