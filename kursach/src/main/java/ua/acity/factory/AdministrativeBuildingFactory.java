package ua.acity.factory;

import ua.acity.model.building.AdministrativeBuilding;
import ua.acity.model.building.Building;

public class AdministrativeBuildingFactory implements BuildingFactory {
    @Override
    public Building createBuilding(BuildingCreationParams params) {
        String department = (String) params.getParam("department");
        return new AdministrativeBuilding(
                params.getId(),
                params.getAddress(),
                params.getFloors(),
                params.getArea(),
                params.getYearBuilt(),
                department);
    }
}
