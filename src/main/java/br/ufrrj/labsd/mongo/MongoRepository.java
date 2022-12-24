package br.ufrrj.labsd.mongo;

import br.ufrrj.labsd.database.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MongoRepository {
    Connection database = DatabaseService.getInstance().getConnection();

    // @TODO: Passar a senha por uma função de derivação de chave, bcrypt ou PBKDF2
    public boolean saveMongoInstance(MongoModel mongoModel){
        try {
            PreparedStatement statement = database.prepareStatement("INSERT INTO MONGO (NAME, HOST,PORT,USERNAME,PASSWORD) VALUES(?,?,?,?,?);");
            statement.setString(1, mongoModel.getName());
            statement.setString(2, mongoModel.getHost());
            statement.setInt(3, mongoModel.getPort());
            statement.setString(4, mongoModel.getUsername());
            statement.setString(5, mongoModel.getPassword());

            int i = statement.executeUpdate();
            boolean success = i == 1;
            statement.close();
            return success;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateMongoInstance(Integer id, MongoModel mongoModel){
       try {
           PreparedStatement statement = database.prepareStatement("UPDATE MONGO SET NAME = ?, " +
                   "HOST= ?, PORT = ?, USERNAME = ?, PASSWORD = ? WHERE ID = ?;");

           statement.setString(1, mongoModel.getName());
           statement.setString(2, mongoModel.getHost());
           statement.setInt(3, mongoModel.getPort());
           statement.setString(4, mongoModel.getUsername());
           statement.setString(5, mongoModel.getPassword());
           statement.setInt(6, id);

           int success = statement.executeUpdate();

           statement.close();

           return success == 1;
       } catch (SQLException e){
           e.printStackTrace();
           return false;
       }
    }

    public MongoModel getMongoInstance(int mongoId) {
        try {
            PreparedStatement statement = database.prepareStatement("SELECT * FROM MONGO WHERE ID = ?;");
            statement.setInt(1, mongoId);
            ResultSet resultSet = statement.executeQuery();
            MongoModel mongoModel = null;
            while (resultSet.next()) {
                mongoModel = new MongoModel(resultSet.getInt("ID"),
                        resultSet.getString("HOST"),
                        resultSet.getInt("PORT"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("NAME"));
            }

            statement.close();
            return mongoModel;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer deleteMongoInstance(int mongoId) {
       try {
           PreparedStatement statetement = database.prepareStatement("DELETE FROM MONGO WHERE ID = ?;");
           statetement.setInt(1, mongoId);
           Integer queryResult = statetement.executeUpdate();
           statetement.close();
           return queryResult;
       } catch (SQLException e){
           e.printStackTrace();
           return null;
       }
    }

    //Esse método poderia ser paginado para suportar uma quantidade grande de registro no banco
    // Na prática, hoje, não vejo uma quantidade possível de registros que justifique a paginação.
    public List<MongoModel> getAllMongoInstances(){
        try {
            Statement statement = database.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MONGO;");
            List<MongoModel> mongoModels = new ArrayList<>();
            while (resultSet.next()) {
                MongoModel model = new MongoModel(resultSet.getInt("ID"),
                        resultSet.getString("HOST"),
                        resultSet.getInt("PORT"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("NAME"));
                mongoModels.add(model);
            }
            statement.close();
            return mongoModels;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer getLastInsertedId(){
        Connection database = DatabaseService.getInstance().getConnection();
        try {
            PreparedStatement statement = database.prepareStatement("select id from mongo order by id desc fetch first 1 row only");
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
