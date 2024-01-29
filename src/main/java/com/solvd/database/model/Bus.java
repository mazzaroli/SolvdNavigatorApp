package com.solvd.database.model;

import java.util.Objects;

public class Bus{
    private int id;
    private String lineName;

    public Bus(int id, String lineName) {
        this.id = id;
        this.lineName = lineName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return id == bus.id && Objects.equals(lineName, bus.lineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lineName);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", lineName='" + lineName + '\'' +
                '}';
    }
}