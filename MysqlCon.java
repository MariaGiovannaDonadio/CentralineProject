import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class  MysqlCon {

	public static ArrayList<Centralina> getCentraline() throws SQLException {

		ArrayList<Centralina> centraline = new ArrayList<Centralina>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Centraline", "root" , "KOdakkdt1");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT idC, Nome FROM Centralina");
			
			// 4. Process the result set
			while (myRs.next()) {
				//System.out.println(myRs.getString("Valore") + ", " + myRs.getString("DataState"));
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
			
			if (myConn != null) {
				myConn.close();
			}
		}
		return centraline;
	}

	public static void insertOsservazione(float valore, String date, int idSensore) throws SQLException {
		
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Centraline", "root" , "KOdakkdt1");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myStmt.executeUpdate("INSERT INTO Osservazioni VALUES ('0', '"+valore+"','"+date+"','"+idSensore+"')");

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}

	public static ArrayList<Sensore> getSensori(int idCentralina) throws SQLException{

		ArrayList<Sensore> sensori = new ArrayList<Sensore>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Centraline", "root" , "KOdakkdt1");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT idS, Tipo FROM Sensori");
			
			// 4. Process the result set
			while (myRs.next()) {
				//System.out.println(myRs.getString("Valore") + ", " + myRs.getString("DataState"));
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
			
			if (myConn != null) {
				myConn.close();
			}
		}
		return sensori;
	}

}