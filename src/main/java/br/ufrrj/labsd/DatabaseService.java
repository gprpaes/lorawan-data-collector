package br.ufrrj.labsd;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import java.util.Properties;

public class DatabaseService {
    private static DatabaseService database;
    private Connection connection;

    private DatabaseService () {
        Dotenv dotenv = Dotenv.load();
        String url = "jdb:postgresql:/";
        Properties props = new Properties();
        props.setProperty("user", dotenv.get("PSQL_USER"));
        props.setProperty("host", dotenv.get("PSQL_HOST"));
        props.setProperty("port", dotenv.get("PSQL_PORT"));
        props.setProperty("database", dotenv.get("PSQL_DB"));
        props.setProperty("password", dotenv.get("PSQL_PASSWORD"));
       try {
           connection = DriverManager.getConnection(url, props);
       } catch (SQLException e) {
           System.out.println("Error on connecting to database: "+e);
       }
    }

    public static DatabaseService getInstance() {
        if(database == null) {
            database = new DatabaseService();
        }
        return database;
    }

    public Connection getConnection() {
        return connection;
    }
}
