package com.solvd.database.model;

import java.util.Objects;

public class Route {
    private int id;

    private int busId;

    private int routeOrder;

    private int stationId;

    public Route(int id, int busId, int routeOrder, int stationId) {
        this.id = id;
        this.busId = busId;
        this.routeOrder = routeOrder;
        this.stationId = stationId;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id && busId == route.busId && routeOrder == route.routeOrder && stationId == route.stationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, busId, routeOrder, stationId);
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
