import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*
 * MySQL Database connector.
 * This is a basic MySQL database connector with nothing extra.
 * I'm writing this code because I bored and I want to write some code.
 * This code will need user input and other modification in case if it doesn't work with your system.
 * For security reason user name , password, port number and database name will need to be input by the user.
 * Use Code practice. 
 */
public class Main {

	private static Connection connection = null;
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		Main main = new Main();
		try {
			System.out.println("\n\t\t\t-------------------------------------\n");
			System.out.println("\n\t\t\t MySQL Database connector Java Code \n");
			System.out.println("\n\t\t\t-------------------------------------\n");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURLConnection = "jdbc:mysql://localhost:";
			System.out.println("Enter the port Number below the test ( ͡❛ ͜ʖ ͡❛)");
			String databasePort = in.nextLine();
			System.out.println("Enter the name of the DataBase you want to connect ( ͡❛ ͜ʖ ͡❛)");
			String databaseName = in.nextLine();
			String dbURL = dbURLConnection + databasePort+ "/"+ databaseName;

			System.out.println("Enter your Username of the MySQL database");
			String username = in.nextLine();
			System.out.println(username);
			System.out.println("Enter your Password of the MySQL database");
			String password = in.nextLine();
			System.out.println(password);

			connection = DriverManager.getConnection(dbURL, username, password);

			System.out.println("--------------------------------------------");
			
			System.out.println(connection.getClientInfo(databaseName));
			
			System.out.println("Enter Choice");
			System.out.println("[1] Insert record");
			System.out.println("[2] Select record");
			System.out.println("[3] Callable Statement: Select record");
			System.out.println("[4] Update record");
			System.out.println("[5] Delete record");
			System.out.println("[6] Status of Server");

			System.out.println("--------------------------------------------");
			int choice = Integer.parseInt(in.nextLine());

			switch (choice) {
			case 1: {
				main.insertRecord();
				break;
			}
			case 2:
				main.selectRecord();
				break;
			case 3: 
				main.selectAllREcord();
				break;
			case 4:
				main.updateRecord();
				break;
			case 5:
				main.deleteRecord();
				break;
			case 6:
				main.statusOfServer();
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}

		} catch (Exception e) {
			throw new RuntimeException("Alert Alert !!!! Something went wrong !!!! " + e.getMessage());
			//			System.out.println("SQLException: " + e.getMessage());
			//		    System.out.println("SQLState: " + e.getSQLState());
			//		    System.out.println("VendorError: " + e.getErrorCode());
		}
		System.out.println("Welcome to MYSQL Apps !!!!");
	}
	private void insertRecord() throws SQLException{
		System.out.println("Insert Record");
		String sql = "INSERT INTO customer( name, meter, address, city, state, email, phone) value(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		System.out.println("Enter your name: ");
		preparedStatement.setString(1, in.nextLine());
		System.out.println("Enter your meter: ");
		preparedStatement.setString(1, in.nextLine());
		int rows = preparedStatement.executeUpdate();
		if (rows > 0) {
			System.out.println("The record insert successfully !!! ");
		}
	}
	public void selectRecord() throws SQLException{
		String sql = "SELECT * FROM tax";
		System.out.println("Select method is call !!!");
		Statement statement = connection.createStatement();
		ResultSet resultSet =statement.executeQuery(sql);
		while (resultSet.next()) {
			int rollnumber = resultSet.getInt(1);
			System.out.println(" ");
		}
	}
	private void updateRecord() throws SQLException {
		String sql = "SELECT * FROM login where username = *";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		if (result.next()) {
			System.out.println(" ");
		}else {
			System.out.println("Record not found");
		}
	}
	private void selectAllREcord()throws SQLException {
		System.out.println("I'm in the 3 methode");
		CallableStatement callableStatement = connection.prepareCall("{ GET_ALL() }");
		ResultSet result = callableStatement.executeQuery();
		while (result.next()) {
			System.out.println("Roll number is " + result.getInt(0));
			System.out.println("Name is " + result.getString(2));
			System.out.println("--------------------------------------------");

		}
	}
	private void deleteRecord() throws SQLException {
		System.out.println("This function will delecte the record");
		String sql = "INSERT INTO customer( name, meter, address, city, state, email, phone) value(?,?,?,?,?,?,?)";
		Statement statement = connection.createStatement();

	}
	private void statusOfServer()throws SQLException {
		String sql = "status";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);

	}
}
