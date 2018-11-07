package controlevendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mylle
 */
public class ConnectionFactory {
    
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/estoque","root","mzll3n@!");
        }catch(ClassNotFoundException e){
           throw new SQLException(e.getMessage());
        }
    }
}
