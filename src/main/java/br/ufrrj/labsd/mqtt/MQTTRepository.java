package br.ufrrj.labsd.mqtt;

import br.ufrrj.labsd.database.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MQTTRepository {
    Connection database = DatabaseService.getInstance().getConnection();

    // @TODO: Passar a senha por uma função de derivação de chave, bcrypt ou PBKDF2
    public boolean save(MQTTModel mqttModel) {
        try {
            PreparedStatement statement = database.prepareStatement("INSERT INTO MQTT (HOST, PORT, USERNAME, PASSWORD) VALUES(?, ?, ?, ?);");
            statement.setString(1, mqttModel.getHost());
            statement.setString(2, mqttModel.getPort());
            statement.setString(3, mqttModel.getUsername());
            statement.setString(4, mqttModel.getPassword());

            int i = statement.executeUpdate();
            boolean success = i == 1;
            statement.close();
            return success;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Integer id, MQTTModel mqttModel) {
        try {
            PreparedStatement statement = database.prepareStatement("UPDATE MQTT SET HOST = ?, PORT = ?, USERNAME = ?, PASSWORD = ? WHERE ID = ?;");
            statement.setString(1, mqttModel.getHost());
            statement.setString(2, mqttModel.getPort());
            statement.setString(3, mqttModel.getUsername());
            statement.setString(4, mqttModel.getPassword());
            statement.setInt(5, id);

            int success = statement.executeUpdate();

            statement.close();

            return success == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public MQTTModel get(int mqttId) {
        try {
            PreparedStatement statement = database.prepareStatement("SELECT * FROM MQTT WHERE ID = ?;");
            statement.setInt(1, mqttId);
            ResultSet resultSet = statement.executeQuery();
            MQTTModel mqttModel = null;

            while (resultSet.next()) {
                mqttModel = new MQTTModel(resultSet.getInt("ID"),
                        resultSet.getString("HOST"),
                        resultSet.getString("PORT"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"));
            }

            statement.close();
            return mqttModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Integer delete(int mqttId){
        try {
            PreparedStatement statetement = database.prepareStatement("DELETE FROM MQTT WHERE ID = ?;");
            statetement.setInt(1, mqttId);
            Integer queryResult = statetement.executeUpdate();
            statetement.close();
            return queryResult;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<MQTTModel> getAll(){
        try{
            Statement statement = database.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MQTT;");
            List<MQTTModel> mqttModels = new ArrayList<>();
            while (resultSet.next()) {
               MQTTModel mqttModel = new MQTTModel(resultSet.getInt("ID"),
                        resultSet.getString("HOST"),
                        resultSet.getString("PORT"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"));

               mqttModels.add(mqttModel);
            }

            statement.close();
            return mqttModels;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public Integer getLastInsertedId(){
        try {
            PreparedStatement statement = database.prepareStatement("select id from mqtt order by id desc fetch first 1 row only");
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

