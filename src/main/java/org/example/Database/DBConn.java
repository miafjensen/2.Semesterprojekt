package org.example.Database;
// hej der

/*import java.sql.*;
import java.util.Queue;

public class DBConn {
    static DBConn instance;
    //private Queue queue = Queue.getInstance();
    static Connection myconn = null;
    static String DB_url = "jdbc;mysql;//localhost;3306/Semesterprojekt2";
    static String user = "root";
    static String password = "Emmeb.10";
    static String JDBC_driver = "com.mysql.jdbc.Driver";
//username:root kode:Emmeb.10
//hejsa

    //jdbc;mysql;//localhost;3306/navnet på databasen
    private DBConn() {
        try {
            Class.forName(JDBC_driver).getDeclaredConstructor().newInstance();
            myconn = DriverManager.getConnection(DB_url, user, password);
            myconn.setAutoCommit(false);
            System.out.println("forbindelse til db oprettet");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public static DBConn getInstance(){
        if(instance==null)
            instance = new DBConn();
        return instance;
    }


}
*/