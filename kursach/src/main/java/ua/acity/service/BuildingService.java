package ua.acity.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.acity.model.building.Building;
import ua.acity.model.maintenance.MaintenanceType;
import ua.acity.model.maintenance.MaintenanceWork;
import ua.acity.model.request.ServiceRequest;
import ua.acity.observer.CityEvent;
import ua.acity.observer.EventPublisher;
import ua.acity.observer.EventType;
import ua.acity.singleton.CityRegistry;

public class BuildingService {
    private CityRegistry registry;
    private EventPublisher eventPublisher;
    private Map<String, ServiceRequest> requests;
    private Map<String, MaintenanceWork> maintenanceWorks;

    public BuildingService() {
        this.registry = CityRegistry.getInstance();
        this.eventPublisher = new EventPublisher();
        this.requests = new HashMap<>();
        this.maintenanceWorks = new HashMap<>();
    }

    public void addBuilding(Building building) {
        registry.registerBuilding(building);
        eventPublisher.notifyObservers(new CityEvent(EventType.BUILDING_CREATED,
                "Building created: " + building.getId() + " (" + building.getType() + ")"));
    }

    public Building getBuilding(String id) {
        return registry.getBuilding(id);
    }

    public Map<String, Building> getAllBuildings() {
        return registry.getAllBuildings();
    }

    public List<Building> getBuildingsByType(String type) {
        return registry.getAllBuildings().values().stream()
                .filter(b -> b.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public void updateBuildingNetworkStatus(String buildingId, String networkType, String status) {
        Building building = registry.getBuilding(buildingId);
        if (building != null) {
            switch (networkType.toLowerCase()) {
                case "water":
                    building.setWaterNetworkStatus(status);
                    break;
                case "electricity":
                    building.setElectricityNetworkStatus(status);
                    break;
                case "gas":
                    building.setGasNetworkStatus(status);
                    break;
                case "sewage":
                    building.setSewageNetworkStatus(status);
                    break;
                default:
                    System.out.println("Unknown network type: " + networkType);
                    return;
            }
            eventPublisher.notifyObservers(new CityEvent(EventType.BUILDING_UPDATED,
                    "Building network status updated: " + buildingId));
        } else {
            System.out.println("Building not found: " + buildingId);
        }
    }

    public String createServiceRequest(String buildingId, String userId, String description, String category) {
        String requestId = "REQ_" + System.currentTimeMillis();
        ServiceRequest request = new ServiceRequest(requestId, buildingId, userId, description, category);
        requests.put(requestId, request);
        eventPublisher.notifyObservers(new CityEvent(EventType.REQUEST_CREATED,
                "Service request created: " + requestId + " for building " + buildingId));
        return requestId;
    }

    public ServiceRequest getServiceRequest(String requestId) {
        return requests.get(requestId);
    }

    public List<ServiceRequest> getServiceRequestsByBuilding(String buildingId) {
        return requests.values().stream()
                .filter(r -> r.getBuildingId().equals(buildingId))
                .collect(Collectors.toList());
    }

    public List<ServiceRequest> getServiceRequestsByStatus(String status) {
        return requests.values().stream()
                .filter(r -> r.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public void assignServiceRequest(String requestId, String assignedTo) {
        ServiceRequest request = requests.get(requestId);
        if (request != null) {
            request.setAssignedTo(assignedTo);
            request.setStatus("ASSIGNED");
        } else {
            System.out.println("Request not found: " + requestId);
        }
    }

    public void completeServiceRequest(String requestId, int rating) {
        ServiceRequest request = requests.get(requestId);
        if (request != null) {
            request.setStatus("COMPLETED");
            request.setCompletedAt(LocalDateTime.now());
            request.setRating(rating);
            eventPublisher.notifyObservers(new CityEvent(EventType.REQUEST_COMPLETED,
                    "Service request completed: " + requestId + " (Rating: " + rating + "/5)"));
        } else {
            System.out.println("Request not found: " + requestId);
        }
    }

    public String createMaintenanceWork(String buildingId, String description,
            String type, LocalDate plannedEndDate) {
        String maintenanceId = "MAINT_" + System.currentTimeMillis();
        MaintenanceWork work = new MaintenanceWork(maintenanceId, buildingId, description,
                MaintenanceType.valueOf(type));
        work.setPlanedEndDate(plannedEndDate);
        maintenanceWorks.put(maintenanceId, work);
        eventPublisher.notifyObservers(new CityEvent(EventType.BUILDING_UPDATED,
                "Maintenance work planned: " + maintenanceId + " for building " + buildingId));
        return maintenanceId;
    }

    public MaintenanceWork getMaintenanceWork(String maintenanceId) {
        return maintenanceWorks.get(maintenanceId);
    }

    public List<MaintenanceWork> getMaintenanceWorkByBuilding(String buildingId) {
        return maintenanceWorks.values().stream()
                .filter(m -> m.getBuildingId().equals(buildingId))
                .collect(Collectors.toList());
    }

    public List<MaintenanceWork> getMaintenanceWorkByStatus(String status) {
        return maintenanceWorks.values().stream()
                .filter(m -> m.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<MaintenanceWork> getAllMaintenanceWorks() {
        return new ArrayList<>(maintenanceWorks.values());
    }

    public void startMaintenanceWork(String maintenanceId) {
        MaintenanceWork work = maintenanceWorks.get(maintenanceId);
        if (work != null) {
            work.setStatus("IN_PROGRESS");
            work.setStartDate(LocalDate.now());
            eventPublisher.notifyObservers(new CityEvent(EventType.BUILDING_UPDATED,
                    "Maintenance work started: " + maintenanceId));
        } else {
            System.out.println("Maintenance work not found: " + maintenanceId);
        }
    }

    public void completeMaintenanceWork(String maintenanceId) {
        MaintenanceWork work = maintenanceWorks.get(maintenanceId);
        if (work != null) {
            work.setStatus("COMPLETED");
            work.setEndDate(LocalDate.now());
            eventPublisher.notifyObservers(new CityEvent(EventType.BUILDING_UPDATED,
                    "Maintenance work completed: " + maintenanceId));
        } else {
            System.out.println("Maintenance work not found: " + maintenanceId);
        }
    }

    public Map<String, Integer> getStatisticsByBuildingType() {
        Map<String, Integer> stats = new HashMap<>();
        for (Building building : registry.getAllBuildings().values()) {
            String type = building.getType();
            stats.put(type, stats.getOrDefault(type, 0) + 1);
        }
        return stats;
    }

    public int getTotalOccupants() {
        return registry.getAllBuildings().values().stream()
                .mapToInt(Building::getOccupants)
                .sum();
    }

    public double getAverageAgeOfBuildings() {
        List<Building> buildings = new ArrayList<>(registry.getAllBuildings().values());
        if (buildings.isEmpty()) return 0;
        return buildings.stream()
                .mapToInt(Building::getAgeInYears)
                .average()
                .orElse(0);
    }

    public Map<String, Long> getRequestStatsByStatus() {
        return requests.values().stream()
                .collect(Collectors.groupingBy(ServiceRequest::getStatus, Collectors.counting()));
    }

    public EventPublisher getEventPublisher() {
        return eventPublisher;
    }
}
