package ua.acity.model.building;

public class ArchitecturalMonument extends Building {
    private static final long serialVersionUID = 1L;
    private String heritage;
    private boolean isUnderProtection;
    private String conservationStatus; 

    public ArchitecturalMonument(String id, String address, int floors, double area, int yearBuilt, String heritage) {
        super(id, address, floors, area, yearBuilt);
        this.heritage = heritage;
        this.isUnderProtection = true;
        this.conservationStatus = "GOOD";
    }

    public String getHeritage() {
        return heritage;
    }

    public void setHeritage(String heritage) {
        this.heritage = heritage;
    }

    public boolean isUnderProtection() {
        return isUnderProtection;
    }

    public void setUnderProtection(boolean underProtection) {
        isUnderProtection = underProtection;
    }

    public String getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(String status) {
        this.conservationStatus = status;
    }

    @Override
    public String getType() {
        return "Architectural Monument";
    }

    @Override
    public String toString() {
        return String.format(
                "ArchitecturalMonument{id='%s', heritage='%s', yearBuilt=%d, protection=%s, conservation='%s'}",
                id, heritage, yearBuilt, isUnderProtection, conservationStatus);
    }
}
