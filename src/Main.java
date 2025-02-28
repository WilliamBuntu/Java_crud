import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

       DbFunctions db = new DbFunctions();
       Connection conn = db.connect("newDB", "postgres", "123456");
       //       db.createTable(conn, "employee");
//         db.insert_row(conn, "employee", "Travis", "23kg, Gikondo, avenue 5");
        db.update_data(conn, "employee", 4, "Mr Robot");
        db.read_data(conn, "employee");
    }
}