package ua.acity.model.building;

public class CommercialBuilding extends Building {
    private static final long serialVersionUID = 1L;
    private int shops;
    private int offices;
    private int tenants;

    public CommercialBuilding(String id, String address, int floors, double area, int yearBuilt, int shops,
            int offices) {
        super(id, address, floors, area, yearBuilt);
        this.shops = shops;
        this.offices = offices;
        this.tenants = 0;
    }

    public int getShops() {
        return shops;
    }

    public void setShops(int shops) {
        this.shops = shops;
    }

    public int getOffices() {
        return offices;
    }

    public void setOffices(int offices) {
        this.offices = offices;
    }

    public int getTenants() {
        return tenants;
    }

    public void setTenants(int tenants) {
        this.tenants = tenants;
        this.occupants = tenants;
    }

    @Override
    public String getType() {
        return "Commercial";
    }

    @Override
    public String toString() {
        return String.format(
                "CommercialBuilding{id='%s', address='%s', shops=%d, offices=%d, tenants=%d, maintenance='%s'}",
                id, address, shops, offices, tenants, maintenanceStatus);
    }
}
