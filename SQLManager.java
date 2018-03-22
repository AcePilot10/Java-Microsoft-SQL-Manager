import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLManager {

	private String server;
	private int port;
	private String username;
	private String password;
	
	/**
	 * Create SQLManager instance with default server and port.
	 * @param username The username
	 * @param password The password
	 */
	public SQLManager(String username, String password) {
		this.username = username;
		this.password = password;
		this.server = "localhost";
		this.port = 1433;
	}
	
	/**
	 * Create SQLManager instance with custom server and port.
	 * @param username Username
	 * @param password Password
	 * @param server Server IP
	 * @param port Server Port
	 */
	public SQLManager(String username, String password, String server, int port) {
		this.username = username;
		this.password = password;
		this.server = server;
		this.port = port;
	}
	
	public void executeQuery(String query) {
		try {
			getConnection().createStatement().executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet getResult(String query) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Connection getConnection() {
		try {
			String url = "jdbc:sqlserver://" + server + ":" + port;
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getServer() {
		return this.server;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
