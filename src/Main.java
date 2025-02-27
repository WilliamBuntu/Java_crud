import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

       DbFunctions db = new DbFunctions();
       Connection conn = db.connect("newDB", "postgres", "123456");
//       db.createTable(conn, "employee");
//         db.insert_row(conn, "employee", "Jessica", "122 Main St, Queens park, IL");
        db.update_data(conn, "employee", "Jessica", "Darline");
        db.read_data(conn, "employee");
    }
}