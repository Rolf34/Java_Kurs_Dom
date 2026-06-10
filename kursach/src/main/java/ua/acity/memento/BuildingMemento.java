package ua.acity.memento;

import ua.acity.model.building.Building;

public class BuildingMemento {
    private final String id;
    private final String address;
    private final int floors;
    private final double area;

    public BuildingMemento(Building building) {
        this.id = building.getId();
        this.address = building.getAddress();
        this.floors = building.getFloors();
        this.area = building.getArea();
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getFloors() {
        return floors;
    }

    public double getArea() {
        return area;
    }
}
