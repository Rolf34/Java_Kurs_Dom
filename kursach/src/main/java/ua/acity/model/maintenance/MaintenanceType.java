package ua.acity.model.maintenance;

public enum MaintenanceType {
    WATER_NETWORK("Water Network Repair"),
    ELECTRICITY_NETWORK("Electricity Network Repair"),
    GAS_NETWORK("Gas Network Repair"),
    SEWAGE_NETWORK("Sewage Network Repair"),
    ROOF_REPAIR("Roof Repair"),
    FACADE_REPAIR("Facade Repair"),
    ELEVATOR_REPAIR("Elevator Repair"),
    PLUMBING("Plumbing"),
    HEATING_SYSTEM("Heating System"),
    GENERAL_INSPECTION("General Inspection"),
    CONSERVATION("Conservation Work"),
    OTHER("Other");

    private final String description;

    MaintenanceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
