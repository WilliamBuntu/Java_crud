import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    //Establishes a connection to the database
    public Connection connect(String db_name, String username, String password) {
        Connection conn = null;
        try {
          Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:4000/"+db_name, username,password);
            if (conn != null) {
                System.out.println("Connected to the database");
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // creates table
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "CREATE TABLE "+table_name+" (emp_id serial PRIMARY KEY, name VARCHAR(200), address VARCHAR(200))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//     insert data
    public void insert_row(Connection conn, String table_name, String name, String address){
        Statement statement;
        try {
            String query = String.format("INSERT INTO %s (name, address) VALUES ('%s', '%s')", table_name, name, address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // read data
    public void read_data(Connection conn, String table_name){
        Statement statement;
        ResultSet resultSet;
        try {

            String query = String.format("SELECT * FROM %s", table_name);
            statement = conn.createStatement();
          resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("emp_id") + " Name: " + resultSet.getString("name") + " Address: " + resultSet.getString("address"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // update data
    public void update_data(Connection conn, String table_name, int id, String new_name){
        Statement statement;
        try {
            String query = String.format("UPDATE %s SET name = '%s' WHERE emp_id = %d", table_name, new_name, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // select data
    public void search_data(Connection conn, String table_name, String name){
        Statement statement;
        ResultSet resultSet;
        try {
            String query = String.format("SELECT * FROM %s WHERE name = '%s'", table_name, name);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("emp_id") + " Name: " + resultSet.getString("name") + " Address: " + resultSet.getString("address"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // delete data
    public void delete_data(Connection conn, String table_name, int id){
        Statement statement;
        try {
            String query = String.format("DELETE FROM %s WHERE emp_id = %d", table_name, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data deleted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // delete table
    public void delete_table(Connection conn, String table_name){
        Statement statement;
        try {
            String query = String.format("DROP TABLE %s", table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table deleted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
