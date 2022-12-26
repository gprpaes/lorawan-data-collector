package br.ufrrj.labsd.mqtt;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbTransient;

public class MQTTModel {
    private int id;
    private String host;
    private String port;
    private String username;
    private String password;

    @JsonbCreator
    public MQTTModel(int id, String host, String port, String username, String password) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    @JsonbTransient
    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @JsonbTransient
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MQTTModel{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
