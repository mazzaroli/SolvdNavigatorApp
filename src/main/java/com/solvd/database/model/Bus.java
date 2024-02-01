package com.solvd.database.model;

public class Bus {
    private Integer id;
    private String lineName;
    private int conexionId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getConexionId() {
        return conexionId;
    }

    public void setConexionId(int conexionId) {
        this.conexionId = conexionId;
    }

    public Bus() {
        super();
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", lineName='" + lineName + '\'' +
                ", conexionId=" + conexionId +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}