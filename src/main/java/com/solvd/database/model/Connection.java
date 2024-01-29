package com.solvd.database.model;

import java.util.Objects;

public class Connection {
    private int id;
    private int originStationId;
    private int destinationStationId;

    public Connection(int id, int originStationId, int destinationStationId) {
        this.id = id;
        this.originStationId = originStationId;
        this.destinationStationId = destinationStationId;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return id == that.id && originStationId == that.originStationId && destinationStationId == that.destinationStationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originStationId, destinationStationId);
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