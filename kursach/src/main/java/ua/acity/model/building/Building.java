package ua.acity.model.building;

import java.io.Serializable;

public abstract class Building implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String address;
    protected int floors;
    protected double area;
    protected int yearBuilt;
    protected String waterNetworkStatus; 
    protected String electricityNetworkStatus; 
    protected String gasNetworkStatus; 
    protected String sewageNetworkStatus;
    protected String maintenanceStatus;
    protected int occupants;

    public Building(String id, String address, int floors, double area, int yearBuilt) {
        this.id = id;
        this.address = address;
        this.floors = floors;
        this.area = area;
        this.yearBuilt = yearBuilt;
        this.waterNetworkStatus = "GOOD";
        this.electricityNetworkStatus = "GOOD";
        this.gasNetworkStatus = "GOOD";
        this.sewageNetworkStatus = "GOOD";
        this.maintenanceStatus = "ACTIVE";
        this.occupants = 0;
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

    public String getWaterNetworkStatus() {
        return waterNetworkStatus;
    }

    public void setWaterNetworkStatus(String status) {
        this.waterNetworkStatus = status;
    }

    public String getElectricityNetworkStatus() {
        return electricityNetworkStatus;
    }

    public void setElectricityNetworkStatus(String status) {
        this.electricityNetworkStatus = status;
    }

    public String getGasNetworkStatus() {
        return gasNetworkStatus;
    }

    public void setGasNetworkStatus(String status) {
        this.gasNetworkStatus = status;
    }

    public String getSewageNetworkStatus() {
        return sewageNetworkStatus;
    }

    public void setSewageNetworkStatus(String status) {
        this.sewageNetworkStatus = status;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String status) {
        this.maintenanceStatus = status;
    }

    public int getOccupants() {
        return occupants;
    }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }

    public abstract String getType();

    public int getAgeInYears() {
        return java.time.Year.now().getValue() - yearBuilt;
    }
}
