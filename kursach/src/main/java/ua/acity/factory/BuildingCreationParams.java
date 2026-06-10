package ua.acity.factory;

import java.util.HashMap;
import java.util.Map;

public class BuildingCreationParams {
    private String id;
    private String address;
    private int floors;
    private double area;
    private int yearBuilt;
    private Map<String, Object> additionalParams = new HashMap<>();

    public BuildingCreationParams(String id, String address, int floors, double area, int yearBuilt) {
        this.id = id;
        this.address = address;
        this.floors = floors;
        this.area = area;
        this.yearBuilt = yearBuilt;
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

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void addParam(String key, Object value) {
        additionalParams.put(key, value);
    }

    public Object getParam(String key) {
        return additionalParams.get(key);
    }

    public Map<String, Object> getAdditionalParams() {
        return new HashMap<>(additionalParams);
    }
}
