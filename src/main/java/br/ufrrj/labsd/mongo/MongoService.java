package br.ufrrj.labsd.mongo;

import br.ufrrj.labsd.database.DatabaseService;
import br.ufrrj.labsd.database.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MongoService {
    private MongoRepository mongoRepository;

    public MongoService(MongoRepository mongoRepository){
        this.mongoRepository = mongoRepository;
    }

    public Integer createMongoInstance(MongoModel mongoModel){
        boolean inserted =  mongoRepository.saveMongoInstance(mongoModel);
        if(inserted){
            return mongoRepository.getLastInsertedId();
        }

        return null;
    }

    public boolean updateMongoInstance(Integer id, MongoModel mongoModel){
        return  mongoRepository.updateMongoInstance(id, mongoModel);
    }

    public Integer deleteMongoInstance(int mongoId){
        return mongoRepository.deleteMongoInstance(mongoId);
    }

    public MongoModel getMongoInstance(int mongoId){
        return mongoRepository.getMongoInstance(mongoId);
    }

    public List<MongoModel> getAllMongoInstances(){
        return mongoRepository.getAllMongoInstances();
    }

}
