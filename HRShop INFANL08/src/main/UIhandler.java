package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UIhandler {
	
	private Scanner scanner = new Scanner(System.in);
	private DBhandler dbHandler = new DBhandler();
	private Student loggedInStudent;


	public UIhandler() {

	}
	
	private boolean verifyUser() {
		scanner.useDelimiter("	");
		System.out.println("Enter your student ID:Password");
        String input = scanner.next();
        String userID = input.split(":")[0];
        String password = input.split(":")[1];
        loggedInStudent = dbHandler.login(userID, password);
        
        if (loggedInStudent != null) return true;
        return false;
        
	}
		
	public void startProgram() {
		 System.out.println("1. Log in");
         System.out.println("2. View class");
         String decision = scanner.next();
         if (decision.matches("1")) {
         	if (verifyUser()) {
             	System.out.println("ID:			Naam:			Wachtwoord:			Klas:			Ingeschreven:");
            	System.out.println(loggedInStudent.getId() + "			" + loggedInStudent.getName() + "			" + loggedInStudent.getPassword() + "			" + loggedInStudent.getKlas() + "			" + loggedInStudent.getIngeschreven());
         	} 
         } else if (decision.matches("2")) {
           	System.out.println("Enter a class to view:");
         	String klas = scanner.next();
         	String query = "SELECT id, naam, klas, ingeschreven FROM student WHERE klas ='" + klas + "' AND ingeschreven = 't'";
         	ArrayList<Student> result = dbHandler.getStudents(query);
         	System.out.println("ID:			Naam:			Klas:			Ingeschreven:");
            for (Student s : result) {
            	System.out.println(s.getId() + "			" + s.getName() + "			" + s.getKlas() + "			" + s.getIngeschreven());
            }
         }
	}
}
