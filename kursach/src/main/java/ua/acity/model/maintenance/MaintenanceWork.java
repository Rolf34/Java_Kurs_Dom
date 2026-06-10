package ua.acity.model.maintenance;

import java.io.Serializable;
import java.time.LocalDate;

public class MaintenanceWork implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String buildingId;
    private String description;
    private MaintenanceType type;
    private String status; 
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate planedEndDate;
    private double budget;
    private String contractor;
    private String priority;

    public MaintenanceWork(String id, String buildingId, String description, MaintenanceType type) {
        this.id = id;
        this.buildingId = buildingId;
        this.description = description;
        this.type = type;
        this.status = "PLANNED";
        this.priority = "MEDIUM";
        this.budget = 0;
        this.contractor = "";
    }

    public String getId() {
        return id;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MaintenanceType getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getPlanedEndDate() {
        return planedEndDate;
    }

    public void setPlanedEndDate(LocalDate planedEndDate) {
        this.planedEndDate = planedEndDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format("MaintenanceWork{id='%s', building='%s', type=%s, status='%s', priority='%s'}",
                id, buildingId, type, status, priority);
    }
}
