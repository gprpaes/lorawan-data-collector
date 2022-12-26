package br.ufrrj.labsd.collector;

import br.ufrrj.labsd.database.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectorRepository {
    Connection database = DatabaseService.getInstance().getConnection();

    public boolean save(CollectorModel collectorModel){
        try{
            PreparedStatement statement = database.prepareStatement("INSERT INTO COLLECTOR (COLLECTION, MONGO_ID, MQTT_ID) VALUES(?,?,?);");
            statement.setString(1, collectorModel.getCollection());
            statement.setInt(2, collectorModel.getMongoId());
            statement.setInt(3, collectorModel.getMqttId());

            int i = statement.executeUpdate();
            boolean success = i == 1;
            statement.close();
            return success;
        } catch(SQLException e){
            e.printStackTrace();
            return  false;
        }
    }

    public boolean update(Integer id, CollectorModel model){
        try {
            PreparedStatement statement = database.prepareStatement("UPDATE COLLECTOR SET COLLECTION = ?, MONGO_ID = ?, MQTT_ID = ? WHERE ID = ?;");
            statement.setString(1, model.getCollection());
            statement.setInt(2, model.getMongoId());
            statement.setInt(3, model.getMqttId());
            statement.setInt(4, id);

            int i = statement.executeUpdate();
            boolean success = i == 1;
            statement.close();
            return success;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public CollectorModel get(int id){
        try {
            PreparedStatement statement = database.prepareStatement("SELECT * FROM COLLECTOR WHERE ID = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            CollectorModel collectorModel = null;

            while (resultSet.next()){
                collectorModel = new CollectorModel(resultSet.getInt("ID"),
                        resultSet.getString("COLLECTION"),
                        resultSet.getInt("MONGO_ID"),
                        resultSet.getInt("MQTT_ID"));
            }
            statement.close();
            return collectorModel;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer delete(int id){
        try {
            PreparedStatement statetement = database.prepareStatement("DELETE FROM COLLECTOR WHERE ID = ?;");
            statetement.setInt(1, id);
            Integer queryResult = statetement.executeUpdate();
            statetement.close();
            return queryResult;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<CollectorModel> getAll(){
        try {
            Statement statement = database.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COLLECTOR;");
            List<CollectorModel> collectorModels = new ArrayList<>();
            while (resultSet.next()){
             CollectorModel collectorModel = new CollectorModel(resultSet.getInt("ID"),
                        resultSet.getString("COLLECTION"),
                        resultSet.getInt("MONGO_ID"),
                        resultSet.getInt("MQTT_ID"));
             collectorModels.add(collectorModel);
            }

            statement.close();
            return collectorModels;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer getLastInsertedId(){
        Connection database = DatabaseService.getInstance().getConnection();
        try {
            PreparedStatement statement = database.prepareStatement("select id from collector order by id desc fetch first 1 row only");
            ResultSet rs = statement.executeQuery();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt("ID");
            }

            return id;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


}
