package com.solvd.database.model;

public class Connection {
    private int id;
    private int originStationId;
    private int destinationStationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginStationId() {
        return originStationId;
    }

    public void setOriginStationId(int originStationId) {
        this.originStationId = originStationId;
    }

    public int getDestinationStationId() {
        return destinationStationId;
    }

    public void setDestinationStationId(int destinationStationId) {
        this.destinationStationId = destinationStationId;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", originStationId=" + originStationId +
                ", destinationStationId=" + destinationStationId +
                '}';
    }
}