package ua.acity.model.building;

public class AdministrativeBuilding extends Building {
    private static final long serialVersionUID = 1L;
    private String department;
    private int employees;

    public AdministrativeBuilding(String id, String address, int floors, double area, int yearBuilt,
            String department) {
        super(id, address, floors, area, yearBuilt);
        this.department = department;
        this.employees = 0;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
        this.occupants = employees;
    }

    @Override
    public String getType() {
        return "Administrative";
    }

    @Override
    public String toString() {
        return String.format("AdministrativeBuilding{id='%s', department='%s', employees=%d, maintenance='%s'}",
                id, department, employees, maintenanceStatus);
    }
}
