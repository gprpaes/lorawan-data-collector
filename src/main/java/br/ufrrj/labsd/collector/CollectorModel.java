package br.ufrrj.labsd.collector;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbTransient;

public class CollectorModel {
    private int id;
    private String collection;
    private int mongoId;
    private int mqttId;

    @JsonbCreator
    public CollectorModel(int id, String collection, int mongoId, int mqttId) {
        this.id = id;
        this.collection = collection;
        this.mongoId = mongoId;
        this.mqttId = mqttId;
    }

    public int getId() {
        return id;
    }

    @JsonbTransient
    public void setId(int id) {
        this.id = id;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public int getMongoId() {
        return mongoId;
    }

    public void setMongoId(int mongoId) {
        this.mongoId = mongoId;
    }

    public int getMqttId() {
        return mqttId;
    }

    public void setMqttId(int mqttId) {
        this.mqttId = mqttId;
    }

    @Override
    public String toString() {
        return "CollectorModel{" +
                "id=" + id +
                ", collection='" + collection + '\'' +
                ", mongoId=" + mongoId +
                ", mqttId=" + mqttId +
                '}';
    }
}


