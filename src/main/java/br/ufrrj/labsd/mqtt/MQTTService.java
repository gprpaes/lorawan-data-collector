package br.ufrrj.labsd.mqtt;

import java.util.List;

public class MQTTService {
    MQTTRepository repository;


    public MQTTService(MQTTRepository repository){
        this.repository = repository;
    }

    public Integer create(MQTTModel mqttModel){
        boolean inserted = repository.save(mqttModel);

        if(inserted) return repository.getLastInsertedId();

        return  null;
    }

    public boolean update(Integer id, MQTTModel mqttModel){
        return repository.update(id, mqttModel);
    }

    public  Integer delete(int id){
        return repository.delete(id);
    }

    public MQTTModel get(int id){
        return repository.get(id);
    }

    public List<MQTTModel> getAll(){
        return repository.getAll();
    }
}
