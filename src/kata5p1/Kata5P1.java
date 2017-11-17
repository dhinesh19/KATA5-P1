/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Kata5P1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        // TODO code application logic here
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:KATA5.db");
        
        Statement st = con.createStatement();
                
        String query = "SELECT * FROM PEOPLE";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
        
        query = "CREATE TABLE IF NOT EXISTS MAIL ('Id' INTEGER PRIMARY KEY AUTOINCREMENT , 'Mail' TEXT NOT NULL);";
        st.execute(query);
      
        FileReader fl = null;
        BufferedReader bf = null;
        String fileName = "/Users/DaniMangtani/NetBeansProjects/Kata5P1/emails.txt";
        
        try{
            fl = new FileReader(fileName);
            bf = new BufferedReader(fl);
            String mail;
            while((mail = bf.readLine()) != null){
                if(!mail.contains("@")){
                    continue;
                }
                query =	"INSERT	INTO	MAIL	(Mail)	VALUES	('"+ mail +"');”";	
                st.execute(query);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        
        query = "SELECT COUNT(*) FROM MAIL";
        rs = st.executeQuery(query);
        System.out.println("Número de registros de la tabla MAIL: " + rs.getInt(1));
        
    }
    
}
