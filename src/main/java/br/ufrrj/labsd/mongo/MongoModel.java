package br.ufrrj.labsd.mongo;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbTransient;

public class MongoModel {
    private Integer id;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String name;

   @JsonbCreator
    public MongoModel(Integer id, String host, Integer port, String username, String password, String name) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    @JsonbTransient
    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonbTransient
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MongoModel{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
