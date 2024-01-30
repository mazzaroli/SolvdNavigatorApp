package com.solvd.database.model;

public class Route {
    private int id;
    private int busId;
    private int routeOrder;
    private int stationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(int routeOrder) {
        this.routeOrder = routeOrder;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", busId=" + busId +
                ", routeOrder=" + routeOrder +
                ", stationId=" + stationId +
                '}';
    }
}
