package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBhandler {
	
	public static Connection dbConnection = null;
	public static Statement dbStatement = null;
	
	private String url = "jdbc:postgresql://localhost:5433/INFDEV08";
	private String dbUser = "User1";
	private String dbPassword = "User1";

	public DBhandler() {
		
	}
	
	public Student login(String id, String password) {
		Student student = null;
		ResultSet result = null;
		try {
            dbConnection = DriverManager.getConnection(url, dbUser, dbPassword);
            dbStatement = dbConnection.createStatement();
            PreparedStatement ps = dbConnection.prepareStatement("SELECT * FROM student WHERE id = '" + id + "' AND wachtwoord = '" + password.toString() + "'");
            
            
            //The code beneath can be used to avoid SQL injections, unlike the way it is done above. 
            /*PreparedStatement ps = dbConnection.prepareStatement("SELECT * FROM student WHERE id =? AND wachtwoord =?");
            ps.setString(1, id);
            ps.setString(2, password);*/
            

            result = ps.executeQuery();
            if (result.next()) {
            	student = new Student (
            		result.getString("id"),
            		result.getString("naam"),
            		result.getString("wachtwoord"),
            		result.getString("klas"),
            		result.getString("ingeschreven")
            	);
            } else {
            	System.err.println("False ID/Password combination");
            }

            return student;
		} catch (Exception e) {
			System.err.println("False ID/Password combination");
		} 
		
        try {
            if (dbStatement != null) {
            	dbStatement.close();
            }
            if (dbConnection != null) {
            	dbConnection.close();
            }
        } catch (SQLException ex) {
        	 System.out.println(ex);
             ex.printStackTrace();
        }
        
		return student;
	}
	
	public ArrayList<Student> getStudentsByKlas(String klas) {
		ArrayList<Student> students = new ArrayList<Student>();
		ResultSet result = null;
		try {
            dbConnection = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement query = dbConnection.prepareStatement("SELECT * FROM student WHERE klas=? AND ingeschreven = 't'");
            query.setString(1, klas);
            result = query.executeQuery();
            while (result.next()) {
            	Student s = new Student(result.getString("id"), result.getString("naam"), "", result.getString("klas"), result.getString("ingeschreven"));
            	students.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("No class found!");
		}
        try {
            if (dbStatement != null) {
            	dbStatement.close();
            }
            if (dbConnection != null) {
            	dbConnection.close();
            }
        } catch (SQLException ex) {
        	 System.out.println(ex);
             ex.printStackTrace();
        }
        return students;
		
	}
}
