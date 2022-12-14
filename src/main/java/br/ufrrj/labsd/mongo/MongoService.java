package br.ufrrj.labsd.mongo;

import java.util.List;

public class MongoService {
    private MongoRepository mongoRepository;

    public MongoService(MongoRepository mongoRepository){
        this.mongoRepository = mongoRepository;
    }

    public boolean createMongoInstance(MongoModel mongoModel){
        return mongoRepository.saveMongoInstance(mongoModel);
    }

    public boolean updateMongoInstance(MongoModel mongoModel){
        return  mongoRepository.updateMongoInstance(mongoModel);
    }

    public boolean deleteMongoInstance(int mongoId){
        return mongoRepository.deleteMongoInstance(mongoId);
    }

    public MongoModel getMongoInstance(int mongoId){
        return mongoRepository.getMongoInstance(mongoId);
    }

    public List<MongoModel> getAllMongoInstances(){
        return mongoRepository.getAllMongoInstances();
    }
}
