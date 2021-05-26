package utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Centralina;
import entity.Sensore;

public class  DBConnection {

	private Connection myConn;

	public DBConnection (){
		this.myConn = null;
	}

	public void connect(JSONObject dbConfig) throws SQLException, JSONException, IOException {
		myConn = DriverManager.getConnection("jdbc:" + dbConfig.getString("dbms") + "://" + dbConfig.getString("serverName")+ ":" + dbConfig.getString("port") + "/" + dbConfig.getString("name") , dbConfig.getString("username") , dbConfig.getString("password"));

	}

	public void disconnect() throws SQLException {
		if (myConn != null) {
			myConn.close();
		}
	}

	public ArrayList<Centralina> getCentraline() throws SQLException {

		ArrayList<Centralina> centraline = new ArrayList<Centralina>();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {		
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT idC, Nome FROM Centralina");	
			while (myRs.next()) {
				Centralina c = new Centralina(myRs.getInt("idC"), myRs.getString("Nome"));
				centraline.add(c);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}

		}
		return centraline;
	}

	public void insertOsservazione(float valore, String date, int idSensore) throws SQLException {
		
		System.out.println("Osservazione: Valore - "+ valore + ", Sensore - " + idSensore);
		
		Statement myStmt = null;
		try {
			myStmt = myConn.createStatement();
			myStmt.executeUpdate("INSERT INTO Osservazioni VALUES ('0', '"+valore+"','"+date+"','"+idSensore+"')");

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			
			if (myStmt != null) {
				myStmt.close();
			}
			
		}
	}

	public ArrayList<Sensore> getSensori(int idCentralina) throws SQLException{

		ArrayList<Sensore> sensori = new ArrayList<Sensore>();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT idS, Tipo FROM Sensori WHERE idCentralina = " + idCentralina);
			while (myRs.next()) {
				Sensore s = new Sensore(myRs.getInt("idS"), myRs.getString("Tipo"));
				sensori.add(s);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}

		}
		return sensori;
	}

}