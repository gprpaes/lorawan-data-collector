package br.ufrrj.labsd.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    private DatabaseUtils () {}

    public static void setupDB(){
        System.out.println("Creating database tables (if necessary)...");
        createMongoTable();
    }


    private static void createMongoTable(){
        Connection database = DatabaseService.getInstance().getConnection();

        try {
            Statement statement = database.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS MONGO(" +
                    "ID SERIAL PRIMARY KEY," +
                    "NAME TEXT NOT NULL," +
                    "HOST TEXT NOT NULL," +
                    "PORT INT NOT NULL," +
                    "USERNAME TEXT," +
                    "PASSWORD TEXT);");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error creating mongo table "+e);
        }
    }
}
