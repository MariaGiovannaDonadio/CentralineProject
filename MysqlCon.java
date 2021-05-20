import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class  MysqlCon {

	public static ArrayList<String> getCentraline() throws SQLException {

		ArrayList<String> centraline = new ArrayList<String>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Centraline", "root" , "KOdakkdt1");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT Nome FROM Centralina");
			
			// 4. Process the result set
			while (myRs.next()) {
				//System.out.println(myRs.getString("Valore") + ", " + myRs.getString("DataState"));
				centraline.add(myRs.getString("Nome"));
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

	public static void insertOsservatione(float valore, String date, int idSensore) throws SQLException {
		
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Centraline", "root" , "KOdakkdt1");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			//myStmt.executeQuery("INSERT INTO Osservazioni VALUES ("+valore+","+date+","+idSensore+")");
			System.out.println("INSERT INTO Osservazioni VALUES ("+valore+","+date+","+idSensore+")");
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

}