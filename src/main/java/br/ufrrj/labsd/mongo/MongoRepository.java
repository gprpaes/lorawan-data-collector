package br.ufrrj.labsd.mongo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MongoRepository {
    Connection database;

    // @TODO: Passar a senha por uma função de derivação de chave, bcrypt ou PBKDF2
    public boolean saveMongoInstance(MongoModel mongoModel) throws SQLException{
        PreparedStatement statement = database.prepareStatement("INSERT INTO MONGO VALUES(?,?,?,?,?);");
        statement.setString(1, mongoModel.getName());
        statement.setString(2, mongoModel.getHost());
        statement.setString(3, mongoModel.getPort());
        statement.setString(4, mongoModel.getUsername());
        statement.setString(5, mongoModel.getPassword());

        boolean success = statement.execute();

        statement.close();
        return success;
    }


    public boolean updateMongoInstance(MongoModel mongoModel) throws SQLException{
        PreparedStatement statement = database.prepareStatement("UPDATE MONGO SET NAME = ?, " +
                "HOST= ?, PORT = ?, USERNAME = ?, PASSWORD = ? WHERE ID = ?;");

        statement.setString(1, mongoModel.getName());
        statement.setString(2, mongoModel.getHost());
        statement.setString(3, mongoModel.getPort());
        statement.setString(4, mongoModel.getUsername());
        statement.setString(5, mongoModel.getPassword());
        statement.setInt(6, mongoModel.getId());

        int success = statement.executeUpdate();

        statement.close();

        return success == 1;
    }

    public MongoModel getMongoInstance(int mongoId) throws  SQLException{
        PreparedStatement statement = database.prepareStatement("SELECT * FROM MONGO WHERE ID = ?;");
        statement.setInt(1, mongoId);
        ResultSet resultSet = statement.executeQuery();
        MongoModel mongoModel = null;
        while (resultSet.next()){
            mongoModel = new MongoModel(resultSet.getInt("ID"),
                    resultSet.getString("HOST"),
                    resultSet.getString("PORT"),
                    resultSet.getString("USERNAME"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getString("NAME"));
        }

        statement.close();
        return mongoModel;
    }

    public boolean deleteMongoInstance(int mongoId) throws SQLException {
        PreparedStatement statetement = database.prepareStatement("DELETE FROM MONGO WHERE ID = ?;");
        statetement.setInt(1, mongoId);
        boolean queryResult  = statetement.execute();
        statetement.close();
        return queryResult;

    }

    //Esse método poderia ser paginado para suportar uma quantidade grande de registro no banco
    // Na prática, hoje, não vejo uma quantidade possível de registros que justifique a paginação.
    public List<MongoModel> getAllMongoInstances() throws SQLException {
        Statement statement = database.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM MONGO;");
        List<MongoModel> mongoModels = new ArrayList<>();
        while (resultSet.next()){
            MongoModel model = new MongoModel(resultSet.getInt("ID"),
                    resultSet.getString("HOST"),
                    resultSet.getString("PORT"),
                    resultSet.getString("USERNAME"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getString("NAME"));
            mongoModels.add(model);
        }
        statement.close();
        return mongoModels;
    }
}
