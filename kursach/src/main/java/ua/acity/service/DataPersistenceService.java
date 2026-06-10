package ua.acity.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import ua.acity.model.building.Building;
import ua.acity.model.maintenance.MaintenanceWork;
import ua.acity.model.request.ServiceRequest;

public class DataPersistenceService {
    private String dataDirectory;

    public DataPersistenceService(String dataDirectory) {
        this.dataDirectory = dataDirectory;
        File dir = new File(dataDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void saveBuildingsToFile(Map<String, Building> buildings) throws IOException {
        String filePath = dataDirectory + File.separator + "buildings.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(buildings);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Building> loadBuildingsFromFile() throws IOException, ClassNotFoundException {
        String filePath = dataDirectory + File.separator + "buildings.dat";
        File file = new File(filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, Building>) ois.readObject();
        }
    }

    public void saveServiceRequestsToFile(Map<String, ServiceRequest> requests) throws IOException {
        String filePath = dataDirectory + File.separator + "requests.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(requests);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, ServiceRequest> loadServiceRequestsFromFile() throws IOException, ClassNotFoundException {
        String filePath = dataDirectory + File.separator + "requests.dat";
        File file = new File(filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, ServiceRequest>) ois.readObject();
        }
    }

    public void saveMaintenanceWorksToFile(Map<String, MaintenanceWork> works) throws IOException {
        String filePath = dataDirectory + File.separator + "maintenance.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(works);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, MaintenanceWork> loadMaintenanceWorksFromFile() throws IOException, ClassNotFoundException {
        String filePath = dataDirectory + File.separator + "maintenance.dat";
        File file = new File(filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, MaintenanceWork>) ois.readObject();
        }
    }

    public void exportBuildingsToText(Map<String, Building> buildings) throws IOException {
        String filePath = dataDirectory + File.separator + "buildings_report.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("=== BUILDINGS REPORT ===");
            writer.println("Generated: " + new java.util.Date());
            writer.println();

            for (Building building : buildings.values()) {
                writer.println("Building ID: " + building.getId());
                writer.println("Type: " + building.getType());
                writer.println("Address: " + building.getAddress());
                writer.println("Year Built: " + building.getYearBuilt());
                writer.println("Age: " + building.getAgeInYears() + " years");
                writer.println("Floors: " + building.getFloors());
                writer.println("Area: " + building.getArea() + " m²");
                writer.println("Occupants: " + building.getOccupants());
                writer.println("Water Network: " + building.getWaterNetworkStatus());
                writer.println("Electricity Network: " + building.getElectricityNetworkStatus());
                writer.println("Gas Network: " + building.getGasNetworkStatus());
                writer.println("Sewage Network: " + building.getSewageNetworkStatus());
                writer.println("Maintenance Status: " + building.getMaintenanceStatus());
                writer.println("---");
            }
        }
    }

    public void exportServiceRequestsToText(Map<String, ServiceRequest> requests) throws IOException {
        String filePath = dataDirectory + File.separator + "requests_report.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("=== SERVICE REQUESTS REPORT ===");
            writer.println("Generated: " + new java.util.Date());
            writer.println();

            for (ServiceRequest request : requests.values()) {
                writer.println("Request ID: " + request.getId());
                writer.println("Building: " + request.getBuildingId());
                writer.println("Category: " + request.getCategory());
                writer.println("Status: " + request.getStatus());
                writer.println("Priority: " + request.getPriority());
                writer.println("Description: " + request.getDescription());
                writer.println("Created: " + request.getCreatedAt());
                if (request.getCompletedAt() != null) {
                    writer.println("Completed: " + request.getCompletedAt());
                    writer.println("Response Time: " + request.getResponseTimeHours() + " hours");
                }
                if (request.getRating() > 0) {
                    writer.println("Rating: " + request.getRating() + "/5");
                }
                writer.println("---");
            }
        }
    }
}
