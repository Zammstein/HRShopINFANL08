package main;

import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;
import com.mysql.jdbc.*;

public class Main {

	public static void main(String[] args) {
		
		SimpleMySQL DBconnectionOne = new SimpleMySQL();
		SimpleMySQL DBconnectionTwo = new SimpleMySQL();
		try {
			DBconnectionOne.connect("localhost:3306", "User1", "User1", "infanl08");
			DBconnectionTwo.connect("localhost:3306", "User2", "User2", "infanl08");
		} catch (Exception e) {
			System.out.println("Unable to connect to db");
		}
			// Maak en start thread 1
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					// Schrijf hier je eigen code
					// Random wachttijd
					try {
						// Genereer een getal tussen de 0 t/m 10.
						int wachtTijd = (int) (Math.random() * 11);
						System.out.println(Thread.currentThread().getName() + ": Slaap " + wachtTijd + " sec");
						// Slaap wachtTijd seconden
						Thread.sleep(wachtTijd * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		 }, "Thread 1").start();
		
		 // Maak en start thread 2. Deze draait tegelijkertijd met thread 1
		 new Thread(new Runnable() {
			 @Override
			 public void run() {
				 while (true) {
					 // Schrijf hier je eigen code
					 // Random wachttijd
					 try {
						 // Genereer een getal tussen de 0 t/m 10.
						 int wachtTijd = (int) (Math.random() * 11);
						 System.out.println(Thread.currentThread().getName() + ": Slaap " + wachtTijd + " sec");
						 // Slaap wachtTijd seconden
						 Thread.sleep(wachtTijd * 1000);
					 } catch (InterruptedException e) {
						 e.printStackTrace();
					 }
				 }
			 }
		 }, "Thread 2").start();
	}
}
