package br.ufrrj.labsd.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    private DatabaseUtils () {}

    public static void setupDB(){
        System.out.println("Creating database tables (if necessary)...");
        createMongoTable();
        createMQTTTable();
        createCollectorTable();
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

    private static void createCollectorTable(){
        Connection database = DatabaseService.getInstance().getConnection();

        try {
            Statement statement = database.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS COLLECTOR(" +
                    "ID SERIAL PRIMARY KEY, "+
                    "COLLECTION TEXT,"+
                    "MONGO_ID INT,"+
                    "MQTT_ID INT,"+
                    "CONSTRAINT fk_mongo"+
                    "   FOREIGN KEY(MONGO_ID)" +
                    "   REFERENCES MONGO(ID),"+
                    "CONSTRAINT fk_mqtt"+
                    "   FOREIGN KEY(MQTT_ID)" +
                    "   REFERENCES MQTT(ID)"+
                    ");");
        } catch (SQLException e){
            System.out.println("Error creating Collector table "+e);
        }
    }


    private static void createMQTTTable(){
        Connection database = DatabaseService.getInstance().getConnection();

        try {
            Statement statement = database.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS MQTT(" +
                    "ID SERIAL PRIMARY KEY, "+
                    "HOST TEXT,"+
                    "PORT TEXT," +
                    "USERNAME TEXT," +
                    "PASSWORD TEXT" +
                    ");");
        } catch (SQLException e){
            System.out.println("Error creating MQTT table "+e);
        }
    }
}
