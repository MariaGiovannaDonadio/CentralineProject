import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class  MysqlCon {

	private Connection myConn;

	public MysqlCon (){
		this.myConn = null;
	}

	public void connect() throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Centraline", "root" , "KOdakkdt1");

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
		
		Statement myStmt = null;
		
		try {
			System.out.println("Osservazione: valore - "+ valore + ", Sensore - " + idSensore);
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
			
		}
	}

	public ArrayList<Sensore> getSensori(int idCentralina) throws SQLException{

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
			myRs = myStmt.executeQuery("SELECT idS, Tipo FROM Sensori WHERE idCentralina = " + idCentralina);
			
			// 4. Process the result set
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