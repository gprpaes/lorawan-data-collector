package br.ufrrj.labsd.mongo;

public class MongoModel {
    private int id;
    private String host;
    private String port;
    private String username;
    private String password;

    public MongoModel(int id, String host, String port, String username, String password) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }
}
