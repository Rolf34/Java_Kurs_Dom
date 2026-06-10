package ua.acity.model.building;

public class ResidentialBuilding extends Building {
    private static final long serialVersionUID = 1L;
    private int apartments;
    private int families;

    public ResidentialBuilding(String id, String address, int floors, double area, int yearBuilt, int apartments) {
        super(id, address, floors, area, yearBuilt);
        this.apartments = apartments;
        this.families = 0;
    }

    public int getApartments() {
        return apartments;
    }

    public void setApartments(int apartments) {
        this.apartments = apartments;
    }

    public int getFamilies() {
        return families;
    }

    public void setFamilies(int families) {
        this.families = families;
        this.occupants = families * 3; 
    }

    @Override
    public String getType() {
        return "Residential";
    }

    @Override
    public String toString() {
        return String.format("ResidentialBuilding{id='%s', address='%s', apartments=%d, families=%d, maintenance='%s'}",
                id, address, apartments, families, maintenanceStatus);
    }
}
