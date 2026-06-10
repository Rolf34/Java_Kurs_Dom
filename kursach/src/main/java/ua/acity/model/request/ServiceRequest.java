package ua.acity.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ServiceRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String buildingId;
    private String userId;
    private String description;
    private String category;
    private String status; 
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String assignedTo; 
    private String notes;
    private int rating;

    public ServiceRequest(String id, String buildingId, String userId, String description, String category) {
        this.id = id;
        this.buildingId = buildingId;
        this.userId = userId;
        this.description = description;
        this.category = category;
        this.status = "PENDING";
        this.priority = "MEDIUM";
        this.createdAt = LocalDateTime.now();
        this.rating = 0;
    }

    public String getId() {
        return id;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        }
    }

    public long getResponseTimeHours() {
        if (completedAt != null) {
            return java.time.temporal.ChronoUnit.HOURS.between(createdAt, completedAt);
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("ServiceRequest{id='%s', category='%s', status='%s', priority='%s', building='%s'}",
                id, category, status, priority, buildingId);
    }
}
